package com.palmcity.vo;

import java.io.Serializable;

import org.json.JSONObject;

public class HuiAdVo implements Serializable {
	private String title;
	private String author;
	private String time;
	private String img1;
	private String img2;
	private String img3;
	private String id;
	private String nid;
	
	public HuiAdVo(){
		
	}
	
	

	public HuiAdVo(String title, String author, String time,
			String img1, String img2, String img3, String id, String nid) {
		super();
		this.title = title;
		this.author = author;
		this.time = time;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.id = id;
		this.nid = nid;
	}



	public HuiAdVo(JSONObject obj){
		try{
			this.title = obj.getString("title");
			this.author = obj.getString("author");
			this.time = obj.getString("time");
			this.img1 = obj.getString("img1");
			this.img2 = obj.getString("img2");
			this.img3 = obj.getString("img3");
			this.id = obj.getString("id");
			this.nid = obj.getString("nid");
		}catch(Exception e){
			
		}
	}
	
	public String getNid() {
		return nid;
	}
	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getTime() {
		return time;
	}
	public String getImg1() {
		return img1;
	}
	public String getImg2() {
		return img2;
	}
	public String getImg3() {
		return img3;
	}
}
