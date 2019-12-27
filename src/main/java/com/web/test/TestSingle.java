package com.web.test;

public class TestSingle{
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				SingleTon.getInstance();
			},i+"").start();
		}
	}
}
class SingleTon {

	private static SingleTon instance = null;

	private SingleTon() {
		System.out.println(Thread.currentThread().getName()+"调用了构造方法");
	}

	public static SingleTon getInstance() {
		instance = new SingleTon();

		return instance;
	}
}

