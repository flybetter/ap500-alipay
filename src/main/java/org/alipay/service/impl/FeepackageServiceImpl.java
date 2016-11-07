package org.alipay.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alipay.config.AlipayConfig;
import org.alipay.dao.CustomerDao;
import org.alipay.dao.FeepackageDao;
import org.alipay.dao.MypackageDao;
import org.alipay.dao.cache.MongodbDao;
import org.alipay.dao.cache.RedisDao;
import org.alipay.dto.AlipayExecution;
import org.alipay.dto.Exposer;
import org.alipay.entity.Customer;
import org.alipay.entity.Feepackage;
import org.alipay.entity.thread.OrderTask;
import org.alipay.enums.AlipayStatEnum;
import org.alipay.exception.AlipayCustomerException;
import org.alipay.exception.AlipayException;
import org.alipay.exception.AlipayReduceException;
import org.alipay.queue.TaskQueueDaemonThread;
import org.alipay.service.FeepackageService;
import org.alipay.service.MypackageService;
import org.alipay.util.AlipaySubmit;
import org.alipay.util.CommonTool;
import org.alipay.util.UtilDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.mongodb.DBObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("feepackageService")
public class FeepackageServiceImpl implements FeepackageService {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());

	@Autowired
	public  AlipayConfig alipayConfig;
	
	@Autowired
	public AlipaySubmit alipaySubmit;
	
	@Autowired
	public FeepackageDao feepackageDao;
	
	@Autowired
	public CustomerDao customerDao;
	
	@Autowired
	public MongodbDao mongodbDao;
	
	@Autowired
	public MypackageDao mypackageDao;
	
	@Autowired
	public MypackageService mypackageService;
	
	@Autowired
	public RedisDao redisDao;
	
	@Autowired
	public TaskQueueDaemonThread taskQueueDaemonThread;

	@Override
	public List<Feepackage> getFeepackageListByType(Integer type) {
		String redisKey="type:"+type;
		String redisValue=redisDao.getRedisObject(redisKey);
		List<Feepackage> feepackageList =null;
		if(redisValue==null){
			 feepackageList=feepackageDao.queryAll(type, null, null);
			 redisValue=JSONArray.fromObject(feepackageList).toString();
			 redisDao.putRedisObject(redisKey, redisValue);
		}else{
			 JSONArray json=JSONArray.fromObject(redisValue);
			 feepackageList =(List<Feepackage>)JSONArray.toCollection(json, Feepackage.class);
		}
		return feepackageList;
	}

	@Override
	public Feepackage getFeepackageById(Integer feepackageId) {
		String redisKey="feepackageId:"+feepackageId;
		String redisValue=redisDao.getRedisObject(redisKey);
		Feepackage feepackage =null;
		if(redisValue==null){
			 feepackage=feepackageDao.queryById(feepackageId);
			 redisValue=JSONObject.fromObject(feepackage).toString();
			 redisDao.putRedisObject(redisKey, redisValue);
		}else{
			 feepackage=(Feepackage)JSONObject.toBean(JSONObject.fromObject(redisValue), Feepackage.class);
		} 
		
		return feepackage;
	}

	@Override
	public Exposer exportFeepackageUrl(Integer feepackageId) {
		String md5=getMD5(feepackageId);
		return new Exposer(md5, feepackageId);
	}

	@Override
	public AlipayExecution executionFeepackage(Integer userId, String md5, Integer feepackageId,
			String payChannel,String payMethod,String payType,String orderState,
			String payTitle,String payDescription ,String otherCost)
			throws AlipayCustomerException,AlipayException, AlipayReduceException {
		if(md5==null||!md5.equals(getMD5(feepackageId))){
			throw new AlipayException("alipay md5 wrong!");
		}
		try {
			Customer customer=customerDao.queryByDeviceAccountId(userId);
			
			if(customer==null){
				throw new AlipayCustomerException("customer is null !");
			}
			
			if(payTitle==null){
				payTitle=customer.getName()+"的订单";
			}
			
			if(payDescription==null){
				payDescription=customer.getName()+"的订单详情";
			}
			
			if(otherCost==null){
				otherCost="0";
			}
			
			Feepackage feepackage=feepackageDao.queryById(feepackageId);
			
			Map<String,Object> paramMap=new HashMap<>();
			
			paramMap.put("buyer",CommonTool.transBeanToMap(customer));
			paramMap.put("combo",CommonTool.transBeanToMap(feepackage));
			paramMap.put("buyerType", AlipayConfig.BUYERTYPE);
			paramMap.put("payChannel", payChannel);
			paramMap.put("payMethod", payMethod);
			paramMap.put("payType", payType);
			paramMap.put("salePrice", feepackage.getTotal());
			paramMap.put("orderState", orderState);
			paramMap.put("payTitle", payTitle);
			paramMap.put("orderState", orderState);
			paramMap.put("payDescription", payDescription);
			paramMap.put("userId", userId);
			paramMap.put("otherCost", otherCost);
			paramMap.put("companyAccount", AlipayConfig.COMPANYACCOUNT);
			paramMap.put("buyTime", new Date());
			paramMap.put("lastPayTime", UtilDate.nextHourDate(new Date()));
			//paramMap.put("payAccount", payAccount);
			String orderNo=mongodbDao.insertOrder(paramMap);
			
			Integer result=feepackageDao.reduceStockCount(feepackageId, feepackage.getLeastPurchase());
			
			if(result<=0){
				throw new AlipayReduceException("alipay reduce stock fail!");
			}else{
				String form=alipaySubmit.getForm(orderNo, feepackage.getName(), feepackage.getTotal().toString(), feepackage.getDescription());
				logger.info("userId:"+userId+"  orderNo:"+orderNo+"  提交给支付宝! ");
				OrderTask orderTask=new OrderTask(orderNo,feepackageId,feepackage.getLeastPurchase());
				taskQueueDaemonThread.put(1000*60*alipayConfig.order_invaid, orderTask);
				return new AlipayExecution(form, AlipayStatEnum.PRESUCCESS);
			}
		} catch (AlipayCustomerException e1) {
			throw  e1;	
		} catch (AlipayReduceException e2) {
			throw  e2;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AlipayException("alipay inner error:"+ e.getMessage());
		}
		
	}

	@Override
	public AlipayExecution successFeepackage( String orderNo, String payAccount)
			throws AlipayException {
		try {
			DBObject object=mongodbDao.queryByOrderNo(orderNo);
			if(object==null){
				throw new AlipayException("mongodb queryByOrderNo error!");
			}
			if(object.get("orderState").equals(AlipayConfig.SUCCESS)){
				return new AlipayExecution( AlipayStatEnum.DATA_REWRITE,orderNo);
			}
			if(object.get("orderState").equals(AlipayConfig.FAIL)){
				return new AlipayExecution( AlipayStatEnum.FAIL,orderNo);
			}
			DBObject combo=(DBObject) object.get("combo");
			Integer feepackageId=null;
			Integer userId=null;
			if(combo.containsField("id")&&object.containsField("userId")){
				feepackageId=(Integer)combo.get("id");
				userId=(Integer) object.get("userId");
			}else{
				throw new AlipayException("mongodb paramer error!");
			}
			Map<String,Object>paramMap=new HashMap<>();
			paramMap.put("orderState", AlipayConfig.SUCCESS);
			paramMap.put("payAccount", payAccount);
			paramMap.put("payTime", new Date());
			boolean mogodbResult=mongodbDao.updateOrder(orderNo, paramMap);
			if(!mogodbResult){
				throw new AlipayException("mongodb update error!");
			}
			Feepackage feepackage=feepackageDao.queryById(feepackageId);
			if(feepackage==null){
				throw new AlipayException("alipay feepackage null !");
			}
			Integer feepackageResult=feepackageDao.addSoldCount(feepackageId, feepackage.getLeastPurchase());
			if(feepackageResult<=0){
				throw new AlipayException("alipay addSoldCount fail !");
			}
			Integer mypackageResult =mypackageService.insertMypackage(userId, feepackageId);
			if(mypackageResult<=0){
				throw new AlipayException("alipay insertMypackage fail !");
			}else{
				return new AlipayExecution(feepackage,orderNo, AlipayStatEnum.SUCCESS);
			}
		}catch(AlipayException e1){	
			throw e1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AlipayException("alipay inner error:"+ e.getMessage());
		}
	}

	//TODO
	@Override
	public AlipayExecution failFeepackage( String orderNo, String payAccount)
			throws AlipayException  {
		try {
			DBObject object=mongodbDao.queryByOrderNo(orderNo);
			if(object==null){
				throw new AlipayException("mongodb queryById error!");
			}
			if(object.get("orderState").equals(AlipayConfig.SUCCESS)){
				return new AlipayExecution(orderNo, AlipayStatEnum.DATA_REWRITE);
			}
			DBObject combo=(DBObject) object.get("combo");
			Integer feepackageId=null;
			if(combo.containsField("id")&&object.containsField("userId")){
				feepackageId=(Integer)combo.get("id");
			}else{
				throw new AlipayException("mongodb paramer error!");
			}
			Map<String,Object>paramMap=new HashMap<>();
			paramMap.put("orderState", AlipayConfig.FAIL);
			paramMap.put("payAccount", payAccount);
			boolean mogodbResult=mongodbDao.updateOrder(orderNo, paramMap);
			if(!mogodbResult){
				throw new AlipayException("mongodb update error!");
			}
			Feepackage feepackage=feepackageDao.queryById(feepackageId);
			if(feepackage==null){
				throw new AlipayException("alipay feepackage null !");
			}
			Integer feepackageResult=feepackageDao.addSoldCount(feepackageId, feepackage.getLeastPurchase());
			if(feepackageResult<=0){
				throw new AlipayException("alipay addSoldCount fail !");
			}else{
				return new AlipayExecution(feepackage,orderNo, AlipayStatEnum.FAIL);
			}
		}catch(AlipayException e1){	
			throw e1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AlipayException("alipay inner error:"+ e.getMessage());
		}
	}
	
	
	private String getMD5(Integer feepackageId){
		String base=feepackageId+'/'+alipayConfig.slat;
		String md5=DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

}
