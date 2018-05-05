package com.forge.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.forge.bean.Forge_Order;
import com.forge.dao.OrderDao;
import com.forge.dao.impl.OrderDaoImpl;
import com.forge.service.OrderService;
import com.forge.util.PageInfo;

public class OrderServiceImpl implements OrderService {
	
	Logger logger = Logger.getLogger(UserServiceImpl.class);  //实例化日志对象，用于记录
	OrderDao dao = new OrderDaoImpl(); 
	@Override
	public void add(Forge_Order t) {
		
	}

	@Override
	public void delete(Serializable id) {
		int rowNum = 0;
		rowNum = dao.delete(id);
		if(rowNum>0){
			logger.info("删除订单成功！");
		}else{
			logger.info("删除订单失败！");
		}
	}

	@Override
	public void update(Forge_Order t) {
		
		int rowNum = 0;
		rowNum = dao.update(t);
		if(rowNum>0){
			logger.info("修改订单成功！");
		}else{
			logger.info("修改订单失败！");
		}
	}

	@Override
	public List<Forge_Order> findAll() {
		List<Forge_Order> orders = new ArrayList();
		orders = dao.findAll();
		return orders;
	}

	@Override
	public Forge_Order findById(Serializable id) {
		Forge_Order order = null;
		order = dao.findById(id);
		return order;
	}

	@Override
	public PageInfo<Forge_Order> findAlls(int pageNum, int pageSize) {
		return null;
	}

	@Override
	public int getTotalCount() {
		return 0;
	}

}
