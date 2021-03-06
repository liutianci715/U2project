package com.forge.service;

import java.util.List;

import com.forge.bean.Forge_Users;
import com.forge.bean.region;



public interface UserService extends BaseService<Forge_Users> {

	/**
	 * 登录
	 * @param loginName  用户名
	 * @param password   密码
	 * @return  登录的用户
	 */
	Forge_Users login(String userName,String Password);
	Forge_Users findByName(String loginName);
	List<region> findAddress(String parentId);

}
