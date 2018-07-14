package com.hxj.代理模式.jdk动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// 由于中介是一个共享对象，多线程情况下需要考虑同步问题，此处仅为单线程下的案例
public class 中介代理 implements InvocationHandler {

	private List<房屋出租接口> 所代理的房东列表 = new ArrayList<>();

	// 可以调用该方法把房东交给中介做代理
	public void 新增房东(房屋出租接口 房东) {
		
		// 将房东假如到房东列表中
		所代理的房东列表.add(房东);
		
		// 对房东列表进行排序
		Collections.sort(所代理的房东列表, new Comparator<房屋出租接口>() {

			@Override
			public int compare(房屋出租接口 o1, 房屋出租接口 o2) {
				return o1.最低租金要求().compareTo(o2.最低租金要求());
			}
		});
	}

	// 租客要先通过中介获得一个租房接口，然后再调该接口去租房
	public 房屋出租接口 获取租房接口() {

		Class<房屋出租接口> clazz = 房屋出租接口.class;
		return (房屋出租接口) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		// 先获取租客的租金
		Integer 租金 = (Integer) args[0];
		
		// 过滤掉不满足要求的租客
//		if (租金 < 本中介最低的租金) {
//			throw new Exception("您的租金(" + 租金 + ")不够来我这租房！");
//		}

		// 再匹配房东
		for (房屋出租接口 房东 : 所代理的房东列表) {

			if (租金 >= 房东.最低租金要求()) {
				method.invoke(房东, args);
			}
		}

		return null;
	}
}
