package com.forge.service;

import java.io.Serializable;
import java.util.List;

import com.forge.bean.Forge_Product_Category;

public interface Product_CategoryService extends BaseService<Forge_Product_Category>{
	public List<Forge_Product_Category> findByType(Serializable id);

	public List<Forge_Product_Category> findType2(Serializable parentId);

	public List<Forge_Product_Category> findType3();

}
