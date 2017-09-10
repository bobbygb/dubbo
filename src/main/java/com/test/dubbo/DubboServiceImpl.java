package com.test.dubbo;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class DubboServiceImpl implements IDubboService {

	public void sayHello(){
		System.out.println("hello,world!");
	}
}
