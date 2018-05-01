package com.forge.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.forge.bean.Forge_Users;
import com.forge.dao.UserDao;
import com.forge.dao.impl.UserDaoImpl;
import com.forge.service.UserService;
import com.forge.util.PageInfo;




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
		int rowNum = 0;
		rowNum = dao.delete(id);
		if(rowNum>0){
			logger.info("删除用户成功！");
		}else{
			logger.info("删除用户失败！");
		}
	}

	@Override
	public void update(Forge_Users t) {
		int rowNum = 0;
		rowNum = dao.update(t);
		if(rowNum>0){
			logger.info("用户信息修改成功！");
		}else{
			logger.info("用户信息修改失败！");
		}
	}

	@Override
	public List<Forge_Users> findAll() {
		List<Forge_Users> users = new ArrayList();
		users = dao.findAll();
		return users;
	}

	@Override
	public Forge_Users findById(Serializable id) {
		Forge_Users user = null; 
		user = dao.findById(id);
		if(user!=null){
			logger.info("查询用户成功！");
		}else{
			logger.info("查询用户失败！");
		}
		return user;
	}

	@Override
	public Forge_Users login(String userName, String Password) {
		System.out.println("impl================"+userName+Password);
		Forge_Users user = null; 
		user = dao.login(userName, Password);
		return user;
	}

	/**
	 * 查询所有用户信息
	 */
	@Override
	public PageInfo<Forge_Users> findAlls(int pageNum, int pageSize) {
		PageInfo<Forge_Users> users = new PageInfo();
		//给pageInfo对象的list集合赋值
		users.setList(dao.findAlls(pageNum, pageSize));
		return users;
	}

	@Override
	public int getTotalCount() {
		int count = 0;
		count = dao.getTotalCount();
		return count;
	}

	
	
	
	
	
	
	
}
