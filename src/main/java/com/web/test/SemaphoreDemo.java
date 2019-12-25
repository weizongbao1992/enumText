package com.web.test;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);
		for (int i = 1; i <8; i++) {
			final int num=i;
			new Thread(() -> {
				try{
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName()+"车抢到了车位");
					Thread.sleep(num*1000);
					System.out.println(Thread.currentThread().getName()+"车停了"+num+"s之后离开车位");
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					semaphore.release();
				}

			},String.valueOf(i)).start();
		}
	}
}
