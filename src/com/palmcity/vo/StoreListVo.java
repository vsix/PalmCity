package com.palmcity.vo;

import java.io.Serializable;

import org.json.JSONObject;

public class StoreListVo implements Serializable{
	String name;
	String id;

	public StoreListVo(JSONObject obj) {
		try {
			this.id = obj.getString("id");
			this.name = obj.getString("name");
		} catch (Exception e) {

		}
	}
	
	public StoreListVo(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}

	public StoreListVo() {
		super();
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

}
