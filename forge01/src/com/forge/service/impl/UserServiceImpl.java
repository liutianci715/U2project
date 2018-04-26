package com.forge.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.forge.bean.Forge_Users;
import com.forge.dao.UserDao;
import com.forge.dao.impl.UserDaoImpl;
import com.forge.service.UserService;




public class UserServiceImpl implements UserService {
	
	
	UserDao dao = new UserDaoImpl();  //实例化UserDao对象
	Logger logger = Logger.getLogger(UserServiceImpl.class);  //实例化日志对象，用于记录

	/**
	 * 注册新用户
	 */
	@Override
	public void add(Forge_Users t) {
		int rowNum = 0;
		rowNum = dao.add(t);
		if(rowNum>0){
			logger.info("新增用户成功！");
		}else{
			logger.info("新增用户失败！");
		}
	}

	@Override
	public void delete(Serializable id) {
		
	}

	@Override
	public void update(Forge_Users t) {
		
	}

	@Override
	public List<Forge_Users> findAll() {
		return null;
	}

	@Override
	public Forge_Users findById(Serializable id) {
		return null;
	}

	@Override
	public Forge_Users login(String userName, String Password) {
		Forge_Users user = null; 
		user = dao.login(userName, Password);
		return user;
	}

	
	
	
	
	
	
	
}
