package com.hxj.代理模式.静态代理;

import java.util.ArrayList;
import java.util.List;

// 由于中介是一个共享对象，多线程情况下需要考虑同步问题，此处仅为单线程下的案例
public class 中介代理 implements 房屋出租接口 {

	private List<房屋出租接口> 所代理的房东列表 = new ArrayList<>();

	// 对于中介来说，他的最低要求就是所有房东中的租金中取最小值
	private Integer 本中介最低的租金;

	// 可以调用该方法把房东交给中介做代理
	public void 新增房东(房屋出租接口 房东) {

		// 首位加盟的房东，他的租金就是本中介的最低租金
		if(所代理的房东列表.isEmpty()) {
			本中介最低的租金 = 房东.最低租金要求();
		}
		
		// 后面每新增一个房东，都要检查他要求的租金是不是本中介最低的，是的话则需要更新该值
		else if (房东.最低租金要求() < 本中介最低的租金) {
			本中介最低的租金 = 房东.最低租金要求();
		}
		
		// 将房东假如到房东列表中
		所代理的房东列表.add(房东);
	}

	@Override
	public void 房屋出租(Integer 租金) throws Exception {

		// 先过滤掉不满足要求的租客
		if (租金 < 最低租金要求()) {
			throw new Exception("您的租金(" + 租金 + ")不够来我这租房！");
		}

		// 再匹配房东
		for (房屋出租接口 房东 : 所代理的房东列表) {

			if (租金 >= 房东.最低租金要求()) {
				房东.房屋出租(租金);
			}
		}
	}

	@Override
	public Integer 最低租金要求() {
		return 本中介最低的租金;
	}
}
