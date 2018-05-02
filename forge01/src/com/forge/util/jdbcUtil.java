package com.forge.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class jdbcUtil {

	  //01.提取所有方法中共同的JDBC API
	   protected static Connection conn = null;
	   protected static PreparedStatement pstmt = null;
	   protected static ResultSet rs = null;
	   
	 //02.建立连接
	   public static boolean getConnection() throws ClassNotFoundException,
		SQLException {
		/*try {
			Class.forName(ConfigManager.getInstance().getValue("jdbc.driver"));
			//建立连接
			conn = DriverManager.getConnection(ConfigManager.getInstance().getValue("jdbc.url"),
					ConfigManager.getInstance().getValue("jdbc.userName"),
					ConfigManager.getInstance().getValue("jdbc.password"));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}*/
		try {
			//初始化上下文对象Tomcat容器
			Context con = new InitialContext();
			//通过数据源中的name属性获取指定的数据源
			DataSource ds = (DataSource) con.lookup("java:comp/env/jdbc/forge");
		    //从连接池中获取connection pool
			conn = ds.getConnection();
			return true;
		} catch (NamingException e) {
			e.printStackTrace();
			return false;
		}  
		
   }
	   
	 //03.提取所有的释放资源代码
		public static void closeConnections() {
			try {
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(pstmt!=null){
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//04.提取所有的增删改方法
		public static int myExcuteUpdate(String sql,Object...param) throws ClassNotFoundException, SQLException{
			int rowNum = 0;
			if(getConnection()){
				pstmt = conn.prepareStatement(sql);
				for(int i =0; i<param.length;i++){
					pstmt.setObject(i+1, param[i]);
				}
				rowNum = pstmt.executeUpdate();
			}
			closeConnections();
			return rowNum;
		}
		
		//05.提取公共的查询方法
		public  static ResultSet myExcuteQuery(String sql,Object...param) throws ClassNotFoundException, SQLException{
			if(getConnection()){
				pstmt = conn.prepareStatement(sql);
				for(int i =0; i<param.length;i++){
					pstmt.setObject(i+1, param[i]);
				}
				rs = pstmt.executeQuery();
			}
			return rs;
		}

}
