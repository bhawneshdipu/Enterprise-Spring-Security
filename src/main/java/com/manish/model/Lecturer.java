package com.manish.model;

public class Lecturer {
	public int lid;
	public String gname;
	public String surname;
	public String  email;
	public String password;
	public String campus;
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	@Override
	public String toString() {
		return "Lecturer [Lid=" + lid + ", gname=" + gname + ", surname=" + surname + ", email=" + email + ", password="
				+ password + ", campus=" + campus + "]";
	}

	
}
