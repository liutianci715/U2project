package com.forge.service.impl;

import java.io.Serializable;
import java.util.List;

import com.forge.bean.Forge_Product;
import com.forge.bean.Forge_Users_Tracks;
import com.forge.dao.Forge_Users_TracksDao;
import com.forge.dao.impl.Forge_Users_TracksDaoImpl;
import com.forge.service.Forge_Users_TracksService;

public class Forge_Users_TracksServiceImpl implements Forge_Users_TracksService {

	Forge_Users_TracksDao dao = new Forge_Users_TracksDaoImpl();
	
	@Override
	public void add(Forge_Users_Tracks t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public List<Forge_Users_Tracks> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Forge_Users_Tracks findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//添加用户浏览记录
	public void addTrack(int userId, String id) {
		dao.addTrack(userId,id);
	}

	@Override 
	//查找浏览的所有商品
	public List<Forge_Product> findAll(Serializable userId) {
		List<Forge_Product> products = dao.findAll(userId);
		return products ;
	}

	@Override
	public void update(Forge_Users_Tracks t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Serializable userId, String productId) {
		dao.update(userId,productId);
	}


}
