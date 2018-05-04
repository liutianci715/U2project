package com.forge.bean;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Cart {
	//������һ�����ϱ������е���Ʒ
	private Map<String,CartItem> map = new LinkedHashMap<>();
	//������Ʒ���ܼ�
	private double price;
	
	//������Ʒ
	public void addProduct(Forge_Product product){
		//��һ�ι�����  �϶� Ϊ null
		CartItem  cartItem = map.get(product.getId());
		if(cartItem == null){  // ֤�����ﳵ��ʲô��û��
			cartItem = new CartItem(); //ʵ����������
			//���û�����������Ʒ�Ž���������
			cartItem.setProduct(product);	
			// �ѹ�����Ž����ﳵ
			map.put(product.getId(), cartItem);
		}else{
			//���������Ʒ  ����Ʒ������1
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
	 * @return ������Ʒ�ܼ�
	 */
	public double getPrice() {
		int totalPrice = 0; //�����ܼ�
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
