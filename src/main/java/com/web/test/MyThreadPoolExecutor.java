package com.web.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolExecutor {

	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors()+"--------------");
		ExecutorService service = new ThreadPoolExecutor(
				2, 5, 1L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3),
				Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

//AbortPolicy() 请求数 > maximunPoolSize + queue.size(5+3) 就会抛出异常
//CallerRunsPolicy() 请求数 > maximunPoolSize + queue.size(5+3) 能办的就办，不能办的就回退给调用者（main）,main线程执行
//DiscardOldestPolicy() 请求数 > maximunPoolSize + queue.size(5+3) 抛弃队列中等待时间最久的任务，把当前任务加入到队列中
//DiscardPolicy() 请求数 > maximunPoolSize + queue.size(5+3) 直接丢弃任务，不予处理也不抛异常。如果允许任务丢失，这是最好的一种策略
		try {
			for (int i = 0; i < 10; i++) {
				service.execute(() -> {

					System.out.println(Thread.currentThread().getName()+"办理业务");

				});
			}
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				service.shutdown();
			}
	}
}
