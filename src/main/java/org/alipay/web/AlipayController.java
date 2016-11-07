package org.alipay.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alipay.config.AlipayConfig;
import org.alipay.dto.AlipayExecution;
import org.alipay.dto.AlipayResult;
import org.alipay.dto.Exposer;
import org.alipay.entity.Feepackage;
import org.alipay.entity.Mypackage;
import org.alipay.enums.AlipayStatEnum;
import org.alipay.enums.FeepackageTypeEnum;
import org.alipay.exception.AlipayCustomerException;
import org.alipay.exception.AlipayException;
import org.alipay.exception.AlipayReduceException;
import org.alipay.service.FeepackageService;
import org.alipay.service.MypackageService;
import org.alipay.util.AlipayNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/alipay")
public class AlipayController {

	public Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public FeepackageService feepackageService;

	@Autowired
	public MypackageService mypackageService;
	


	/**
	 * 
	* @Title: list 
	* @Description: 获得所有的套餐
	* @param @param userId
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/{userId}/list")
	public String list(@PathVariable("userId") Integer userId, Model model) {
		logger.debug("11111");
		logger.info("222");
		List<Feepackage> subjectList = feepackageService.getFeepackageListByType(FeepackageTypeEnum.SUBJECT.getValue());
		List<Feepackage> supplementList = feepackageService
				.getFeepackageListByType(FeepackageTypeEnum.SUPPLEMENT.getValue());
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("supplementList", supplementList);
		model.addAttribute("userId", userId);
		return "list";
	
	}

	/**
	 * 
	* @Title: my_list 
	* @Description: 通过userid获得套餐
	* @param @param userId
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/{userId}/my_list")
	public String my_list(@PathVariable("userId") Integer userId, Model model) {
		logger.debug("11111");
		logger.info("222");
		List<Mypackage> subjectList = mypackageService.getMypackageListByType(userId,
				FeepackageTypeEnum.SUBJECT.getValue());
		List<Mypackage> supplementList = mypackageService.getMypackageListByType(userId,
				FeepackageTypeEnum.SUPPLEMENT.getValue());
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("supplementList", supplementList);
		model.addAttribute("userId", userId);
		return "my_list";
	};

	/**
	 * 
	* @Title: detail 
	* @Description: 获得套餐详情
	* @param @param feepackageId
	* @param @return    设定文件 
	* @return AlipayResult<Feepackage>    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/{feepackageId}/detail", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public AlipayResult<Feepackage> detail(@PathVariable("feepackageId") Integer feepackageId) {
		try {
			Feepackage feepackage = feepackageService.getFeepackageById(feepackageId);
			return new AlipayResult<Feepackage>(true, feepackage);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AlipayResult<Feepackage>(false, e.getMessage());
		}
	};

	/**
	 * 
	* @Title: exposer 
	* @Description: 暴露md5 
	* @param @param feepackageId
	* @param @return    设定文件 
	* @return AlipayResult<Exposer>    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/{feepackageId}/exposer", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public AlipayResult<Exposer> exposer(@PathVariable("feepackageId") Integer feepackageId) {
		try {
			Exposer exposer = feepackageService.exportFeepackageUrl(feepackageId);
			return new AlipayResult<Exposer>(true, exposer);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AlipayResult<Exposer>(false, AlipayStatEnum.INNER_ERROR.getStateInfo());
		}
	}

	/**
	 * 
	* @Title: execution 
	* @Description: 执行下单 
	* @param @param feepackageId
	* @param @param md5
	* @param @param userId
	* @param @return    设定文件 
	* @return AlipayResult<AlipayExecution>    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/{feepackageId}/{md5}/execution", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public AlipayResult<AlipayExecution> execution(@PathVariable("feepackageId") Integer feepackageId,
			@PathVariable("md5") String md5, @CookieValue(value = "userid", required = false) Integer userId) {
		if (userId == null) {
			return new AlipayResult<AlipayExecution>(false, "userId is null!");
		}
		String payChannel	 = AlipayConfig.PAYCHANNEL;
		String payMethod = AlipayConfig.PAYMETHOD;
		String payType = AlipayConfig.PAYTYPE;
		String orderState = AlipayConfig.ORDER;
		try {
			AlipayExecution alipayExecution = feepackageService.executionFeepackage(userId, md5, feepackageId,
					payChannel, payMethod, payType, orderState, null, null, null);
			return new AlipayResult<AlipayExecution>(true, alipayExecution);
		} catch (AlipayCustomerException e1) {
			return new AlipayResult<AlipayExecution>(false, e1.getMessage());
		} catch (AlipayReduceException e2) {
			return new AlipayResult<AlipayExecution>(false, e2.getMessage());
		} catch (AlipayException e3) {
			return new AlipayResult<AlipayExecution>(false, e3.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AlipayResult<AlipayExecution>(false, AlipayStatEnum.INNER_ERROR.getStateInfo());
		}
	}

	/**
	 * @throws UnsupportedEncodingException 
	 * 
	* @Title: return_url 
	* @Description: 支付宝get请求回调
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/return_url",method=RequestMethod.GET)
	public String return_url(HttpServletRequest request ) throws UnsupportedEncodingException{
		//获取支付宝GET过来反馈信息
		String result=null;
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = request.getParameter("out_trade_no");

		//支付宝交易号
		String trade_no = request.getParameter("trade_no");

		//交易状态
		String trade_status =request.getParameter("trade_status");
		
		//买家支付宝账号
		String buyer_email  = request.getParameter("buyer_email");
		
		
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
		if(verify_result){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				try {
					AlipayExecution alipayExecution=feepackageService.successFeepackage(out_trade_no, buyer_email);
					if(alipayExecution.getState()==AlipayStatEnum.DATA_REWRITE.getState()){
						logger.info("get请求回调   orderNo:"+out_trade_no+" result:"+alipayExecution.getStateInfo());
						return "pay_success";
					}
				} catch (AlipayException e) {
					logger.error(e.getMessage(),e);
					return "pay_fail";
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					return "pay_fail";
				}
				
			}
		}else{
			return "pay_fail";
		}
		return null;
	}

	@RequestMapping(value = "test")
	public String test() {
		return "pay_success";
	};

	
}
