package com.web.test;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () ->{System.out.println("召集了7棵龙珠，召唤神龙");}) ;

		for (int i = 1; i < 8; i++) {
			final int num =i;
			new Thread(() ->{
				System.out.println("召集了第"+num+"棵龙珠");
				try {
					Thread.sleep(1000);
					cyclicBarrier.await();
				} catch (Exception e) {

					e.printStackTrace();
				}
			},String.valueOf(num)).start();
		}
	}

}
