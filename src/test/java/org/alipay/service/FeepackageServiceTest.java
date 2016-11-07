package org.alipay.service;

import java.util.List;

import org.alipay.config.AlipayConfig;
import org.alipay.dto.AlipayExecution;
import org.alipay.dto.Exposer;
import org.alipay.entity.Feepackage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
						"classpath:spring/spring-service.xml"})
public class FeepackageServiceTest {
	
	Logger logger=LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FeepackageService feepackageService;

	@Test
	public void testGetFeepackageListByType() {
		Integer type=0;
		List<Feepackage> feepackageList=feepackageService.getFeepackageListByType(type);
		for(Feepackage feepackage:feepackageList){
			logger.info("feepackage:"+feepackage);
		}
	}

	@Test
	public void testGetFeepackageById() {
		Integer feepackageId=1;
	    Feepackage feepackage 	=feepackageService.getFeepackageById(feepackageId);
		logger.info("feepackage:"+feepackage);
	}

	@Test
	public void testExportFeepackageUrl() {
		Integer feepackageId=1;
	    Exposer exposer	=feepackageService.exportFeepackageUrl(feepackageId);
	    logger.info("exposer:"+exposer);
		//[md5=ab85dcebe610f09273329c93a901dd08, feepackageId=1]
	}

	@Test
	public void testExecutionFeepackage() {
		Integer userId=39;
		String md5="ab85dcebe610f09273329c93a901dd08";
		Integer feepackageId=1;
		String payChannel=AlipayConfig.PAYCHANNEL;
		String payMethod=AlipayConfig.PAYMETHOD;
		String payType=AlipayConfig.PAYTYPE;
		String orderState=AlipayConfig.FAIL;
		String payTitle="111";
		String payDescription="222";
		String otherCost="0";
		AlipayExecution alipayExection=feepackageService.executionFeepackage(userId, md5, feepackageId, payChannel, payMethod, payType,  orderState, payTitle, payDescription, otherCost);
		logger.info("alipayExection:"+alipayExection);
	}

	@Test
	public void testSuccessFeepackage() {
		String orderNo="autophone2016080110321296";
		String  payAccount="13357708210";
		AlipayExecution alipayExection=feepackageService.successFeepackage(orderNo, payAccount);
		logger.info("alipayExection:"+alipayExection);
	}
	
	
	@Test
	public void testFailFeepackage() {
		String orderNo="autophone2016072814190634";
		String  payAccount="13357708210";
		AlipayExecution alipayExection=feepackageService.failFeepackage(orderNo, payAccount);
		logger.info("alipayExection:"+alipayExection);
	}
	
}
