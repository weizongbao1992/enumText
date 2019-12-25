package com.web.test;

public enum CountryDemo {

	One(1,"齐","小齐国"),
	Two(2,"楚","小楚国"),
	Three(3,"燕","小燕国"),
	Fore(4,"韩","小韩国"),
	Five(5,"赵","小赵国"),
	Sie(6,"魏","小魏国");

	private int code;
	private String message;
	private String message1;
	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public String getMessage1() {
		return message1;
	}
	private CountryDemo(Integer code, String message,String message1) {
		this.code = code;
		this.message = message;
		this.message1 = message1;
	}

	public static CountryDemo get_CountryDemo(int index) {
		CountryDemo[] countryDemos = CountryDemo.values();
		for (CountryDemo countryDemo : countryDemos) {
			if(index == countryDemo.getCode()) {
				return countryDemo;
			}
		}

		return null;
	}
}
