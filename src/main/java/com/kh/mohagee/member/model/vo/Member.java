package com.kh.mohagee.member.model.vo;

public class Member {
	
	private int userNo;
	private String userId;
	private String password;
	private String userName;
	private String nickName;
	private String phone;
	private String introduce;
	private String status;
	private int reportCount;
	private String userGrade;
	private int emailCheck;
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(int userNo, String userId, String password, String userName, String nickName, String phone,
			String introduce, String status, int reportCount, String userGrade, int emailCheck) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.nickName = nickName;
		this.phone = phone;
		this.introduce = introduce;
		this.status = status;
		this.reportCount = reportCount;
		this.userGrade = userGrade;
		this.emailCheck = emailCheck;
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", password=" + password + ", userName=" + userName
				+ ", nickName=" + nickName + ", phone=" + phone + ", introduce=" + introduce + ", status=" + status
				+ ", reportCount=" + reportCount + ", userGrade=" + userGrade + ", emailCheck=" + emailCheck + "]";
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public String getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}

	public int getEmailCheck() {
		return emailCheck;
	}

	public void setEmailCheck(int emailCheck) {
		this.emailCheck = emailCheck;
	}
	
}
