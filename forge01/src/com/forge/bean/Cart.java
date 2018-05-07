package com.forge.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Cart implements Serializable {
	//������һ�����ϱ������е���Ʒ
	private static Map<String,CartItem> map = new LinkedHashMap<>();
	//������Ʒ���ܼ�
	private double price;
	
	private int count;
	
	

	//������Ʒ
	public void addProduct(Forge_Product product,int num){
		//��һ�ι�����  �϶� Ϊ null
		System.out.println("============进入了cartItem ==============");

		CartItem  cartItem = map.get(product.getId());
		if(cartItem == null){  // ֤�����ﳵ��ʲô��û��
			System.out.println("============进入了cartItem == null==============");
			cartItem = new CartItem(); //ʵ������
			//���û�����������Ʒ�Ž�������
			System.out.println("===================cartItem"+product);
			cartItem.setProduct(product);
			cartItem.setNum(num);
			// �ѹ�����Ž��ﳵ
			map.put(product.getId(), cartItem);
		}else{
			System.out.println("============进入了cartItem != null==============");   
			
			cartItem.setNum(cartItem.getNum()+num);
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
	
	public void setCount(int count) {
		System.out.println("map.size():"+map.size());
		this.count = map.size();
	}
	public int getCount() {
		return count;
	}

	

}
