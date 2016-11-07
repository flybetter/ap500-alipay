package org.alipay.dao;

import org.alipay.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class CustomerDaoTest {
	
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CustomerDao customerDao;

	@Test
	public void testQueryByDeviceAccountId() {
		Integer userId=25;
		Customer customer =customerDao.queryByDeviceAccountId(userId);
		logger.info("customer:"+customer);
		
	}

}
