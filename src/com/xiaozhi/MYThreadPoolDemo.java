package com.xiaozhi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MYThreadPoolDemo {

	public static void main(String[] args) {
		//һ�����
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		
		//һ��һ��
		//ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
		
		//N���������ܾ���
		//ExecutorService threadPool3 = Executors.newCachedThreadPool();
		
//		ExecutorService threadPool5 = new ThreadPoolExecutor(
//				2,                                     //��פ�����߳���
//				5,                                     //���ͬʱִ���߳���
//				100L,                                  //��������̴߳��ʱ��
//				TimeUnit.SECONDS,                      //ʱ�䵥λ
//				new LinkedBlockingQueue<>(3),          //�Ⱥ���
//				Executors.defaultThreadFactory(),      //�̹߳���
//				new ThreadPoolExecutor.AbortPolicy()); //��̭(�ܾ�)���ԣ����֣�
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
