package com.forge.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.forge.bean.Forge_Users;
import com.forge.bean.region;
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
		String sql = "INSERT INTO forge_users(loginName,`password`,phone,email,address) VALUES (?,?,?,?,?)";
		System.out.println("t.getLoginName()==============>"+t.getLoginName());
		Object []param = {t.getLoginName(),t.getPassword(),t.getPhone(),t.getEmail(),t.getAddress()};
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
		String sql = "select * from forge_users";
		Forge_Users user = null;
		List<Forge_Users> users = new ArrayList();
		try {
			rs = myExcuteQuery(sql);
			users = ResultSetUtil.findAll(rs, Forge_Users.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnections();
		}
		return users;
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

	@Override
	public Forge_Users findByName(String loginName) {
		String sql = "select * from forge_users  where loginName=? ";
		Object []param = {loginName};
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

	@Override
	public List<region> findAddress(String parentId) {
		List list=new ArrayList();
		String sql="select region_id,region_name from region where parent_id = ?";
		Object [] params={parentId};
		Map map=null;
		 try {
			rs=myExcuteQuery(sql, params);
			while (rs.next()) {
				map=new HashMap();
				map.put("id", rs.getInt(1));
				map.put("name", rs.getString(2));
	            list.add(map);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnections();
		}
		return list ;	
	}

}
