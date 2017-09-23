package com.ldu.third.aop;

public class UserLog {
	public void beforeSave(){
		System.out.println("before");
	}
	public void afterSave(){
		System.out.println("after");
	}
}
