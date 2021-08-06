package com.ssafy.sharehouse.dto;

public class AreaCode {
	private String sidoCode;
	private String sidoName;
	private String gugunCode;
	private String gugunName;
	private String dongCode;
	private String dongName;
	

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public AreaCode() {
		super();
	}
	
	public AreaCode(String sidoCode, String sidoName) {
		super();
		this.sidoCode = sidoCode;
		this.sidoName = sidoName;
	}
	
	public AreaCode(String sidoCode, String sidoName, String gugunCode, String gugunName) {
		super();
		this.sidoCode = sidoCode;
		this.sidoName = sidoName;
		this.gugunCode = gugunCode;
		this.gugunName = gugunName;
	}
	
	public AreaCode(String sidoCode, String sidoName, String gugunCode, String gugunName, String dongCode,
			String dongName) {
		super();
		this.sidoCode = sidoCode;
		this.sidoName = sidoName;
		this.gugunCode = gugunCode;
		this.gugunName = gugunName;
		this.dongCode = dongCode;
		this.dongName = dongName;
	}

	@Override
	public String toString() {
		return "SidoGugunDongCodeDto [sidoCode=" + sidoCode + ", sidoName=" + sidoName + ", gugunCode=" + gugunCode
				+ ", gugunName=" + gugunName + ", dongCode=" + dongCode + ", dongName=" + dongName + "]";
	}
}
