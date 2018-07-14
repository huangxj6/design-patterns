package com.hxj.代理模式.静态代理;

public class Test {

	public static void main(String[] args) {
		
		// 中介开张
		中介代理 中介 = new 中介代理();
		
		// 开张后找房东加盟
		中介.新增房东(new 房东1());
		中介.新增房东(new 房东2());
		
		// 租客1来找中介租房
		try {
			中介.房屋出租(1500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 租客2来找中介租房
		try {
			中介.房屋出租(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
