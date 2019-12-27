package com.web.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

class MyCall implements Callable<Integer>{
	private Integer num;

	public MyCall(Integer num) {
		super();
		this.num = num;
	}

	public Integer call() throws Exception {
		Thread.sleep(3000);
		return num*num;
	}

}
public class CallableDemo {
	private static final int NUM=8;
	public static void main(String[] args) {
		MyCall myCall = new MyCall(NUM);
		FutureTask<Integer> task = new FutureTask<>(myCall);
		/*
		 * FutureTask<Integer> task = new FutureTask<>(myCall);
		 *
		 * Thread thread = new Thread(task); thread.start(); try {
		 * System.out.println(task.get()); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (ExecutionException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 *
		 */
		ExecutorService executor =Executors.newScheduledThreadPool(10);
		executor.submit(task);
		List<Integer> list = new ArrayList<Integer>();
		executor.shutdown();
		System.out.println("==================开始================");
		try {
			System.out.println(task.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileInputStream fin = new FileInputStream("");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("==================最后================");
	}

}
