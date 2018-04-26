package com.forge.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.forge.bean.Forge_News;
import com.forge.dao.NewsDao;
import com.forge.dao.impl.NewsDaoImpl;
import com.forge.service.NewsService;

public class NewsServiceImpl implements NewsService {

	NewsDao dao = new NewsDaoImpl();  //实例化UserDao对象
	Logger logger = Logger.getLogger(UserServiceImpl.class);  //实例化日志对象，用于记录
	@Override
	public void add(Forge_News t) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Forge_News t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Forge_News> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Forge_News findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

}
