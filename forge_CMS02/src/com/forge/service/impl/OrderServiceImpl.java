package com.forge.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.forge.bean.Forge_Order;
import com.forge.dao.OrderDao;
import com.forge.dao.impl.OrderDaoImpl;
import com.forge.service.OrderService;
import com.forge.util.PageInfo;

public class OrderServiceImpl implements OrderService {

	OrderDao dao = new OrderDaoImpl(); 
	@Override
	public void add(Forge_Order t) {
		
	}

	@Override
	public void delete(Serializable id) {
		
	}

	@Override
	public void update(Forge_Order t) {
		
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
