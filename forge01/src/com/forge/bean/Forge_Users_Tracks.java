package com.forge.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 未来的你会感谢现在奋斗的你！
 * 用户足迹
 * 浏览过的商品记录
 *
 */
public class Forge_Users_Tracks implements Serializable{

	private String userId;  //用户ID
	private String productId; //商品ID
	private String viewTime;   //用户的浏览时间
	
	public Forge_Users_Tracks() {
		super();
	}
	
	
	public Forge_Users_Tracks(String userId, String productId, String viewTime) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.viewTime = viewTime;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getViewTime() {
		return viewTime;
	}


	public void setViewTime(String viewTime) {
		this.viewTime = viewTime;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
