package com.xiaozhi;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(6,() -> {
			System.out.println(Thread.currentThread().getName() + "\t ���ִ�������");
			});
		for (int i = 1; i <= 6; i++) {		
			new Thread(() -> {
				//���ӷ�
				System.out.println(Thread.currentThread().getName() + "\t ִ�����");
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
//���н����ֻ�е�ִ�������߳�֮�����ִ�У������ʮ���̲߳�һ���ǵ��߸��߳�ִ�У�
//1	 ִ�����
//4	 ִ�����
//2	 ִ�����
//3	 ִ�����
//5	 ִ�����
//6	 ִ�����
//4	 ���ִ�������
