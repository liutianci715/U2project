package com.forge.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.forge.bean.CartItem;
import com.forge.bean.Forge_Cart;
import com.forge.dao.Forge_CartDao;
import com.forge.util.ResultSetUtil;
import com.forge.util.jdbcUtil;

public class Forge_CartDaoImpl extends jdbcUtil implements Forge_CartDao {

	@Override
	public int add(Forge_Cart t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Serializable id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Forge_Cart t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Forge_Cart> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Forge_Cart findById(Serializable id) {
		
		return null;
	}

	@Override
	public List<Forge_Cart> findByUserId(Serializable id) {
		String sql = "select * from forge_cart where userId = ?";
		Object []param = {id};
		List<Forge_Cart> cartList = null;
		try {
			rs = myExcuteQuery(sql, param);
			cartList = ResultSetUtil.findAll(rs, Forge_Cart.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartList;
	}

	@Override
	public void addProduct(Serializable userId, CartItem mitem) {
		String sql = "insert into forge_cart value(?,?,?,?) ";
		Object []param = {userId,mitem.getProduct().getId(),mitem.getNum(),mitem.getPrice()};
		try {
			myExcuteUpdate(sql, param);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateProduct(Serializable userId, CartItem uitem) {
		String sql = "update forge_cart set productNum=?,price=? where userId=?and productId=?";
		Object []param = {uitem.getNum(),uitem.getPrice(),userId,uitem.getProduct().getId()};
		try {
			myExcuteUpdate(sql, param);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delDbData() {
		String sql ="delete from forge_cart";
		try {
			myExcuteUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void clear(int userId) {
		String sql ="delete from forge_cart where userId=?";
		Object []param = {userId};
		try {
			myExcuteUpdate(sql,param);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
