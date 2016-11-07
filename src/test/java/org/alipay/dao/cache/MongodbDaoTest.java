package org.alipay.dao.cache;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class MongodbDaoTest {
	
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MongodbDao mongodbDao;

	@Test
	public void testInsertOrder() {
		Map <String,Object>paramMap=new HashMap<>();
		paramMap.put("name", "ccc");
		String id=mongodbDao.insertOrder(paramMap);
		logger.info("id:"+id);
	}
	


}
