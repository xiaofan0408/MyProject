package com.model;

import java.sql.Timestamp;

public class Illust {
    
	private int id;
	private String thumbURL;
	private String realURL;
	private int authorid;
	private double width;
	private double height;
	private String title;
	private String remark;
	private int score;
	private Timestamp time;

	public String getThumbURL() {
		return thumbURL;
	}

	public void setThumbURL(String thumbURL) {
		this.thumbURL = thumbURL;
	}

	public String getRealURL() {
		return realURL;
	}

	public void setRealURL(String realURL) {
		this.realURL = realURL;
	}

	public Illust(String thumbURL, String realURL) {
		this.thumbURL = thumbURL;
		this.realURL = realURL;
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAuthorid() {
		return authorid;
	}

	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	
	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Illust() {

	}

}
