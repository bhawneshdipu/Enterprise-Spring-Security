package com.manish.model;

public class CourseConduction {

	int ccid;
	int lid;
	int cid;
	String semester;
	int capacity;
	public int getCcid() {
		return ccid;
	}
	public void setCcid(int ccid) {
		this.ccid = ccid;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
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
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	@Override
	public String toString() {
		return "CourseConduction [ccid=" + ccid + ", lid=" + lid + ", cid=" + cid + ", semester=" + semester
				+ ", capacity=" + capacity + "]";
	}
	
	
}
