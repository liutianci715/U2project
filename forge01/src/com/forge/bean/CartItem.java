package com.forge.bean;

import java.io.Serializable;

/**
 * ��������ﳵ�е����ݣ�
 */
public class CartItem implements Serializable{
	private Forge_Product product;  //��Ʒ
	private int num;  //����
	private double price;  //��Ʒ���ܼ�
	public Forge_Product getProduct() {
		return product;
	}
	public void setProduct(Forge_Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	//��Ʒ���ܽ��
	public double getPrice() {
		return product.getPrice()*num;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
