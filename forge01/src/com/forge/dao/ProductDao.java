package com.forge.dao;

import java.util.List;

import com.forge.bean.Forge_Product;

public interface ProductDao extends BaseDao<Forge_Product> {

	List<Forge_Product> findBooksAjax();

}
