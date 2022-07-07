package toy.shopping.pay.member.model.vo;

import java.sql.Date;

public class Member {
	private String emailId;
	private String userPwd;
	private String userName;
	private String nickName;
	private String phone;
	private Date birthDate;
	private String gender;
	private String memberStatus;
	private Date createDate;
	private Date modifyDate;
	private String address;
	private int point;
	private int loginType;
	
	public Member() {}

	public Member(String emailId, String userPwd, String userName, String nickName, String phone, Date birthDate,
			String gender, String memberStatus, Date createDate, Date modifyDate, String address, int point,
			int loginType) {
		super();
		this.emailId = emailId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.nickName = nickName;
		this.phone = phone;
		this.birthDate = birthDate;
		this.gender = gender;
		this.memberStatus = memberStatus;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.address = address;
		this.point = point;
		this.loginType = loginType;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getLoginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}

	@Override
	public String toString() {
		return "Member [emailId=" + emailId + ", userPwd=" + userPwd + ", userName=" + userName + ", nickName="
				+ nickName + ", phone=" + phone + ", birthDate=" + birthDate + ", gender=" + gender + ", memberStatus="
				+ memberStatus + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", address=" + address
				+ ", point=" + point + ", loginType=" + loginType + "]";
	}
	
}
