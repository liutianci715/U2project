package com.forge.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.forge.bean.Forge_Order;
import com.forge.bean.Forge_Users;
import com.forge.dao.OrderDao;
import com.forge.util.ResultSetUtil;
import com.forge.util.jdbcUtil;

public class OrderDaoImpl extends jdbcUtil implements OrderDao {

	@Override
	public int add(Forge_Order t) {
		return 0;
	}

	@Override
	public int delete(Serializable id) {
		return 0;
	}

	@Override
	public int update(Forge_Order t) {
		return 0;
	}

	@Override
	public List<Forge_Order> findAll() {
		String sql = "select * from forge_order";
		List<Forge_Order> orders = new ArrayList();
		try {
			rs = myExcuteQuery(sql);
			orders = ResultSetUtil.findAll(rs, Forge_Order.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnections();
		}
		return orders;
	}

	@Override
	public Forge_Order findById(Serializable id) {
		String sql = "select * from forge_order where id =?";
		Object []param = {id};
		Forge_Order order = null;
		try {
			rs = myExcuteQuery(sql,param);
			order = ResultSetUtil.findById(rs, Forge_Order.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnections();
		}
		return order;
	}

	@Override
	public int getTotalCount() {
		return 0;
	}

	@Override
	public List<Forge_Order> findAlls(int pageNum, int pageSize) {
		return null;
	}

}
