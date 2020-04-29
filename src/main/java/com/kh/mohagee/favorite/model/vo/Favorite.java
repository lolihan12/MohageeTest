package com.kh.mohagee.favorite.model.vo;

public class Favorite {
	
	private int bNo;
	private int userNo;
	private String fStatus;
	
	public Favorite() {
		super();
	}

	public Favorite(int bNo, int userNo, String fStatus) {
		super();
		this.bNo = bNo;
		this.userNo = userNo;
		this.fStatus = fStatus;
	}

	@Override
	public String toString() {
		return "Favorite [bNo=" + bNo + ", userNo=" + userNo + ", fStatus=" + fStatus + "]";
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getfStatus() {
		return fStatus;
	}

	public void setfStatus(String fStatus) {
		this.fStatus = fStatus;
	}
	
}
