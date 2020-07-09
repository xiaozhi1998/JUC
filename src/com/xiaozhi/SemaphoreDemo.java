package com.xiaozhi;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

	public static void main(String[] args) {
		//ģ��������λ
		Semaphore semaphore = new Semaphore(3);
		//ģ��������
		for (int i = 1; i <= 6; i++) {		
			new Thread(() -> {
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + "\t ������λ");
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "\t 3����뿪��λ");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					semaphore.release();
				}
			},String.valueOf(i)).start();
		}
	}
}
//���н����
//1	 ������λ
//3	 ������λ
//2	 ������λ
//3	 3����뿪��λ
//2	 3����뿪��λ
//1	 3����뿪��λ
//5	 ������λ
//4	 ������λ
//6	 ������λ
//4	 3����뿪��λ
//6	 3����뿪��λ
//5	 3����뿪��λ
