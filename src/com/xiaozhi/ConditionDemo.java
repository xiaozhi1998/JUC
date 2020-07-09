package com.xiaozhi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
//打印a1-a4，b1-b5，c1-c6，重复四次
	public static void main(String[] args) {
		ShareResource shareResource = new ShareResource();
		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				shareResource.a();
			}
		},"a").start();
		
		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				shareResource.b();
			}
		},"b").start();
		
		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				shareResource.c();
			}
		},"c").start();
	}

}

class ShareResource{
	private int number = 1;
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();
	
	public void a() {
		lock.lock();
		try {
			while(number != 1) {
				c1.await();
			}
			for (int i = 1; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}			
			number = 2;
			c2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}		
	}
	
	public void b() {
		lock.lock();
		try {
			while(number != 2) {
				c2.await();
			}
			for (int i = 1; i < 6; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			number = 3;
			c3.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}		
	}
	
	public void c() {
		lock.lock();
		try {
			while(number != 3) {
				c3.await();
			}
			for (int i = 1; i < 7; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			number = 1;
			c1.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}		
	}
}