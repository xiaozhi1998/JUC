package com.xiaozhi;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

	public static void main(String[] args) {
		MyCache myCache = new MyCache();
		
		for (int i = 1; i <= 5; i++) {
			final int tempInt = i;
			new Thread(() -> {			
				myCache.put(tempInt + "", tempInt+ "");
			},String.valueOf(i)).start();
		}
		for (int i = 1; i <= 5; i++) {
			final int tempInt = i;
			new Thread(() -> {			
				myCache.get(tempInt + "");
			},String.valueOf(i)).start();
		}
	}

}

//��Դ��
class MyCache{
	private volatile Map<String,Object> map = new HashMap<>();
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	public void put(String key,Object value) {
		rwLock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\t ����д��:" + key);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(key, value);
			System.out.println(Thread.currentThread().getName() + "\t д�����!");
		} finally {
			rwLock.writeLock().unlock();
		}		
	}
	
	public void get(String key) {
		rwLock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\t ���ڶ�ȡ...");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Object result = map.get(key);
			System.out.println(Thread.currentThread().getName() + "\t ��ȡ���:" + result);
		} finally {
			rwLock.readLock().unlock();
		}	
	}
}
//��д�������н����
//1	 ����д��:1
//1	 д�����!
//3	 ����д��:3
//3	 д�����!
//2	 ����д��:2
//2	 д�����!
//4	 ����д��:4
//4	 д�����!
//5	 ����д��:5
//5	 д�����!
//1	 ���ڶ�ȡ...
//2	 ���ڶ�ȡ...
//4	 ���ڶ�ȡ...
//3	 ���ڶ�ȡ...
//5	 ���ڶ�ȡ...
//1	 ��ȡ���:1
//5	 ��ȡ���:5
//2	 ��ȡ���:2
//4	 ��ȡ���:4
//3	 ��ȡ���:3
