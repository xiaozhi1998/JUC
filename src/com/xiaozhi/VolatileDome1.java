package com.xiaozhi;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDome1 {

	public static void main(String[] args) {
		MyData myData = new MyData();
		for (int i = 1; i <= 20; i++) {
			new Thread(() -> {
				for (int j = 0; j < 1000; j++) {
					myData.addData();
					myData.addAtomic();
				}			
			},String.valueOf(i)).start();
		}
		//当只有主线程时结束循环
		while(Thread.activeCount() > 2) {
			Thread.yield();
		}
		System.out.println("a最终值" + "\t" + myData.a);
		System.out.println("b最终值" + "\t" + myData.b);
	}

}
//运行结果：a总是小于20000,b总是为20000
//因为a++是非原子性的，会出现写值丢失的情况
//可以在addData（）方法前加synchronized保证原子性，但是不推荐
//推荐使用JUC下的AtomicInteger
//volatile总结：可见性，不保证原子性，禁止指令重排
class MyData{
	//不保证原子性
	volatile int a = 0;
	public void addData() {
		a++;
	}
	//保证原子性
	AtomicInteger b = new AtomicInteger();
	public void addAtomic() {
		b.getAndIncrement();
	}
}
