package com.forge.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.forge.bean.Forge_Product_Category;
import com.forge.dao.Product_CategoryDao;
import com.forge.util.ResultSetUtil;
import com.forge.util.jdbcUtil;

public class Product_CategoryDaoImpl extends jdbcUtil implements Product_CategoryDao{

	@Override
	public int add(Forge_Product_Category t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Serializable id) {
		return 0;
	}

	@Override
	public int update(Forge_Product_Category t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Forge_Product_Category> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Forge_Product_Category findById(Serializable id) {
		return null;
	}
	
	@Override
	public List<Forge_Product_Category> findByType(Serializable id) {
		String sql = "select * from forge_product_category where type=?";
		Object []param = {id};
		List<Forge_Product_Category> cate = new ArrayList();
		try {
			rs = myExcuteQuery(sql, param);
			cate = ResultSetUtil.findAll(rs, Forge_Product_Category.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cate;
	}
}
