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
import com.googlecode.jtype.Types;

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
		return dao.findAll();
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

	@Override
	public List<Forge_Product_Category> findType2(Serializable parentId) {
		List<Forge_Product_Category> type2 = new ArrayList();
		type2 = dao.findType2(parentId);
		return type2;
	}

	@Override
	public List<Forge_Product_Category> findType3() {
		System.out.println("===================进入了findType3================");
		List<Forge_Product_Category> type3 = new ArrayList();
		type3 = dao.findType3();
		for(int i=0;i<type3.size();i++){
			System.out.println(type3.get(i).getName());
		}
		return type3;
	}



}
