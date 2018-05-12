package com.forge.service;

import java.io.Serializable;
import java.util.List;

import com.forge.bean.Forge_Product;
import com.forge.bean.Forge_Users_Tracks;

public interface Forge_Users_TracksService extends BaseService<Forge_Users_Tracks>{

	//增加足迹
	void addTrack(int userId, String id);

	List<Forge_Product> findAll(Serializable userId);

	void update(Serializable userId, String productId);
	String findTime(Serializable userId, Serializable productId);

}
