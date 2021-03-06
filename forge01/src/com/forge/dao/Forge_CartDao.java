package com.forge.dao;

import java.io.Serializable;
import java.util.List;

import com.forge.bean.CartItem;
import com.forge.bean.Forge_Cart;


public interface Forge_CartDao extends BaseDao<Forge_Cart> {
	List<Forge_Cart> findByUserId(Serializable id);

	void addProduct(Serializable userId, CartItem mitem);

	void updateProduct(Serializable userId, CartItem uitem);

	void delDbData();

	void clear(int userId);
}
