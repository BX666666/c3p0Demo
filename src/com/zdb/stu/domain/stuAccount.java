package com.zdb.stu.domain;

public class stuAccount {
	private String sId;
	private String sName;
	private String sPassword;
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsPassword() {
		return sPassword;
	}
	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}
	@Override
	public String toString() {
		return "stuAccount [sId=" + sId + ", sName=" + sName + ", sPassword=" + sPassword + "]";
	}
	
}
