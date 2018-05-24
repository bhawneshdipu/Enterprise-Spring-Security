package com.manish.model;

public class Assessment {

	int aid;
	int sid;
	int eid;
	int a1;
	int a2;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getA1() {
		return a1;
	}
	public void setA1(int a1) {
		this.a1 = a1;
	}
	public int getA2() {
		return a2;
	}
	public void setA2(int a2) {
		this.a2 = a2;
	}
	@Override
	public String toString() {
		return "Assessment [aid=" + aid + ", sid=" + sid + ", eid=" + eid + ", a1=" + a1 + ", a2=" + a2 + "]";
	}
	
}

