package com.xiaozhi;

public class SingletonDemo {
	private static volatile SingletonDemo instance = null;
	
	private SingletonDemo() {
		System.out.println(Thread.currentThread().getName()+"\t 构造方法SingletonDemo()");
	}
	//DCL（Double Check Lock 双端检锁机制）
	public static SingletonDemo getInstance() {
		if(instance == null) {
			synchronized(SingletonDemo.class) {
				if(instance == null) {
					instance = new SingletonDemo();
				}
			}
		}
		return instance;
	}
	
	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			new Thread(() -> {
					SingletonDemo.getInstance();		
			},String.valueOf(i)).start();
		}
	}
}
//单例模式volatile分析
//运行结果：1	 构造方法SingletonDemo()