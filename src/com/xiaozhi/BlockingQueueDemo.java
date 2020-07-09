package com.xiaozhi;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
		BlockingQueue<String> blockingQueue1 = new ArrayBlockingQueue<>(3);
		BlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue<>(3);
		BlockingQueue<String> blockingQueue3 = new ArrayBlockingQueue<>(3);
		
		
		//直接抛异常
		System.out.println("----直接抛异常----");
		System.out.println(blockingQueue.add("a"));
		System.out.println(blockingQueue.add("b"));
		System.out.println(blockingQueue.add("c"));
		//System.out.println(blockingQueue.add("d"));
		//直接抛异常：java.lang.IllegalStateException: Queue full
		
		//检查是否为空，不为空输出首元素
		System.out.println(blockingQueue.element());
		
		System.out.println(blockingQueue.remove());//a
		System.out.println(blockingQueue.remove());//b
		System.out.println(blockingQueue.remove());//c
		
		//System.out.println(blockingQueue.element());
		//直接抛异常：java.util.NoSuchElementException
		
		//System.out.println(blockingQueue.remove());
		//直接抛异常：java.util.NoSuchElementException
		
		
		//不抛异常,返回false
		System.out.println("---不抛异常,返回false---");
		System.out.println(blockingQueue1.offer("a"));
		System.out.println(blockingQueue1.offer("b"));
		System.out.println(blockingQueue1.offer("c"));
		System.out.println(blockingQueue1.offer("d"));//false
		
		System.out.println(blockingQueue1.peek());
		
		System.out.println(blockingQueue1.poll());//a
		System.out.println(blockingQueue1.poll());//b
		System.out.println(blockingQueue1.poll());//c
		System.out.println(blockingQueue1.poll());//null
		
		System.out.println(blockingQueue1.peek());//null
		
		
		
		//阻塞一直等待
		System.out.println("------阻塞------");
		blockingQueue2.put("a");
		blockingQueue2.put("b");
		blockingQueue2.put("c");
		//System.out.println("=======1=========");
		//blockingQueue2.put("d");
		//会一直阻塞等待，不会向下执行
		//System.out.println("=======2=========");
		blockingQueue2.take();
		blockingQueue2.take();
		blockingQueue2.take();		
		//System.out.println("=======3=========");
		//blockingQueue2.take();
		//会一直阻塞等待，不会向下执行
		//System.out.println("=======4=========");				
		
		
		//超时设置
		System.out.println("------超时设置------");
		System.out.println(blockingQueue3.offer("a", 2L, TimeUnit.SECONDS));
		System.out.println(blockingQueue3.offer("b", 2L, TimeUnit.SECONDS));
		System.out.println(blockingQueue3.offer("c", 2L, TimeUnit.SECONDS));
		//两秒后继续往下执行
		System.out.println(blockingQueue3.offer("d", 2L, TimeUnit.SECONDS));//false
		
		System.out.println(blockingQueue3.poll(2L, TimeUnit.SECONDS));//a
		System.out.println(blockingQueue3.poll(2L, TimeUnit.SECONDS));//b
		System.out.println(blockingQueue3.poll(2L, TimeUnit.SECONDS));//c
		//两秒后继续往下执行
		System.out.println(blockingQueue3.poll(2L, TimeUnit.SECONDS));//null
	}
}
