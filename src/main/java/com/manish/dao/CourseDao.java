package com.manish.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.manish.model.Course;

public class CourseDao {

	@Autowired
	DataSource mysqlDataSource;
	
	private String tableName="course";
	public final String insertValueList=" TITLE, PREREQUISITES ";
	public final String valueOrderList=" CID, TITLE, PREREQUISITES ";
	
	public final String values=" ?, ?  ";
	public final String updateValueList=" TITLE=?, PREREQUISITES=? ";
	
	public final String courseInsertPstmt="INSERT INTO "+tableName+" ( "+insertValueList+")"+ "  VALUES ( "+values+" ) ";
	
	public final String courseUpdatePstmt="UPDATE "+tableName+" SET "+updateValueList +" WHERE CID=? ";
	
	public final String courseFindPstmt="SELECT * FROM "+tableName+" WHERE CID=? ";
	
	public final String courseDeletePstmt="DELETE  FROM "+tableName+" WHERE CID=? ";
	
	public final String courseAllPstmt="SELECT * FROM "+tableName+" ";
	
	
	public int insert(Course obj) {
		try {
			Connection conn=mysqlDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(courseInsertPstmt);
			pstmt.setString(1, obj.getTitle());
			pstmt.setString(2, obj.getPrerequisites());
			
			int countInsert=pstmt.executeUpdate();
			
			if(countInsert>0){
					System.out.println("Connection Insert Success");
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
	public int update(Course obj) {
		try {
			Connection conn=mysqlDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(courseUpdatePstmt);
			pstmt.setString(1, obj.getTitle());
			pstmt.setString(2, obj.getPrerequisites());
			pstmt.setInt(3, obj.getCid());
			
			int countUpdate=pstmt.executeUpdate();
			
			if(countUpdate>0){
					System.out.println("Connection Update Success");
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
	public Course[] all(){
		try {
			Connection conn=mysqlDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(courseAllPstmt);
			ResultSet rs=pstmt.executeQuery();
			List<Course> arr=new ArrayList<Course>();
			while(rs.next()){
				Course obj=new Course();
				
				obj.setCid(rs.getInt("CID"));
				obj.setTitle(rs.getString("TITLE"));
				obj.setPrerequisites(rs.getString("PREREQUISITES"));
				arr.add(obj);
			}
			rs.close();
			return arr.toArray(new Course[arr.size()]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	public Course find(int id){
		try {
			Connection conn=mysqlDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(courseFindPstmt);
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			
			Course obj=new Course();
			
			while(rs.next()){
				obj.setCid(rs.getInt("CID"));
				obj.setTitle(rs.getString("TITLE"));
				obj.setPrerequisites(rs.getString("PREREQUISITES"));
				
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
			PreparedStatement pstmt=conn.prepareStatement(courseDeletePstmt);
			pstmt.setInt(1, id);
			int deleteCount=pstmt.executeUpdate();
			return deleteCount;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	
}
