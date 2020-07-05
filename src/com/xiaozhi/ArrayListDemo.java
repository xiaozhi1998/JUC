package com.xiaozhi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListDemo {

	public static void main(String[] args) {
		//不安全，报错java.util.ConcurrentModificationException
		//ArrayList<String> list1 = new ArrayList<String>();
		//安全
		Vector<String> list2 = new Vector<String>();
		List<String> list3 = Collections.synchronizedList(new ArrayList<String>());
		//Collections.synchronizedSet(),Collections.synchronizedMap()
		List<String> list4 = new CopyOnWriteArrayList<String>();		
		//CopyOnWriteArraySet<E>,ConcurrentHashMap<K, V>
		for (int i = 1; i <= 10; i++) {
			new Thread(() -> {
				//list1.add(UUID.randomUUID().toString().substring(0, 8));
				list2.add(UUID.randomUUID().toString().substring(0, 8));
				list3.add(UUID.randomUUID().toString().substring(0, 8));
				list4.add(UUID.randomUUID().toString().substring(0, 8));
			},String.valueOf(i)).start();
		}
		while(Thread.activeCount() > 2) {
			
		}
		//System.out.println(list1);
		System.out.println(list2);
		System.out.println(list3);
		System.out.println(list4);									
	}

}
