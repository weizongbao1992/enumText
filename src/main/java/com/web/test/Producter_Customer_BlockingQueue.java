package com.web.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Resource_{
	private AtomicInteger atomicInteger = new AtomicInteger();
	private BlockingQueue<String> blockingQueue = null;
	private boolean flag=true;
	public Resource_(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	public void prod() throws Exception{

		String data = null;
		while(flag) {
			data= atomicInteger.incrementAndGet()+"";
			boolean offer = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
			if(offer) {
				System.out.println(Thread.currentThread().getName()+"添加数据"+data+"成功");
			}else {
				System.out.println(Thread.currentThread().getName()+"添加数据"+data+"失败");
			}
			Thread.sleep(1000);
		}
		System.out.println(Thread.currentThread().getName()+"大boss叫停了，flag=false了");
	}
	public void custom() throws Exception{

		String result = null;
		while(flag) {
			result = blockingQueue.poll(2, TimeUnit.SECONDS);
			if(result == null || result.equalsIgnoreCase("")) {
				flag=false;
				System.out.println(Thread.currentThread().getName()+"超过2s没有消费的数据，消费失败");
				break;
			}
			System.out.println(Thread.currentThread().getName()+"消费数据"+result+"成功");
		}
	}
	public void stop() {
		flag = false;
	}
}
public class Producter_Customer_BlockingQueue {
	public static void main(String[] args) {
		Resource_ resource_ = new Resource_(new ArrayBlockingQueue<>(10));

		new Thread(() -> {
			System.out.println("开始生产数据=====");
			try {
				resource_.prod();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		},"AA").start();
		new Thread(() -> {
			System.out.println("开始消费数据=====");
			try {
				resource_.custom();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		},"BB").start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resource_.stop();
		System.out.println("5s之后，大boss关闭了程序");
	}
}
