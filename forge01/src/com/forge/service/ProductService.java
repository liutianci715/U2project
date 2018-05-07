package com.forge.service;

import com.forge.bean.Cart;
import com.forge.bean.Forge_Product;

public interface ProductService extends BaseService<Forge_Product> {
	
	void addCart(String id, Cart cart,int num);

	void del(String id, Cart cart);
	
}
