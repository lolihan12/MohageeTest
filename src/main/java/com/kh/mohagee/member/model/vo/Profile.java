package com.kh.mohagee.member.model.vo;

import java.sql.Date;

public class Profile {

	private int profileNo;
	private int userNo;
	private String pOriginalFileName;
	private String pRenamedFileName;
	private String pFilePath;
	private Date pFileDate;
	private String pFileStatus;
	
	public Profile() {
		super();
	}

	public Profile(int profileNo, int userNo, String pOriginalFileName, String pRenamedFileName, String pFilePath,
			Date pFileDate, String pFileStatus) {
		super();
		this.profileNo = profileNo;
		this.userNo = userNo;
		this.pOriginalFileName = pOriginalFileName;
		this.pRenamedFileName = pRenamedFileName;
		this.pFilePath = pFilePath;
		this.pFileDate = pFileDate;
		this.pFileStatus = pFileStatus;
	}

	@Override
	public String toString() {
		return "Profile [profileNo=" + profileNo + ", userNo=" + userNo + ", pOriginalFileName=" + pOriginalFileName
				+ ", pRenamedFileName=" + pRenamedFileName + ", pFilePath=" + pFilePath + ", pFileDate=" + pFileDate
				+ ", pFileStatus=" + pFileStatus + "]";
	}

	public int getProfileNo() {
		return profileNo;
	}

	public void setProfileNo(int profileNo) {
		this.profileNo = profileNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getpOriginalFileName() {
		return pOriginalFileName;
	}

	public void setpOriginalFileName(String pOriginalFileName) {
		this.pOriginalFileName = pOriginalFileName;
	}

	public String getpRenamedFileName() {
		return pRenamedFileName;
	}

	public void setpRenamedFileName(String pRenamedFileName) {
		this.pRenamedFileName = pRenamedFileName;
	}

	public String getpFilePath() {
		return pFilePath;
	}

	public void setpFilePath(String pFilePath) {
		this.pFilePath = pFilePath;
	}

	public Date getpFileDate() {
		return pFileDate;
	}

	public void setpFileDate(Date pFileDate) {
		this.pFileDate = pFileDate;
	}

	public String getpFileStatus() {
		return pFileStatus;
	}

	public void setpFileStatus(String pFileStatus) {
		this.pFileStatus = pFileStatus;
	}
	
}
