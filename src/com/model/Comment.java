package com.model;

public class Comment {
	
	private int id;
	private String content;
	private int userid;
	private int illustid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getIllustid() {
		return illustid;
	}
	public void setIllustid(int illustid) {
		this.illustid = illustid;
	}
	
}
