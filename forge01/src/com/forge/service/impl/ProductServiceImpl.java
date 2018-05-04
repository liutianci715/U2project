package com.forge.service.impl;

import java.io.Serializable;
import java.util.List;

import com.forge.bean.Cart;
import com.forge.bean.Forge_Product;
import com.forge.dao.ProductDao;
import com.forge.dao.impl.ProductDaoImpl;
import com.forge.service.ProductService;


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
	public void addCart(String id, Cart cart) {
		//�����ݿ��л�ȡ��Ʒ
		Forge_Product product = dao.findById(id);
		//����Ʒ�Ž����ﳵ
		cart.addProduct(product);
	}

	@Override
	public void del(String id, Cart cart) {
		cart.getMap().remove(id);
	}

}
