package com.hxj.代理模式.jdk动态代理;

public class 房东2 implements 房屋出租接口{

	@Override
	public void 房屋出租(Integer 租金) {
		
		System.out.println("我是房东2");
		
		System.out.println("1、收到租金" + 租金 + "元");
		
		System.out.println("2、签合同");
		
		System.out.println("3、交出钥匙");
	}

	@Override
	public Integer 最低租金要求() {
		return 2000;
	}
}
