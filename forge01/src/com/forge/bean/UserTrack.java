package com.forge.bean;

import java.util.Date;

public class UserTrack {
	private int userId;  
	private String viewTime;  //�û����ʱ�� 
	private Forge_Product product;  //��Ʒ��¼
	public UserTrack() {
		super();
	}
	public UserTrack(int userId, String viewTime, Forge_Product product) {
		super();
		this.userId = userId;
		this.viewTime = viewTime;
		this.product = product;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getViewTime() {
		return viewTime;
	}
	public void setViewTime(String viewTime) {
		this.viewTime = viewTime;
	}
	public Forge_Product getProduct() {
		return product;
	}
	public void setProduct(Forge_Product product) {
		this.product = product;
	}
	
	
	
	
	
}
