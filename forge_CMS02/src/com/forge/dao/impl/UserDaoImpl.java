package com.forge.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.forge.bean.Forge_Users;
import com.forge.dao.UserDao;
import com.forge.util.ResultSetUtil;
import com.forge.util.jdbcUtil;



public class UserDaoImpl extends jdbcUtil implements UserDao {

	/**
	 * 注册新用户
	 * 需要输入登录名和密码
	 */
	@Override
	public int add(Forge_Users t) {
		String sql = "INSERT INTO forge_users(loginName,`password`) VALUES (?,?)";
		Object []param = {t.getLoginName(),t.getPassword()};
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
		return 0;
	}

	@Override
	public int update(Forge_Users t) {
		return 0;
	}

	@Override
	public List<Forge_Users> findAll() {
		return null;
	}

	@Override
	public Forge_Users findById(Serializable id) {
		return null;
	}

	@Override
	public Forge_Users login(String userName, String Password) {
		String sql = "select * from forge_users  where loginName=? and password=?";
		Object []param = {userName,Password};
		Forge_Users user = null;
		try {
			rs = myExcuteQuery(sql, param);
			user = ResultSetUtil.findById(rs, Forge_Users.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
