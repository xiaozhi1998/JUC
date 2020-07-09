package com.xiaozhi;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(6,() -> {
			System.out.println(Thread.currentThread().getName() + "\t 最后执行且完成");
			});
		for (int i = 1; i <= 6; i++) {		
			new Thread(() -> {
				//做加法
				System.out.println(Thread.currentThread().getName() + "\t 执行完成");
				try {
					cyclicBarrier.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
			},String.valueOf(i)).start();
		}
	}
}
//运行结果：只有当执行六个线程之后才能执行（如果有十个线程不一定是第七个线程执行）
//1	 执行完成
//4	 执行完成
//2	 执行完成
//3	 执行完成
//5	 执行完成
//6	 执行完成
//4	 最后执行且完成
