package com.kh.mohagee.talkBoard.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class TalkBoard implements Serializable {
	
	private int tno;
	private String tWriter;
	private String tTitle;
	private String tContent;
	private int tCount;
	private Date tDate;
	private String titleFilename;
	private String tFileType;
	private String tStatus;
	private String tTag;
	
	private int tFileno;
	
	
	public TalkBoard() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TalkBoard(int tno, String tWriter, String tTitle, String tContent, int tCount, Date tDate,
			String titleFilename, String tFileType, String tStatus, String tTag, int tFileno) {
		super();
		this.tno = tno;
		this.tWriter = tWriter;
		this.tTitle = tTitle;
		this.tContent = tContent;
		this.tCount = tCount;
		this.tDate = tDate;
		this.titleFilename = titleFilename;
		this.tFileType = tFileType;
		this.tStatus = tStatus;
		this.tTag = tTag;
		this.tFileno = tFileno;
	}


	public int getTno() {
		return tno;
	}


	public void setTno(int tno) {
		this.tno = tno;
	}


	public String gettWriter() {
		return tWriter;
	}


	public void settWriter(String tWriter) {
		this.tWriter = tWriter;
	}


	public String gettTitle() {
		return tTitle;
	}


	public void settTitle(String tTitle) {
		this.tTitle = tTitle;
	}


	public String gettContent() {
		return tContent;
	}


	public void settContent(String tContent) {
		this.tContent = tContent;
	}


	public int gettCount() {
		return tCount;
	}


	public void settCount(int tCount) {
		this.tCount = tCount;
	}


	public Date gettDate() {
		return tDate;
	}


	public void settDate(Date tDate) {
		this.tDate = tDate;
	}


	public String getTitleFilename() {
		return titleFilename;
	}


	public void setTitleFilename(String titleFilename) {
		this.titleFilename = titleFilename;
	}


	public String gettFileType() {
		return tFileType;
	}


	public void settFileType(String tFileType) {
		this.tFileType = tFileType;
	}


	public String gettStatus() {
		return tStatus;
	}


	public void settStatus(String tStatus) {
		this.tStatus = tStatus;
	}


	public String gettTag() {
		return tTag;
	}


	public void settTag(String tTag) {
		this.tTag = tTag;
	}


	public int gettFileno() {
		return tFileno;
	}


	public void settFileno(int tFileno) {
		this.tFileno = tFileno;
	}


	@Override
	public String toString() {
		return "TalkBoard [tno=" + tno + ", tWriter=" + tWriter + ", tTitle=" + tTitle + ", tContent=" + tContent
				+ ", tCount=" + tCount + ", tDate=" + tDate + ", titleFilename=" + titleFilename + ", tFileType="
				+ tFileType + ", tStatus=" + tStatus + ", tTag=" + tTag + ", tFileno=" + tFileno + "]";
	}


	
	
	

}
