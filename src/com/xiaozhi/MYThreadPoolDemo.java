package com.xiaozhi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MYThreadPoolDemo {

	public static void main(String[] args) {
		//一池五个
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		
		//一池一个
		//ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
		
		//N个，由性能决定
		//ExecutorService threadPool3 = Executors.newCachedThreadPool();
		
//		ExecutorService threadPool5 = new ThreadPoolExecutor(
//				2,                                     //常驻核心线程数
//				5,                                     //最大同时执行线程数
//				100L,                                  //多余空闲线程存活时间
//				TimeUnit.SECONDS,                      //时间单位
//				new LinkedBlockingQueue<>(3),          //等候区
//				Executors.defaultThreadFactory(),      //线程工厂
//				new ThreadPoolExecutor.AbortPolicy()); //淘汰(拒绝)策略（四种）
//		                               CallerRunsPolicy
//		                               DiscardOldestPolicy
//		                               DiscardPolicy
	
		try {
			for (int i = 1; i < 10; i++) {
				threadPool.execute(() -> {
					System.out.println(Thread.currentThread().getName() + "\t OK");
				});		
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}
	}

}
