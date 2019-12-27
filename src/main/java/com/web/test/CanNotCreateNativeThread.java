package com.web.test;

public class CanNotCreateNativeThread {

	public static void main(String[] args) {
		System.out.println("****************************");
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
