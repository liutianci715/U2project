package com.forge.service;

import java.io.Serializable;
import java.util.List;

import com.forge.util.PageInfo;

public interface BaseService <T>{

	void add (T t);
	void  delete(Serializable id);
	void  update(T t);
	List<T> findAll();
	T findById (Serializable id);
	
	PageInfo<T> findAlls(int pageNum, int pageSize);

	int getTotalCount();
}
