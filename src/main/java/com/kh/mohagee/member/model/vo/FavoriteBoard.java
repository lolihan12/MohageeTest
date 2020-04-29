package com.kh.mohagee.member.model.vo;

import java.sql.Date;

public class FavoriteBoard {
	
	private int bNo;
	private int userNo;
	private String bTitle;
	private String bTag;
	private String bContent;
	private int cNo;
	private String bUrl;
	private String bCategory;
	private String bKind;
	private int bCount;
	private Date bDate;
	private String bStatus;
	private String titleFilename;
	private String bFileType;
	private int favoriteCount;
	
	public FavoriteBoard() {
		super();
	}

	public FavoriteBoard(int bNo, int userNo, String bTitle, String bTag, String bContent, int cNo, String bUrl,
			String bCategory, String bKind, int bCount, Date bDate, String bStatus, String titleFilename,
			String bFileType, int favoriteCount) {
		super();
		this.bNo = bNo;
		this.userNo = userNo;
		this.bTitle = bTitle;
		this.bTag = bTag;
		this.bContent = bContent;
		this.cNo = cNo;
		this.bUrl = bUrl;
		this.bCategory = bCategory;
		this.bKind = bKind;
		this.bCount = bCount;
		this.bDate = bDate;
		this.bStatus = bStatus;
		this.titleFilename = titleFilename;
		this.bFileType = bFileType;
		this.favoriteCount = favoriteCount;
	}

	@Override
	public String toString() {
		return "FavoriteBoard [bNo=" + bNo + ", userNo=" + userNo + ", bTitle=" + bTitle + ", bTag=" + bTag
				+ ", bContent=" + bContent + ", cNo=" + cNo + ", bUrl=" + bUrl + ", bCategory=" + bCategory + ", bKind="
				+ bKind + ", bCount=" + bCount + ", bDate=" + bDate + ", bStatus=" + bStatus + ", titleFilename="
				+ titleFilename + ", bFileType=" + bFileType + ", favoriteCount=" + favoriteCount + "]";
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

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbTag() {
		return bTag;
	}

	public void setbTag(String bTag) {
		this.bTag = bTag;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
	}

	public String getbUrl() {
		return bUrl;
	}

	public void setbUrl(String bUrl) {
		this.bUrl = bUrl;
	}

	public String getbCategory() {
		return bCategory;
	}

	public void setbCategory(String bCategory) {
		this.bCategory = bCategory;
	}

	public String getbKind() {
		return bKind;
	}

	public void setbKind(String bKind) {
		this.bKind = bKind;
	}

	public int getbCount() {
		return bCount;
	}

	public void setbCount(int bCount) {
		this.bCount = bCount;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	public String getbStatus() {
		return bStatus;
	}

	public void setbStatus(String bStatus) {
		this.bStatus = bStatus;
	}

	public String getTitleFilename() {
		return titleFilename;
	}

	public void setTitleFilename(String titleFilename) {
		this.titleFilename = titleFilename;
	}

	public String getbFileType() {
		return bFileType;
	}

	public void setbFileType(String bFileType) {
		this.bFileType = bFileType;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

}
