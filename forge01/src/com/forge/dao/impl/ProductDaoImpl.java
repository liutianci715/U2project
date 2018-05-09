package com.forge.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.forge.bean.Forge_Product;
import com.forge.dao.ProductDao;
import com.forge.util.ResultSetUtil;
import com.forge.util.jdbcUtil;

public class ProductDaoImpl extends jdbcUtil implements ProductDao {

	@Override
	public int add(Forge_Product t) {
		return 0;
	}

	@Override
	public int delete(Serializable id) {
		return 0;
	}

	@Override
	public int update(Forge_Product t) {
		return 0;
	}

	@Override
	public List<Forge_Product> findAll() {
		String sql = "select * from forge_product";
		List<Forge_Product> list = null;
		try {
			rs = myExcuteQuery(sql);
			list = ResultSetUtil.findAll(rs, Forge_Product.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Forge_Product findById(Serializable id) {
		String sql = "select * from forge_product where id=?";
		Object[] params = { id };
		Forge_Product product = null;
		try {
			rs = myExcuteQuery(sql, params);
			product = ResultSetUtil.findById(rs, Forge_Product.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public List<Forge_Product> findBooksAjax() {
		String sql="SELECT * FROM forge_product ";//WHERE  `name` LIKE '%' ? '%' 
		List<Forge_Product> list=null;
		try {
			rs=myExcuteQuery(sql);
			list=ResultSetUtil.findAll(rs, Forge_Product.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
