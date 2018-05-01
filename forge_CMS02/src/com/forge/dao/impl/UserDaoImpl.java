package com.forge.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
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
		String sql = "delete from forge_users where userId=?";
		Object []param = {id};
		int rowsNum = 0;
		try {
			rowsNum = myExcuteUpdate(sql, param);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsNum;
	}

	@Override
	public int update(Forge_Users t) {
		String sql = "update forge_users set loginName=?,phone=?,email=?,address=? where userId=?";
		Object []param = {t.getLoginName(),t.getPhone(),t.getEmail(),t.getAddress(),t.getUserId()}; 
		int rowsNum = 0;
		try {
			rowsNum =myExcuteUpdate(sql, param);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsNum;
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
		String sql = "select * from forge_users where userId =?";
		Object []param = {id};
		Forge_Users user = null;
		try {
			rs = myExcuteQuery(sql,param);
			user = ResultSetUtil.findById(rs, Forge_Users.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnections();
		}
		return user;
	}

	@Override
	public Forge_Users login(String userName, String Password) {
	
		String sql = "select * from forge_users  where loginName=? and password=?";
		Object []param = {userName,Password};
		Forge_Users user = null;
		try {
			rs = myExcuteQuery(sql, param);
			//System.out.println("============================="+rs.next());
			user = ResultSetUtil.findById(rs, Forge_Users.class);
			//System.out.println("============================="+user);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<Forge_Users> findAlls(int pageNum, int pageSize) {
		String sql = "select * from forge_users limit ?,?";
		//创建一个用户来保存所有的用户
		List<Forge_Users> users = new ArrayList();
		Object []param = {pageNum,pageSize};
		try {
			rs = myExcuteQuery(sql, param);
			users = ResultSetUtil.findAll(rs, Forge_Users.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	//获取信息总条数
	@Override
	public int getTotalCount() {
		String sql = "select count(*) as count from forge_users";
		int count = 0;
		try {
			rs = myExcuteQuery(sql);
			if(rs.next()){
				count = rs.getInt("count");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

}
