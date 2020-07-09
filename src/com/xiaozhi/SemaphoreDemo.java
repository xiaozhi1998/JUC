package com.xiaozhi;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

	public static void main(String[] args) {
		//模拟三个车位
		Semaphore semaphore = new Semaphore(3);
		//模拟六部车
		for (int i = 1; i <= 6; i++) {		
			new Thread(() -> {
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "\t 3秒后离开车位");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					semaphore.release();
				}
			},String.valueOf(i)).start();
		}
	}
}
//运行结果：
//1	 抢到车位
//3	 抢到车位
//2	 抢到车位
//3	 3秒后离开车位
//2	 3秒后离开车位
//1	 3秒后离开车位
//5	 抢到车位
//4	 抢到车位
//6	 抢到车位
//4	 3秒后离开车位
//6	 3秒后离开车位
//5	 3秒后离开车位
