package com.forge.bean;

import java.io.Serializable;
import java.sql.Date;

/**
*Created by 刘天赐on2018-04-21
*@Descrptionforge_news实体类
*/ 


public class Forge_News  implements Serializable{
	private int id;
	private String title;
	private String content;
	private Date createTime;
	private String img;
	public Forge_News(){}
	public Forge_News(int id,String title,String content,Date createTime,String img){
	super();
	this. id=id;
	this. title=title;
	this. content=content;
	this. createTime=createTime;
	this. img=img;
}
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setTitle(String title){
	this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setContent(String content){
	this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setCreateTime(Date createTime){
	this.createTime=createTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setImg(String img){
	this.img=img;
	}
	public String getImg(){
		return img;
	}
}

