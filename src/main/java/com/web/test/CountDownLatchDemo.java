package com.web.test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

	private static final Integer COUNT=6;
	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(COUNT);
		for (int i = 1; i < 7; i++) {
			System.out.println(CountryDemo.get_CountryDemo(i));
			new Thread(new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName()+"国，被灭了=============");
					countDownLatch.countDown();

				}
			},CountryDemo.get_CountryDemo(i).getMessage1()).start();
		}

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("秦国统一天下=========");
	}
}
