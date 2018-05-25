package com.manish.model;

import com.google.gson.Gson;

public class Course {

	int cid;
	String title;
	String prerequisites;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrerequisites() {
		return prerequisites;
	}
	public void setPrerequisites(String prerequisites) {
		this.prerequisites = prerequisites;
	}
	@Override
	public String toString() {
		return "Course [cid=" + cid + ", title=" + title + ", prerequisites=" + prerequisites + "]";
	}
	
	public String toJSON() {
		Gson gson=new Gson();
		return gson.toJson(this);
	}
}
