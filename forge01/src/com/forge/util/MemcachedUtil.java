package com.forge.util;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class MemcachedUtil {

	//私有化本类变量
	private static MemcachedUtil util;
	private static MemcachedClient client;
	
	static{
		util = new MemcachedUtil();
		try {
			client = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//2.私有化构造方法
	private MemcachedUtil(){}
	
	//3.提供对外访问接口
	public static MemcachedClient getInstance(){
		return client;
	}
}
