package org.alipay.dao;

import java.util.Date;
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
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class MypackageDaoTest {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MypackageDao mypackageDao;

	@Test
	public void testQueryByUserIdAndPackageType() {
		Integer userId=25;
		Integer type=0;
		List<Mypackage> myPackageList=mypackageDao.queryByUserIdAndPackageType(userId, type);
		for(Mypackage mypackage:myPackageList){
			logger.info("mypackage:"+mypackage);
		}
	}

	@Test
	public void testInsertMypackage() {
		Integer  packageId=1;
		Date startTime=new Date(); 		
		Date endTime=new Date(); 	
		Integer packageType=0;
		Integer chargingWay=1;
		Integer userId=25;
		Integer reuslt=mypackageDao.insertMypackage(packageId, startTime, endTime, packageType, chargingWay, userId);
		logger.info("reuslt:"+reuslt);
		
	}

}
