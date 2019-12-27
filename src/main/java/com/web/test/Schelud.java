package com.web.test;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schelud {

	@Scheduled(cron = "0/1 * * * * *")
	public void test1() {
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				SingleTon.getInstance();
			},i+"").start();
		}
	}
}
