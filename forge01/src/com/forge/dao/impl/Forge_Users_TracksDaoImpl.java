package com.forge.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.forge.bean.Forge_Product;
import com.forge.bean.Forge_Users_Tracks;
import com.forge.dao.Forge_Users_TracksDao;
import com.forge.dao.ProductDao;
import com.forge.util.ResultSetUtil;
import com.forge.util.jdbcUtil;

public class Forge_Users_TracksDaoImpl extends jdbcUtil implements
		Forge_Users_TracksDao {

	@Override
	public int add(Forge_Users_Tracks t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Serializable id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Forge_Users_Tracks t) {
		// TODO Auto-generated method stub
		return 0;
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
	public void addTrack(int userId, String id) {
		String sql = "insert into forge_user_tracks value (?,?,?)";
		//获取用户的浏览时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		Object []param = {userId,id,date};
		try {
			myExcuteUpdate(sql, param);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	//查找浏览的所有商品
	public List<Forge_Product> findAll(Serializable userId) {
		ProductDao pdao = new ProductDaoImpl ();  //商品dao层实例，用于查询商品
		List<Forge_Product> products = new ArrayList();
		String sql = "SELECT * FROM`forge_user_tracks` WHERE userId = ?";
		Object []param = {userId};
		try {
			rs = myExcuteQuery(sql, param);
			//获取用户所有的
			List<Forge_Users_Tracks> tracks =  ResultSetUtil.findAll(rs ,Forge_Users_Tracks.class );
			//取出所有的商品Id
			for(int i = 0; i<tracks.size();i++){
				String productId = tracks.get(i).getProductId();
				Forge_Product product = pdao.findById(productId);
				products.add(product);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public void update(Serializable userId, String productId) {
				//获取用户的浏览时间
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				String date = df.format(new Date());// new Date()为获取当前系统时间
				String sql = "UPDATE forge_user_tracks SET viewTime=? WHERE userId=? AND productId=?";
				Object []param = {date,userId,productId};
				try {
					myExcuteUpdate(sql, param);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
	}

	
}
