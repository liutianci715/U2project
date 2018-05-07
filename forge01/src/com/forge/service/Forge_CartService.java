package com.forge.service;

import java.io.Serializable;
import java.util.List;

import com.forge.bean.Forge_Cart;

public interface Forge_CartService extends BaseService<Forge_Cart> {
	List<Forge_Cart> findByUserId(Serializable id);

}
