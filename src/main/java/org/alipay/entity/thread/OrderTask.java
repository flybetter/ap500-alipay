package org.alipay.entity.thread;

import org.alipay.dao.FeepackageDao;
import org.alipay.queue.TaskQueueDaemonThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class OrderTask implements Runnable {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
	
	private FeepackageDao feepackageDao;
	
	private  Integer feepackageId;
	
	private Integer leastPurchase;

	private String orderNo;
	
	private TaskQueueDaemonThread taskQueueDaemonThread;
	
	private final Object lockObj = new Object();

	public OrderTask(String orderNo,Integer feepackageId,Integer leastPurchase) {
		super();
		feepackageDao= (FeepackageDao)context.getBean("feepackageDao");
		taskQueueDaemonThread= (TaskQueueDaemonThread)context.getBean("taskQueueDaemonThread");
		this.orderNo = orderNo;
		this.feepackageId=feepackageId;
		this.leastPurchase=leastPurchase;
	}

	@Override
	public void run() {
		synchronized (lockObj) {
			logger.info("线程中 orderNo:"+orderNo);
		    feepackageDao.addStockCount(this.feepackageId,this.leastPurchase);
		}
	}


	@Override
	public String toString() {
		return "OrderTask [feepackageId=" + feepackageId+ ", leastPurchase=" + leastPurchase + ", orderNo=" + orderNo + "]";
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	

}
