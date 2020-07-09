package com.xiaozhi;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(6);
		for (int i = 1; i <= 6; i++) {		
			new Thread(() -> {
				//做减法				
				System.out.println(Thread.currentThread().getName() + "\t 执行完成");
				countDownLatch.countDown();
			},String.valueOf(i)).start();
		}
		countDownLatch.await();
		System.out.println(Thread.currentThread().getName() + "\t 最后执行且完成");
	}
}
//运行结果：只有当执行六个线程之后才能执行（如果有十个线程不一定是第七个线程执行）
//1	 执行完成
//2	 执行完成
//3	 执行完成
//4	 执行完成
//5	 执行完成
//6	 执行完成
//main	 最后执行且完成