package com.web.test;

class HoldLockDemo implements Runnable{

	private String lockA;
	private String lockB;



	public HoldLockDemo(String lockA, String lockB) {
		super();
		this.lockA = lockA;
		this.lockB = lockB;
	}



	@Override
	public void run() {
		synchronized (lockA) {
			System.out.println(Thread.currentThread().getName()+"自己持有:"+lockA+"，试图得到:"+lockB);

			synchronized (lockB) {
				System.out.println(Thread.currentThread().getName()+"自己持有:"+lockB+"，试图得到:"+lockA);
			}
		}

	}

}
public class DeathLockDemo {

	public static void main(String[] args) {
		String lockA = "lockA";
		String lockB = "lockB";
		new Thread(new HoldLockDemo(lockA, lockB),"ThreadAAAAAA").start();
		new Thread(new HoldLockDemo(lockB, lockA),"ThreadBBBBBB").start();
	}
}
