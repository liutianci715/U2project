package com.forge.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.forge.bean.Forge_News;
import com.forge.dao.NewsDao;
import com.forge.util.jdbcUtil;

public class NewsDaoImpl extends jdbcUtil implements NewsDao {

	@Override
	public int add(Forge_News t) {
		String sql = "INSERT INTO forge_news(title,content,createTime,img) VALUES (?,?,?,?)";
		Object []param = {t.getTitle(),t.getContent(),t.getCreateTime(),t.getImg()};
		int rowNum = 0;
		try {
			rowNum = myExcuteUpdate(sql, param);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowNum;
	}

	@Override
	public int delete(Serializable id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Forge_News t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Forge_News> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Forge_News findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Forge_News> findAlls(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
