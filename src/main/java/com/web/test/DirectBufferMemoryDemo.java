package com.web.test;

import java.nio.ByteBuffer;

public class DirectBufferMemoryDemo {

	public static void main(String[] args) {
		System.out.println("直接内存大小:"+(sun.misc.VM.maxDirectMemory()/1024/1024) +"M");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ByteBuffer b = ByteBuffer.allocateDirect(6*1024*1024);
	}

}
