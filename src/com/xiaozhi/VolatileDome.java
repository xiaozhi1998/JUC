package com.xiaozhi;

import java.util.concurrent.TimeUnit;

public class VolatileDome {

	public static void main(String[] args) {
		MyData1 myData1 = new MyData1();
		MyData2 myData2 = new MyData2();
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName()+"\t Thread1 come in");
			//暂停3秒
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			myData1.setData();
		},"Thread1").start();
		while(myData1.a == 0) {
			
		}
		System.out.println(Thread.currentThread().getName()+"\t Thread1 over");
		
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName()+"\t Thread2 come in");
			//暂停3秒
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			myData2.setData();
		},"Thread2").start();
		while(myData2.a == 0) {
			
		}
		System.out.println(Thread.currentThread().getName()+"\t Thread2 over");
	}

}
//运行结果
//Thread1	 Thread1 come in
//3s后，获取到修改后的值，结束循环
//main	 Thread1 over
//Thread2	 Thread2 come in
//未获取到修改后的值，一直做死循环

class MyData1{
	//可见性
	volatile int a = 0;
	public void setData() {
		this.a = 10;
	}
}

class MyData2{
	int a = 0;
	public void setData() {
		this.a = 10;
	}
}