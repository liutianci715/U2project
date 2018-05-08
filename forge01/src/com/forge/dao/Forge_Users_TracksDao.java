package com.forge.dao;

import java.io.Serializable;
import java.util.List;

import com.forge.bean.Forge_Product;
import com.forge.bean.Forge_Users_Tracks;

public interface Forge_Users_TracksDao extends BaseDao<Forge_Users_Tracks> {

	void addTrack(int userId, String id);

	//查找浏览的所有商品
	List<Forge_Product> findAll(Serializable userId);

	void update(Serializable userId, String productId);
}
