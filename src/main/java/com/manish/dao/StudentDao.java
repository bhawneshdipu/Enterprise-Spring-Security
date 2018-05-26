package com.manish.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.manish.model.Lecturer;
import com.manish.model.Student;

public class StudentDao {

	@Autowired
	DataSource mysqlDataSource;
	
	private String tableName="student";
	public final String insertValueList=" FNAME, SURNAME , EMAIL , MAJOR , PASSWORD ";
	public final String valueOrderList=" SID, FNAME, SURNAME , EMAIL , MAJOR , PASSWORD ";
	
	public final String values=" ? , ? , ?, ? , ?   ";
	public final String updateValueList=" FNAME=?, SURNAME=? , EMAIL=? , MAJOR=? , PASSWORD=? ";
	
	public final String studentInsertPstmt="INSERT INTO "+tableName+" ( "+insertValueList+")"+ "  VALUES ( "+values+" ) ";
	
	public final String studentUpdatePstmt="UPDATE "+tableName+" SET "+updateValueList +" WHERE SID=? ";
	
	public final String studentFindPstmt="SELECT * FROM "+tableName+" WHERE SID=? ";
	
	public final String studentDeletePstmt="DELETE  FROM "+tableName+" WHERE SID=? ";
	
	public final String studentAllPstmt="SELECT * FROM "+tableName+" ";
	public final String studentLoginPstmt="SELECT * FROM "+tableName+" WHERE EMAIL=? AND PASSWORD=? ";
	
	
	public int insert(Student obj) {
		try {
			Connection conn=mysqlDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(studentInsertPstmt);
			pstmt.setString(1, obj.getFname());
			pstmt.setString(2, obj.getSurname());
			pstmt.setString(3, obj.getEmail());
			pstmt.setString(4, obj.getMajor());
			pstmt.setString(5, obj.getPassword());
			
			
			
			int countInsert=pstmt.executeUpdate();
			
			if(countInsert>0){
					System.out.println("Student Insert Success");
				return countInsert;
			}else{
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
	
	}
	public int update(Student obj) {
		try {
			Connection conn=mysqlDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(studentUpdatePstmt);
			pstmt.setString(1, obj.getFname());
			pstmt.setString(2, obj.getSurname());
			pstmt.setString(3, obj.getEmail());
			pstmt.setString(4, obj.getMajor());
			pstmt.setString(5, obj.getPassword());
			pstmt.setInt(6, obj.getSid());
			
			
			int countUpdate=pstmt.executeUpdate();
			
			if(countUpdate>0){
					System.out.println("Student  Update Success");
				return countUpdate;
			}else{
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
	
	}
	public Student[] all(){
		try {
			Connection conn=mysqlDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(studentAllPstmt);
			ResultSet rs=pstmt.executeQuery();
			List<Student> arr=new ArrayList<Student>();
			while(rs.next()){
				Student obj=new Student();
				
				obj.setSid(rs.getInt("SID"));
				obj.setFname(rs.getString("FNAME"));
				obj.setSurname(rs.getString("SURNAME"));
				obj.setEmail(rs.getString("EMAIL"));
				obj.setMajor(rs.getString("MAJOR"));
				
				arr.add(obj);
			}
			rs.close();
			return arr.toArray(new Student[arr.size()]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	public Student find(int id){
		try {
			Connection conn=mysqlDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(studentFindPstmt);
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			
			Student obj=new Student();
			
			while(rs.next()){
				obj.setSid(rs.getInt("SID"));
				obj.setFname(rs.getString("FNAME"));
				obj.setSurname(rs.getString("SURNAME"));
				obj.setEmail(rs.getString("EMAIL"));
				obj.setMajor(rs.getString("MAJOR"));
				obj.setPassword(rs.getString("PASSWORD"));
				
				break;
			}
			rs.close();
			return obj;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	public int delete(int id){
		try {
			Connection conn=mysqlDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(studentDeletePstmt);
			pstmt.setInt(1, id);
			int deleteCount=pstmt.executeUpdate();
			return deleteCount;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	public Student login(String email,String password) {
		try {
			Connection conn=mysqlDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(studentLoginPstmt);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			
			
			while(rs.next()){
				Student obj=new Student();
				
				obj.setSid(rs.getInt("SID"));
				obj.setFname(rs.getString("FNAME"));
				obj.setSurname(rs.getString("SURNAME"));
				obj.setEmail(rs.getString("EMAIL"));
				obj.setMajor(rs.getString("MAJOR"));
				obj.setPassword(rs.getString("PASSWORD"));
				
				return obj;
				
			}
			rs.close();
			return null;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public Student getByEmail(String email) {
		String studentByEmailPstmt="SELECT * FROM "+tableName+" WHERE EMAIL=? ";
		
		try {
			Connection conn=mysqlDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(studentByEmailPstmt);
			pstmt.setString(1, email);
			ResultSet rs=pstmt.executeQuery();
			
			
			while(rs.next()){
				Student obj=new Student();
				
				obj.setSid(rs.getInt("SID"));
				obj.setFname(rs.getString("FNAME"));
				obj.setSurname(rs.getString("SURNAME"));
				obj.setEmail(rs.getString("EMAIL"));
				obj.setMajor(rs.getString("MAJOR"));
				obj.setPassword(rs.getString("PASSWORD"));
				
				return obj;
				
			}
			rs.close();
			return null;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
