package com.manish.model;

public class CourseAssessment {

	int caid;
	int cid;
	int aid;
	String semester;
	public int getCaid() {
		return caid;
	}
	public void setCaid(int caid) {
		this.caid = caid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	@Override
	public String toString() {
		return "CourseAssessment [caid=" + caid + ", cid=" + cid + ", aid=" + aid + ", semester=" + semester + "]";
	}
	
}
