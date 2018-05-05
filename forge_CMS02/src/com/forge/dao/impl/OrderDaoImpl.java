package com.forge.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.forge.bean.Forge_Order;
import com.forge.bean.Forge_Users;
import com.forge.dao.OrderDao;
import com.forge.service.impl.UserServiceImpl;
import com.forge.util.ResultSetUtil;
import com.forge.util.jdbcUtil;

public class OrderDaoImpl extends jdbcUtil implements OrderDao {

	@Override
	public int add(Forge_Order t) {
		return 0;
	}

	@Override
	public int delete(Serializable id) {
		String sql = "delete from forge_order where id=?";
		Object []param = {id};
		int rowsNum = 0;
		try {
			rowsNum = myExcuteUpdate(sql, param);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsNum;
	
	}

	@Override
	public int update(Forge_Order t) {
		String sql = "update forge_order set userAddress=?,createTime=?,cost=?,type=?,serialNumber=? where id=?";
		Object []param = {t.getUserAddress(),t.getCreateTime(),t.getCost(),t.getType(),t.getSerialNumber(),t.getId()}; 
		int rowsNum = 0;
		try {
			rowsNum =myExcuteUpdate(sql, param);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsNum;
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
