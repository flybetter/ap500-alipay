package org.alipay.service;

import java.util.List;

import org.alipay.entity.Mypackage;
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
public class MypackageServiceTest {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MypackageService mypackageSerivce;
	
	@Test
	public void testGetMypackageListByType() {
		Integer userId=25;
		Integer type=0;
		List<Mypackage>mypackageList=mypackageSerivce.getMypackageListByType(userId, type);
		for(Mypackage mypackage:mypackageList){
			logger.info("mypackage:"+mypackage);
		}
	}
	
	
	@Test
	public void testInsertMypackage(){
		Integer userId=40;
		Integer feepackageId=3;
		Integer result=mypackageSerivce.insertMypackage(userId, feepackageId);
		logger.info("result:"+result);
	}

}
