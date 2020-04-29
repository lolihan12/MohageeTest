package com.kh.mohagee.talkBoard.model.vo;

import java.io.Serializable;
import java.sql.Date;


public class TalkAttachment implements Serializable {
	
	private int tFileno;
	private int tno;
	private String tFileName;
	private String tFilePath;
	private Date tFileDate;
	private int tFileLevel;
	private String tFileStatus;
	private String tFileType;
	
	
	public TalkAttachment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TalkAttachment(int tFileno, int tno, String tFileName, String tFilePath, Date tFileDate, int tFileLevel,
			String tFileStatus, String tFileType) {
		super();
		this.tFileno = tFileno;
		this.tno = tno;
		this.tFileName = tFileName;
		this.tFilePath = tFilePath;
		this.tFileDate = tFileDate;
		this.tFileLevel = tFileLevel;
		this.tFileStatus = tFileStatus;
		this.tFileType = tFileType;
	}


	public int gettFileno() {
		return tFileno;
	}


	public void settFileno(int tFileno) {
		this.tFileno = tFileno;
	}


	public int getTno() {
		return tno;
	}


	public void setTno(int tno) {
		this.tno = tno;
	}


	public String gettFileName() {
		return tFileName;
	}


	public void settFileName(String tFileName) {
		this.tFileName = tFileName;
	}


	public String gettFilePath() {
		return tFilePath;
	}


	public void settFilePath(String tFilePath) {
		this.tFilePath = tFilePath;
	}


	public Date gettFileDate() {
		return tFileDate;
	}


	public void settFileDate(Date tFileDate) {
		this.tFileDate = tFileDate;
	}


	public int gettFileLevel() {
		return tFileLevel;
	}


	public void settFileLevel(int tFileLevel) {
		this.tFileLevel = tFileLevel;
	}


	public String gettFileStatus() {
		return tFileStatus;
	}


	public void settFileStatus(String tFileStatus) {
		this.tFileStatus = tFileStatus;
	}


	public String gettFileType() {
		return tFileType;
	}


	public void settFileType(String tFileType) {
		this.tFileType = tFileType;
	}


	@Override
	public String toString() {
		return "TalkAttachment [tFileno=" + tFileno + ", tno=" + tno + ", tFileName=" + tFileName + ", tFilePath="
				+ tFilePath + ", tFileDate=" + tFileDate + ", tFileLevel=" + tFileLevel + ", tFileStatus=" + tFileStatus
				+ ", tFileType=" + tFileType + "]";
	}

	
	

}
