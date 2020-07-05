package com.xiaozhi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {

	//ԭ�������߳�
	AtomicReference<Thread> atomicReference = new AtomicReference<>();
	
	public void myLock() {
		Thread thread = Thread.currentThread();
		System.out.println(Thread.currentThread().getName() + "\t come in");
		while(!atomicReference.compareAndSet(null, thread)) {
			
		}
	}
	public void myUnLock() {
		Thread thread = Thread.currentThread();	
		atomicReference.compareAndSet(thread, null);
		System.out.println(Thread.currentThread().getName() + "\t myUnLock");
	}
	public static void main(String[] args) {
		SpinLockDemo spinLockDemo = new SpinLockDemo();
		
		new Thread(() -> {
			spinLockDemo.myLock();
			//��ͣ5��
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			spinLockDemo.myUnLock();
		},"t1").start();
		//��ͣ1��
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(() -> {
			spinLockDemo.myLock();
			spinLockDemo.myUnLock();			
		},"t2").start();
	}

}
//�����������н��
//t1	 come in
//t2	 come in
//t1	 myUnLock
//t2	 myUnLock
