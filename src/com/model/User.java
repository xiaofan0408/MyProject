package com.model;

public class User {
	
	private int id;
	private String userName;
	private String passWord;
	private String email;
	private String userImg;
	private int SumOfillust;
	private int SumOffollow;
	private int beconcerned;
	private String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSumOfillust() {
		return SumOfillust;
	}
	public void setSumOfillust(int sumOfillust) {
		SumOfillust = sumOfillust;
	}
	public int getSumOffollow() {
		return SumOffollow;
	}
	public void setSumOffollow(int sumOffollow) {
		SumOffollow = sumOffollow;
	}
	public int getBeconcerned() {
		return beconcerned;
	}
	public void setBeconcerned(int beconcerned) {
		this.beconcerned = beconcerned;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
