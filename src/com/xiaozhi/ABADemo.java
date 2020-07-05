package com.xiaozhi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

	static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100,1);
	
	public static void main(String[] args) {
		new Thread(() -> {
			int stamp = atomicStampedReference.getStamp();
			System.out.println(Thread.currentThread().getName()+"\t 当前版本号:" + stamp);
			//暂停1秒
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1);
			System.out.println(Thread.currentThread().getName()+"\t 当前版本号:" + atomicStampedReference.getStamp());
			atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
			System.out.println(Thread.currentThread().getName()+"\t 当前版本号:" + atomicStampedReference.getStamp());
		},"t1").start();
		new Thread(() -> {
			int stamp = atomicStampedReference.getStamp();
			System.out.println(Thread.currentThread().getName()+"\t 当前版本号:" + stamp);
			//暂停3秒
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean result = atomicStampedReference.compareAndSet(100, 2020, stamp, stamp + 1);
			System.out.println(Thread.currentThread().getName() + "\t 修改是否成功:" + result +"\t 当前版本号:" + atomicStampedReference.getStamp() + "\t 当前值:" + atomicStampedReference.getReference());
		},"t2").start();
	}

}
