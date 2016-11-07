package org.alipay.service;

import java.util.List;

import org.alipay.dto.AlipayExecution;
import org.alipay.dto.Exposer;
import org.alipay.entity.Feepackage;
import org.alipay.exception.AlipayCustomerException;
import org.alipay.exception.AlipayException;
import org.alipay.exception.AlipayReduceException;

public interface FeepackageService {
	
	/**
	 * 
	* @Title: getFeepackageListByType 
	* @Description: 通过type查询套餐类型
	* @param @param type
	* @param @return    设定文件 
	* @return List<Feepackage>    返回类型 
	* @throws
	 */
	List<Feepackage> getFeepackageListByType(Integer type);
	
	
	/**
	 * 
	* @Title: getFeepackageById 
	* @Description: 通过id获取套餐类型
	* @param @param feepackageId
	* @param @return    设定文件 
	* @return Feepackage    返回类型 
	* @throws
	 */
	Feepackage getFeepackageById(Integer feepackageId);
	
	
	/**
	 * 
	* @Title: exportFeepackageUrl 
	* @Description: 通过feepackageId获得md5并且放回页面提高安全性
	* @param @param feepackageId
	* @param @return    设定文件 
	* @return Exposer    返回类型 
	* @throws
	 */
	Exposer exportFeepackageUrl(Integer feepackageId);
	
	
	
	/**
	 * 
	* @Title: executionFeepackage 
	* @Description: 执行下单
	* @param @param userId
	* @param @param md5
	* @param @param feepackageId
	* @param @param payChannel 支付通道 (支付宝  其他)
	* @param @param payMethod  支付方式 (扫码支付   系统外支付)
	* @param @param payType 购买方式(终端方式 人工录入)
	* @param @param salePrice
	* @param @param orderState 已付款 未付款
	* @param @param payTitle
	* @param @param payDescription
	* @param @param otherCost
	* @param @throws AlipayCustomerException
	* @param @throws AlipayException
	* @param @throws AlipayReduceException    设定文件 
	* @return AlipayExecution    返回类型 
	* @throws
	 */
	AlipayExecution executionFeepackage(Integer userId, String md5, Integer feepackageId,
			String payChannel,String payMethod,String payType,String orderState,
			String payTitle,String payDescription ,String otherCost)
			throws AlipayCustomerException,AlipayException,AlipayReduceException;
	
	
	
	/**
	 * 
	* @Title: successFeepackage 
	* @Description: 支付宝回调成功后,套餐已售加数量
	* @param @param feepackageId
	* @param @param orderId
	* @param @param payAccount
	* @param @param userId
	* @param @return    设定文件 
	* @return AlipayExecution    返回类型 
	* @throws
	 */
	AlipayExecution successFeepackage(String orderNo, String payAccount)
			throws AlipayException;

	/**
	 * 
	* @Title: failFeepackage 
	* @Description:  支付宝回调失败后,套餐数量加数量
	* @param @param feepackageId
	* @param @param orderId
	* @param @param payAccount
	* @param @return    设定文件 
	* @return AlipayExecution    返回类型 
	* @throws
	 */
	//TODO
    AlipayExecution failFeepackage(String orderNo, String payAccount)
    		throws AlipayException;
		


}
