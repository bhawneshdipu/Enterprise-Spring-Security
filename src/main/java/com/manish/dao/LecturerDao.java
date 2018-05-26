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

public class LecturerDao {

	@Autowired
	DataSource mysqlDataSource;
	
	private String tableName="lecturer";
	public final String insertValueList=" GNAME, SURNAME , EMAIL , CAMPUS, PASSWORD ";
	public final String valueOrderList=" LID, GNAME, SURNAME , EMAIL , CAMPUS , PASSWORD ";
	
	public final String values=" ? , ?, ? , ?  , ? ";
	public final String updateValueList=" GNAME=?, SURNAME=? , EMAIL=? , CAMPUS=? , PASSWORD=? ";
	
	public final String lecturerInsertPstmt="INSERT INTO "+tableName+" ( "+insertValueList+")"+ "  VALUES ( "+values+" ) ";
	
	public final String lecturerUpdatePstmt="UPDATE "+tableName+" SET "+updateValueList +" WHERE LID=? ";
	
	public final String lecturerFindPstmt="SELECT * FROM "+tableName+" WHERE LID=? ";
	
	public final String lecturerDeletePstmt="DELETE  FROM "+tableName+" WHERE LID=? ";
	
	public final String lecturerAllPstmt="SELECT * FROM "+tableName+" ";
	
	public final String lecturerLoginPstmt="SELECT * FROM "+tableName+" WHERE EMAIL=? AND PASSWORD=? ";
	
	
	public int insert(Lecturer obj) {
		try {
			Connection conn=mysqlDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(lecturerInsertPstmt);
			pstmt.setString(1, obj.getGname());
			pstmt.setString(2, obj.getSurname());
			pstmt.setString(3, obj.getEmail());
			pstmt.setString(4, obj.getCampus());
			pstmt.setString(5, obj.getPassword());
			
			
			
			int countInsert=pstmt.executeUpdate();
			
			if(countInsert>0){
					System.out.println("Lecturer Insert Success");
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
	public int update(Lecturer obj) {
		try {
			Connection conn=mysqlDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(lecturerUpdatePstmt);
			pstmt.setString(1, obj.getGname());
			pstmt.setString(2, obj.getSurname());
			pstmt.setString(3, obj.getEmail());
			pstmt.setString(4, obj.getCampus());
			pstmt.setString(5, obj.getPassword());
			pstmt.setInt(6, obj.getLid());
			
			
			int countUpdate=pstmt.executeUpdate();
			
			if(countUpdate>0){
					System.out.println("Lecturer  Update Success");
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
	public Lecturer[] all(){
		try {
			Connection conn=mysqlDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(lecturerAllPstmt);
			ResultSet rs=pstmt.executeQuery();
			List<Lecturer> arr=new ArrayList<Lecturer>();
			while(rs.next()){
				Lecturer obj=new Lecturer();
				
				obj.setLid(rs.getInt("LID"));
				obj.setGname(rs.getString("GNAME"));
				obj.setSurname(rs.getString("SURNAME"));
				obj.setEmail(rs.getString("EMAIL"));
				obj.setCampus(rs.getString("CAMPUS"));
				
				arr.add(obj);
			}
			rs.close();
			return arr.toArray(new Lecturer[arr.size()]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	public Lecturer find(int id){
		try {
			Connection conn=mysqlDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(lecturerFindPstmt);
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			
			Lecturer obj=new Lecturer();
			
			while(rs.next()){
				obj.setLid(rs.getInt("LID"));
				obj.setGname(rs.getString("GNAME"));
				obj.setSurname(rs.getString("SURNAME"));
				obj.setEmail(rs.getString("EMAIL"));
				obj.setCampus(rs.getString("CAMPUS"));
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
			PreparedStatement pstmt=conn.prepareStatement(lecturerDeletePstmt);
			pstmt.setInt(1, id);
			int deleteCount=pstmt.executeUpdate();
			return deleteCount;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	public Lecturer login(String email,String password) {
		try {
			Connection conn=mysqlDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(lecturerLoginPstmt);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			
			
			while(rs.next()){
				Lecturer obj=new Lecturer();
				
				obj.setLid(rs.getInt("LID"));
				obj.setGname(rs.getString("GNAME"));
				obj.setSurname(rs.getString("SURNAME"));
				obj.setEmail(rs.getString("EMAIL"));
				obj.setCampus(rs.getString("CAMPUS"));
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
	
	public Lecturer getByEmail(String email) {
		String lecturerByEmailPstmt="SELECT * FROM "+tableName+" WHERE EMAIL=? ";
		
		try {
			Connection conn=mysqlDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(lecturerByEmailPstmt);
			pstmt.setString(1, email);
			ResultSet rs=pstmt.executeQuery();
			
			
			while(rs.next()){
				Lecturer obj=new Lecturer();
				
				obj.setLid(rs.getInt("LID"));
				obj.setGname(rs.getString("GNAME"));
				obj.setSurname(rs.getString("SURNAME"));
				obj.setEmail(rs.getString("EMAIL"));
				obj.setCampus(rs.getString("CAMPUS"));
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
