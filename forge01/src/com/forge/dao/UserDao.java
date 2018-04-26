package com.forge.dao;

import com.forge.bean.Forge_Users;



public interface UserDao extends BaseDao<Forge_Users>{
	/**
	 * 登录
	 * @param loginName  用户名
	 * @param password   密码
	 * @return  登录的用户
	 */
	Forge_Users login(String userName,String Password);
}
