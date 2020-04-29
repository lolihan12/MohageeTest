package com.kh.mohagee.travelBoard.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class TravelBoard implements Serializable {

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
	private String titleFilename;
	private String bFileType;
	private String bStatus;
	private String nickName;
	private String introduce;
	private String pRenamedFileName;
	private int favoriteCount;
	private int mNo;
	private String cityName;
	private double mapY;
	private double mapX;
	private int commentCount;
   
	private int bFileno;

	public TravelBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TravelBoard(int bNo, int userNo, String bTitle, String bTag, String bContent, int cNo, String bUrl,
			String bCategory, String bKind, int bCount, Date bDate, String titleFilename, String bFileType,
			String bStatus, String nickName, String introduce, String pRenamedFileName, int favoriteCount, int mNo,
			String cityName, double mapY, double mapX, int commentCount, int bFileno) {
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
		this.titleFilename = titleFilename;
		this.bFileType = bFileType;
		this.bStatus = bStatus;
		this.nickName = nickName;
		this.introduce = introduce;
		this.pRenamedFileName = pRenamedFileName;
		this.favoriteCount = favoriteCount;
		this.mNo = mNo;
		this.cityName = cityName;
		this.mapY = mapY;
		this.mapX = mapX;
		this.commentCount = commentCount;
		this.bFileno = bFileno;
	}

	@Override
	public String toString() {
		return "TravelBoard [bNo=" + bNo + ", userNo=" + userNo + ", bTitle=" + bTitle + ", bTag=" + bTag
				+ ", bContent=" + bContent + ", cNo=" + cNo + ", bUrl=" + bUrl + ", bCategory=" + bCategory + ", bKind="
				+ bKind + ", bCount=" + bCount + ", bDate=" + bDate + ", titleFilename=" + titleFilename
				+ ", bFileType=" + bFileType + ", bStatus=" + bStatus + ", nickName=" + nickName + ", introduce="
				+ introduce + ", pRenamedFileName=" + pRenamedFileName + ", favoriteCount=" + favoriteCount + ", mNo="
				+ mNo + ", cityName=" + cityName + ", mapY=" + mapY + ", mapX=" + mapX + ", commentCount="
				+ commentCount + ", bFileno=" + bFileno + "]";
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

	public String getbStatus() {
		return bStatus;
	}

	public void setbStatus(String bStatus) {
		this.bStatus = bStatus;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getpRenamedFileName() {
		return pRenamedFileName;
	}

	public void setpRenamedFileName(String pRenamedFileName) {
		this.pRenamedFileName = pRenamedFileName;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public double getMapY() {
		return mapY;
	}

	public void setMapY(double mapY) {
		this.mapY = mapY;
	}

	public double getMapX() {
		return mapX;
	}

	public void setMapX(double mapX) {
		this.mapX = mapX;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getbFileno() {
		return bFileno;
	}

	public void setbFileno(int bFileno) {
		this.bFileno = bFileno;
	}
	
}