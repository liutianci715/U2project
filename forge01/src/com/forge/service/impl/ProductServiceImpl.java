package com.forge.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.forge.bean.Cart;
import com.forge.bean.Forge_Product;
import com.forge.dao.ProductDao;
import com.forge.dao.impl.ProductDaoImpl;
import com.forge.service.ProductService;
import com.forge.util.MemcachedUtil;
import com.google.gson.Gson;


public class ProductServiceImpl implements ProductService {

	ProductDao dao = new ProductDaoImpl();
	
	@Override
	public void add(Forge_Product t) {

	}

	@Override
	public void delete(Serializable id) {

	}

	@Override
	public void update(Forge_Product t) {

	}

	@Override
	public List<Forge_Product> findAll() {
		return dao.findAll();
	}

	@Override
	public Forge_Product findById(Serializable id) {
		return dao.findById(id);
	}

	@Override
	public void addCart(String id, Cart cart,int num) {
		//����ݿ��л�ȡ��Ʒ
		Forge_Product product = dao.findById(id);
		System.out.println("product++++++++++++"+product);
		System.out.println("num++++++++++++++"+num);
		System.out.println("cart++++++++++++++"+cart);
		//����Ʒ�Ž��
		cart.addProduct(product,num);
	
		
	}

	@Override
	public void del(String id, Cart cart) {
		cart.getMap().remove(id);
	}

	@Override
	public List<String> findBooksAjax(String name) {
		List<String> str=new ArrayList();
		List<Forge_Product> list=null;
		// 如果缓存中没有对象时，就进入数据库查询
		if (MemcachedUtil.getInstance().get("books") == null) {
			 list= dao.findBooksAjax();
			MemcachedUtil.getInstance().set("books", 1000, list);
		}
		list= (List<Forge_Product>) MemcachedUtil.getInstance().get("books");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().indexOf(name)!=-1) {
				str.add(list.get(i).getName());
			}
		}
		System.out.println("222"+str);
		return str;
	}


}
