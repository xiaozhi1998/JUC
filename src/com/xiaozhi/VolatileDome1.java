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
		//��ֻ�����߳�ʱ����ѭ��
		while(Thread.activeCount() > 2) {
			Thread.yield();
		}
		System.out.println("a����ֵ" + "\t" + myData.a);
		System.out.println("b����ֵ" + "\t" + myData.b);
	}

}
//���н����a����С��20000,b����Ϊ20000
//��Ϊa++�Ƿ�ԭ���Եģ������дֵ��ʧ�����
//������addData��������ǰ��synchronized��֤ԭ���ԣ����ǲ��Ƽ�
//�Ƽ�ʹ��JUC�µ�AtomicInteger
//volatile�ܽ᣺�ɼ��ԣ�����֤ԭ���ԣ���ָֹ������
class MyData{
	//����֤ԭ����
	volatile int a = 0;
	public void addData() {
		a++;
	}
	//��֤ԭ����
	AtomicInteger b = new AtomicInteger();
	public void addAtomic() {
		b.getAndIncrement();
	}
}
