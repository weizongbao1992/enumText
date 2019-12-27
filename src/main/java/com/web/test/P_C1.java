package com.web.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class GuiTai{
	private Integer num =0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public void increment() throws Exception{
		lock.lock();
		try {
			while(num >= 20) {
				condition.await();
			}
			num++;
			System.out.println(Thread.currentThread().getName()+"生产了"+num);
			Thread.sleep(20);
			condition.signalAll();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	public void decrement() throws Exception{
		lock.lock();
		try {
			while(num == 0) {
				condition.await();
			}
			Thread.sleep(880);
			System.out.println(Thread.currentThread().getName()+"消费了"+num);

			num--;
			condition.signalAll();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
public class P_C1 {
	public static void main(String[] args) {
		GuiTai guiTai = new GuiTai();
		new Thread(() ->{
			while(true) {
				try {
					guiTai.increment();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"AA").start();
		new Thread(() ->{
			while(true) {
				try {
					guiTai.decrement();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"BB").start();
	}
}
