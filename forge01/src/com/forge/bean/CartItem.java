package com.forge.bean;
/**
 * 购物项（购物车中的内容）
 */
public class CartItem {
	private Forge_Product product;  //商品
	private int num;  //数量
	private double price;  //商品的总价
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
	
	//商品的总金额
	public double getPrice() {
		return product.getPrice()*num;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
