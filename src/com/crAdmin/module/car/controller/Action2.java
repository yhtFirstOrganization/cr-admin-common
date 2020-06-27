package com.crAdmin.module.car.controller;

import org.springframework.util.StringUtils;

public class Action2 {

	public static void main(String[] args) {
//		new thread().start();
//		new thread().start();

		thread2 thread2 = new thread2();
		new Thread(thread2).start();
		new Thread(thread2).start();
	}
}

class thread extends Thread {
	private volatile String name;

	
	
	@Override
	public void run() {
		if (StringUtils.isEmpty(name)) {
			name = Thread.currentThread().getId() + " ";
		}
		System.out.println(name);
	}
}

class thread2 implements Runnable {
	private String name;
	@Override
	public void run() {
		if (StringUtils.isEmpty(name)) {
			name = Thread.currentThread().getId() + " ";
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name);
	}
}
