package org.alipay.dao;

import java.util.List;

import org.alipay.entity.Feepackage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class FeepackageDaoTest {

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FeepackageDao feepackageDao;

	@Test
	public void testReduceStockCount() {
		int feepackageId=1;
		int leastPurchase=6;
		int result=feepackageDao.reduceStockCount(feepackageId, leastPurchase);
		logger.info("reuslt:"+result);
	}

	@Test
	public void testQueryAll() {
		Integer type =3;
		List<Feepackage> feepackageList=feepackageDao.queryAll(type, null, null);
		for(Feepackage age:feepackageList){
			logger.info("age:"+age);
		}
	}

	@Test
	public void testQueryById() {
		Integer feepackageId=1;
		Feepackage feepackage=feepackageDao.queryById(feepackageId);
		logger.info("feepackage:"+feepackage);
	}

	@Test
	public void testAddSoldCount() {
		Integer feepackageId=1;
		Integer leastPurchase=6;
		Integer result=feepackageDao.addSoldCount(feepackageId, leastPurchase);
		logger.info("result:"+result);
	}
	
	
	@Test
	public void testAddStockCount() {
		Integer feepackageId=1;
		Integer leastPurchase=6;
		Integer result=feepackageDao.addStockCount(feepackageId, leastPurchase);
		logger.info("result:"+result);
	}

}
