package com.ssafy.sharehouse.dto;

public class WishList {
	String userId;
	String rentNo;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRentNo() {
		return rentNo;
	}
	public void setRentNo(String rentNo) {
		this.rentNo = rentNo;
	}
	
	public WishList() {
		super();
	}
	public WishList(String userId, String rentNo) {
		super();
		this.userId = userId;
		this.rentNo = rentNo;
	}
	
	@Override
	public String toString() {
		return "WishList [userId=" + userId + ", rentNo=" + rentNo + "]";
	}
	
	
}
