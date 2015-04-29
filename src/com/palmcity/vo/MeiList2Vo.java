package com.palmcity.vo;

import java.io.Serializable;

import org.json.JSONObject;

public class MeiList2Vo implements Serializable{
	private String title;
	private String author;
	private String time;
	private String img1;
	private String img2;
	private String img3;
	private String id;
	private String nid;
	

	

	public MeiList2Vo(JSONObject obj){
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
