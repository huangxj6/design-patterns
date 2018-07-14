package com.hxj.代理模式.jdk动态代理;

public class Test {

	public static void main(String[] args) {
		
		// 中介开张
		中介代理 中介 = new 中介代理();
		
		// 开张后找房东加盟
		中介.新增房东(new 房东1());
		中介.新增房东(new 房东2());
		
		// 在动态代理中，中介无需自己实现房屋出租接口，可通过直接动态生成一个该接口的子类对象的方式返回给调用方调用
		// 相对于静态代理，好处就是假如后期我在房屋出租接口中加方法是，无需修改到中介代理类
		房屋出租接口 出租接口 = 中介.获取租房接口();
		
		// 租客1来找中介租房
		try {
			出租接口.房屋出租(1500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 租客2来找中介租房
		try {
			出租接口.房屋出租(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
