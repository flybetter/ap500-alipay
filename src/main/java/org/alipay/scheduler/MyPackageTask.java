package org.alipay.scheduler;

import org.alipay.dao.MypackageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("myPackageTask")
public class MyPackageTask {
	
	@Autowired
	private MypackageDao mypackageDao;
	
	
	@Scheduled(cron = "0 0 23 * * ?")  
	public void myPackageTask(){
		mypackageDao.deleteMypackage();
	};

}
