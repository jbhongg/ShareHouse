package com.ssafy.sharehouse.dto;

import lombok.Data;

@Data
public class HouseRent{
	private int no;
	private String dong;
	private String houseName;
	private int buildYear;
	private int code;
	private String dealDate;
	private double area;
	private int floor;
	private int deposit;
	private int monthlyRent;
	private int residentsNum;
	private String address;
	private String img;
	
	public HouseRent(int no, String dong, String houseName, int buildYear, int code, String dealDate, double area,
			int floor, int deposit, int monthlyRent, int residentsNum, String address) {
		super();
		this.no = no;
		this.dong = dong;
		this.houseName = houseName;
		this.buildYear = buildYear;
		this.code = code;
		this.dealDate = dealDate;
		this.area = area;
		this.floor = floor;
		this.deposit = deposit;
		this.monthlyRent = monthlyRent;
		if(residentsNum==0)
			this.residentsNum=1;
		else this.residentsNum = residentsNum;
		this.address = address;
	}

}
