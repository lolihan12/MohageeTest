package com.kh.mohagee.talkBoard.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Tcomment implements Serializable {
	
	private int tcno;
	private int tno;
	private int userNo;
	private String tcContent;
	private Date tcDate;
	private int tcLevel;
	private String tcStatus;
	private int ttcNo;
	
	public Tcomment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tcomment(int tno, int userNo, String tcContent) {
		super();
		this.tno = tno;
		this.userNo = userNo;
		this.tcContent = tcContent;
	}
	
	public Tcomment(int tcno, int tno, int userNo, String tcContent, Date tcDate, int tcLevel, String tcStatus,
			int ttcNo) {
		super();
		this.tcno = tcno;
		this.tno = tno;
		this.userNo = userNo;
		this.tcContent = tcContent;
		this.tcDate = tcDate;
		this.tcLevel = tcLevel;
		this.tcStatus = tcStatus;
		this.ttcNo = ttcNo;
	}
	
	public Tcomment(int tno, int userNo, String tcContent, int ttcNo) {
		super();
		this.tno = tno;
		this.userNo = userNo;
		this.tcContent = tcContent;
		this.ttcNo = ttcNo;
	}

	public int getTcno() {
		return tcno;
	}
	public void setTcno(int tcno) {
		this.tcno = tcno;
	}
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getTcContent() {
		return tcContent;
	}
	public void setTcContent(String tcContent) {
		this.tcContent = tcContent;
	}
	public Date getTcDate() {
		return tcDate;
	}
	public void setTcDate(Date tcDate) {
		this.tcDate = tcDate;
	}
	public int getTcLevel() {
		return tcLevel;
	}
	public void setTcLevel(int tcLevel) {
		this.tcLevel = tcLevel;
	}
	public String getTcStatus() {
		return tcStatus;
	}
	public void setTcStatus(String tcStatus) {
		this.tcStatus = tcStatus;
	}
	public int getTtcNo() {
		return ttcNo;
	}
	public void setTtcNo(int ttcNo) {
		this.ttcNo = ttcNo;
	}
	@Override
	public String toString() {
		return "Tcomment [tcno=" + tcno + ", tno=" + tno + ", userNo=" + userNo + ", tcContent=" + tcContent
				+ ", tcDate=" + tcDate + ", tcLevel=" + tcLevel + ", tcStatus=" + tcStatus + ", ttcNo=" + ttcNo + "]";
	}
	
	

}
