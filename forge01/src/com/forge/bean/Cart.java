package com.forge.bean;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Cart {
	//必须有一个集合保存所有的商品
	private Map<String,CartItem> map = new LinkedHashMap<>();
	//所有商品的总价
	private double price;
	
	//新增商品
	public void addProduct(Forge_Product product){
		//第一次购物项  肯定 为 null
		CartItem  cartItem = map.get(product.getId());
		if(cartItem == null){  // 证明购物车中什么都没有
			cartItem = new CartItem(); //实例化购物项
			//将用户传递来的商品放进购物项中
			cartItem.setProduct(product);	
			// 把购物项放进购物车
			map.put(product.getId(), cartItem);
		}else{
			//如果存在商品  该商品数量加1
			cartItem.setNum(cartItem.getNum()+1);
		}
	}

	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	
	/**
	 * 
	 * @return 所有商品总价
	 */
	public double getPrice() {
		int totalPrice = 0; //保存总价
		for(Entry<String,CartItem> product : map.entrySet()){
			CartItem cartItem = product.getValue();
			totalPrice += cartItem.getPrice();
		}
		return totalPrice;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	

}
