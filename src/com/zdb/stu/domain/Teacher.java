package com.zdb.stu.domain;

public class Teacher {
	private String tId;
	private String tName;
	private String tPassword;
	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public String gettPassword() {
		return tPassword;
	}
	public void settPassword(String tPassword) {
		this.tPassword = tPassword;
	}
	@Override
	public String toString() {
		return "TeaAccount [tId=" + tId + ", tName=" + tName + ", tPassword=" + tPassword + "]";
	}
	
}
