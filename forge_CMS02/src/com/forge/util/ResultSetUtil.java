package com.forge.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 01.我们从数据库中获取的结果集就是ResultSet
 * 02.结果集中的类型我们确定吗？？？ 不确定
 * 03.sql语句一旦执行T的类型就确定了！
 * 04.通过反射机制  向实体类中的属性赋值
 */
public class ResultSetUtil {

	/**
	 * 1.务必需要一个结果集ResultSet,才能获得T的类型
	 * 2.反射需要Class<T>
	 */
	
	public static <T> T findById(ResultSet rs, Class<T> c){
		T object = null;
		try {
			if(rs.next()){
				object = c.newInstance();  //实例化对象
				Field[] fields = c.getDeclaredFields();  //获取实体类中所有的属性
				for(Field field:fields){
					field.setAccessible(true);  //打开权限开关
					field.set(object, rs.getObject(field.getName()));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return object;
		
	}
	
	public static <T> List<T> findAll(ResultSet rs,Class<T> c){
		T object = null;
		List<T> objects = new ArrayList();
		try {
			while(rs.next()){
				object = c.newInstance();  //实例化对象
				Field[] fields = c.getDeclaredFields();  //获取对象所有属性、
				for(Field field : fields){
					field.setAccessible(true);  //打开访问权限
					field.set(object, rs.getObject(field.getName()));
				}
				//将对象加入到集合
				objects.add(object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return objects;
		
	}
}
