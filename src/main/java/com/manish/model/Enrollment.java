package com.manish.model;

public class Enrollment {

	int eid;
	int sid;
	int cid;
	String semester;
	public int getEid() {
		return eid;
	}
	
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	@Override
	public String toString() {
		return "Enrollment [eid=" + eid + ", sid=" + sid + ", cid=" + cid + ", semester=" + semester + "]";
	}
}
