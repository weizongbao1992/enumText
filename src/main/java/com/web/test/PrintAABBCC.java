package com.web.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareR{

	private int number=1;
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();

	public void print5() {
		lock.lock();
		try {
			while(number != 1) {
				try {
					condition1.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int j = 0; j < 5; j++) {
				System.out.println(Thread.currentThread().getName()+"打印了AA");
			}
			number =2;
			condition2.signal();
		} finally {
			lock.unlock();
		}

	}
	public void print10() {
		lock.lock();
		try {
			while(number != 2) {
				try {
					condition2.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int j = 0; j < 10; j++) {
				System.out.println(Thread.currentThread().getName()+"打印了BB");
			}
			number =3;
			condition3.signal();
		} finally {
			lock.unlock();
		}

	}
	public void print15() {
		lock.lock();
		try {
			while(number != 3) {
				try {
					condition3.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int j = 0; j < 15; j++) {
				System.out.println(Thread.currentThread().getName()+"打印了CC");
			}
			number =1;
			condition1.signal();
		} finally {
			lock.unlock();
		}

	}

}
public class PrintAABBCC {
	private static ShareR p = new ShareR();
	public static void main(String[] args) {

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				p.print5();
			}
		},"A").start();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				p.print10();
			}
		},"B").start();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				p.print15();
			}
		},"C").start();

	}

}
