package com.zdb.stu.domain;

public class student {
	private String stuId;
	private String stuName;
	private String stuSex;
	private String stuAge;
	private String stuNum;
	private String stuScore;

	
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	public String getStuAge() {
		return stuAge;
	}
	public void setStuAge(String stuAge) {
		this.stuAge = stuAge;
	}
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
	public String getStuScore() {
		return stuScore;
	}
	public void setStuScore(String stuScore) {
		this.stuScore = stuScore;
	}
	@Override
	public String toString() {
		return "student [stuId=" + stuId + ", stuName=" + stuName + ", stuSex=" + stuSex + ", stuAge=" + stuAge
				+ ", stuNum=" + stuNum + ", stuScore=" + stuScore + "]";
	}
	
	
}
