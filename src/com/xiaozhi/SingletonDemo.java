package com.xiaozhi;

public class SingletonDemo {
	private static volatile SingletonDemo instance = null;
	
	private SingletonDemo() {
		System.out.println(Thread.currentThread().getName()+"\t ���췽��SingletonDemo()");
	}
	//DCL��Double Check Lock ˫�˼������ƣ�
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
//����ģʽvolatile����
//���н����1	 ���췽��SingletonDemo()