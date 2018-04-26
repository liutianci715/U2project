package com.forge.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService <T>{

	void add (T t);
	void  delete(Serializable id);
	void  update(T t);
	List<T> findAll();
	T findById (Serializable id);
}
