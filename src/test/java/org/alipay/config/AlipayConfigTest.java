package org.alipay.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
						"classpath:spring/spring-service.xml",
						"classpath:spring/spring-web.xml"})
public class AlipayConfigTest {

	@Test
	public void test() {
	}

}
