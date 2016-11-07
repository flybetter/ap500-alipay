package org.alipay.queue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.alipay.entity.thread.OrderTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("taskQueueDaemonThread")
public class TaskQueueDaemonThread {

	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static Map<String,Object> orderMap=new ConcurrentHashMap<String,Object>();
	
	public static Map<String,Object> map=new HashMap<String,Object>();
	
    private TaskQueueDaemonThread() {
    }

    private static class LazyHolder {
        private static TaskQueueDaemonThread taskQueueDaemonThread = new TaskQueueDaemonThread();
    }

    public static TaskQueueDaemonThread getInstance() {
        return LazyHolder.taskQueueDaemonThread;
    }

    Executor executor = Executors.newFixedThreadPool(20);
    /**
     * 守护线程
     */
    private Thread daemonThread;

    /**
     * 初始化守护线程
     */
    @PostConstruct
    public void init() {
        daemonThread = new Thread(() -> execute());
        daemonThread.setDaemon(true);
        daemonThread.setName("Task Queue Daemon Thread");
        daemonThread.start();
    }

    private void execute() {
    	logger.info("taskQueueDaemonThread start:" + System.currentTimeMillis());
        while (true) {
            try {
                //从延迟队列中取值,如果没有对象过期则队列一直等待，
                Task t1 = t.take();
                if (t1 != null) {
                    //修改问题的状态
                    Runnable task = t1.getTask();
                    if (task == null) {
                        continue;
                    }
                    executor.execute(task);
                    logger.info("[at task:" + task + "]   [Time:" + System.currentTimeMillis() + "]");
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * 创建一个最初为空的新 DelayQueue
     */
    private DelayQueue<Task> t = new DelayQueue<>();

    /**
     * 添加任务，
     * time 延迟时间
     * task 任务
     * 用户为问题设置延迟时间
     */
    public void put(long time, Runnable task) {
    	logger.info("TaskQueueDaemonThread put:"+task);
        //转换成ns
        long nanoTime = TimeUnit.NANOSECONDS.convert(time, TimeUnit.MILLISECONDS);
        //创建一个任务
        Task k = new Task(nanoTime, task);
    	OrderTask orderTask=(OrderTask)task;
    	orderMap.put(orderTask.getOrderNo(), k);
        //将任务放在延迟的队列中
        t.put(k);
    }

    /**
     * 结束订单
     * @param task
     */
    public boolean endTask(String orderNo){
    	Task task=(Task)orderMap.get(orderNo);
    	logger.info("TaskQueueDaemonThread remove:"+task);
    	orderMap.remove(orderNo);
        return t.remove(task);
    }
   

}
