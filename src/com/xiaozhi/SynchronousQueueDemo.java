package com.xiaozhi;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {

	public static void main(String[] args) {
		BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
		new Thread(() -> {
			try {
				System.out.println(Thread.currentThread().getName()+"\t put a");
			    blockingQueue.put("a");
			    
			    System.out.println(Thread.currentThread().getName()+"\t put b");
			    blockingQueue.put("b");
			    
			    System.out.println(Thread.currentThread().getName()+"\t put c");
			    blockingQueue.put("c");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"t1").start();
		
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(3);
				System.out.println(Thread.currentThread().getName()+"\t take a");
			    blockingQueue.take();
			    
			    TimeUnit.SECONDS.sleep(3);
			    System.out.println(Thread.currentThread().getName()+"\t take b");
			    blockingQueue.take();
			    
			    TimeUnit.SECONDS.sleep(3);
			    System.out.println(Thread.currentThread().getName()+"\t take c");
			    blockingQueue.take();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"t2").start();
	}
}
//运行结果：移除一个生产一个，保证只要一个
//t1	 put a
//t2	 take a
//t1	 put b
//t2	 take b
//t1	 put c
//t2	 take c
