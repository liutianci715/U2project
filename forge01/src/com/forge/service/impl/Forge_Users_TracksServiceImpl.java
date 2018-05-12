package com.forge.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.forge.bean.Forge_Product;
import com.forge.bean.Forge_Users_Tracks;
import com.forge.bean.UserTrack;
import com.forge.dao.Forge_Users_TracksDao;
import com.forge.dao.ProductDao;
import com.forge.dao.impl.Forge_Users_TracksDaoImpl;
import com.forge.dao.impl.ProductDaoImpl;
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

	@Override
	public String findTime(Serializable userId, Serializable productId) {
		String time = dao.findTime(userId, productId);
		return time;
	}

	@Override
	public List<UserTrack> queryTrack(int userId) {
		ProductDao pdao = new ProductDaoImpl();
		List<UserTrack> userTrack = new ArrayList();
		UserTrack a = null;
		Forge_Product product = null;
		
		List<Forge_Users_Tracks> tracks = dao.findByUserId(userId);
		for(int i=0;i<tracks.size();i++){
			product = pdao.findById(tracks.get(i).getProductId());
			a = new UserTrack();
			a.setProduct(product);
			a.setViewTime(tracks.get(i).getViewTime());
			a.setUserId(userId);
			userTrack.add(a);
		}
		return userTrack;
	}


}
