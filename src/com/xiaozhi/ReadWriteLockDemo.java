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

//资源类
class MyCache{
	private volatile Map<String,Object> map = new HashMap<>();
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	public void put(String key,Object value) {
		rwLock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\t 正在写入:" + key);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(key, value);
			System.out.println(Thread.currentThread().getName() + "\t 写入完成!");
		} finally {
			rwLock.writeLock().unlock();
		}		
	}
	
	public void get(String key) {
		rwLock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\t 正在读取...");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Object result = map.get(key);
			System.out.println(Thread.currentThread().getName() + "\t 读取完成:" + result);
		} finally {
			rwLock.readLock().unlock();
		}	
	}
}
//读写锁，运行结果：
//1	 正在写入:1
//1	 写入完成!
//3	 正在写入:3
//3	 写入完成!
//2	 正在写入:2
//2	 写入完成!
//4	 正在写入:4
//4	 写入完成!
//5	 正在写入:5
//5	 写入完成!
//1	 正在读取...
//2	 正在读取...
//4	 正在读取...
//3	 正在读取...
//5	 正在读取...
//1	 读取完成:1
//5	 读取完成:5
//2	 读取完成:2
//4	 读取完成:4
//3	 读取完成:3
