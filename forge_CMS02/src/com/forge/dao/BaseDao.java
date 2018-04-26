package com.forge.dao;

import java.io.Serializable;
import java.util.List;


public interface BaseDao<T> {

	int add (T t);
	int delete(Serializable id);
	int update(T t);
	List<T> findAll();
	T findById (Serializable id);
}
