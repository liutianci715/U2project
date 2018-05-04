package com.forge.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.forge.bean.Cart;
import com.forge.bean.Forge_Product;
import com.forge.bean.Forge_Product_Category;
import com.forge.dao.Product_CategoryDao;
import com.forge.dao.impl.Product_CategoryDaoImpl;
import com.forge.service.ProductService;
import com.forge.service.Product_CategoryService;

public class Product_CategoryServiceImpl implements Product_CategoryService {

	Product_CategoryDao dao = new Product_CategoryDaoImpl();
	
	@Override
	public void add(Forge_Product_Category t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Forge_Product_Category t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Forge_Product_Category> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Forge_Product_Category findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Forge_Product_Category> findByType(Serializable id) {
		List<Forge_Product_Category> cate = new ArrayList();
		cate = dao.findByType(id);
		return cate;
	}



}
