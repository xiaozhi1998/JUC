package com.xiaozhi;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(6);
		for (int i = 1; i <= 6; i++) {		
			new Thread(() -> {
				//������				
				System.out.println(Thread.currentThread().getName() + "\t ִ�����");
				countDownLatch.countDown();
			},String.valueOf(i)).start();
		}
		countDownLatch.await();
		System.out.println(Thread.currentThread().getName() + "\t ���ִ�������");
	}
}
//���н����ֻ�е�ִ�������߳�֮�����ִ�У������ʮ���̲߳�һ���ǵ��߸��߳�ִ�У�
//1	 ִ�����
//2	 ִ�����
//3	 ִ�����
//4	 ִ�����
//5	 ִ�����
//6	 ִ�����
//main	 ���ִ�������