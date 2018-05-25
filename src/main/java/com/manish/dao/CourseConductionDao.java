package com.manish.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.manish.model.CourseConduction;

public class CourseConductionDao {

		@Autowired
		DataSource mysqlDataSource;
		
		private String tableName="course_conduction";
		public final String insertValueList=" LID , CID, SEMESTER , CAPACITY  ";
		public final String valueOrderList=" CCID, LID , CID, SEMESTER , CAPACITY ";
		
		public final String values=" ?, ?, ? , ? ";
		public final String updateValueList=" LID=? , CID =? , SEMESTER=? , CAPACITY=? ";
		
		public final String courseConductionInsertPstmt="INSERT INTO "+tableName+" ( "+insertValueList+")"+ "  VALUES ( "+values+" ) ";
		
		public final String courseConductionUpdatePstmt="UPDATE "+tableName+" SET "+updateValueList +" WHERE CAID=? ";
		
		public final String courseConductionFindPstmt="SELECT * FROM "+tableName+" WHERE CCID=? ";
		
		public final String courseConductionDeletePstmt="DELETE  FROM "+tableName+" WHERE CCID=? ";
		
		public final String courseConductionAllPstmt="SELECT * FROM "+tableName+" ";
		
		
		public int insert(CourseConduction obj) {
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(courseConductionInsertPstmt);
				pstmt.setInt(1, obj.getLid());
				pstmt.setInt(2, obj.getCid());
				pstmt.setString(3, obj.getSemester());
				pstmt.setInt(4, obj.getCapacity());
				
				
				int countInsert=pstmt.executeUpdate();
				
				if(countInsert>0){
						System.out.println("CourseConduction Insert Success");
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
		public int update(CourseConduction obj) {
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(courseConductionUpdatePstmt);
				pstmt.setInt(1, obj.getLid());
				pstmt.setInt(2, obj.getCid());
				pstmt.setString(3, obj.getSemester());
				pstmt.setInt(4, obj.getCapacity());
				pstmt.setInt(5, obj.getCcid());
				
				int countUpdate=pstmt.executeUpdate();
				
				if(countUpdate>0){
						System.out.println("CourseConduction Update Success");
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
		public CourseConduction[] all(){
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(courseConductionAllPstmt);
				ResultSet rs=pstmt.executeQuery();
				List<CourseConduction> arr=new ArrayList<CourseConduction>();
				while(rs.next()){
					CourseConduction obj=new CourseConduction();
					
					obj.setLid(rs.getInt("LID"));
					obj.setCid(rs.getInt("CID"));
					
					obj.setCcid(rs.getInt("CCID"));
					obj.setSemester(rs.getString("SEMESTER"));

					obj.setCapacity(rs.getInt("CAPACITY"));
					arr.add(obj);
				}
				rs.close();
				return arr.toArray(new CourseConduction[arr.size()]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			
		}
		public CourseConduction find(int id){
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(courseConductionFindPstmt);
				pstmt.setInt(1, id);
				ResultSet rs=pstmt.executeQuery();
				
				CourseConduction obj=new CourseConduction();
				
				while(rs.next()){
					obj.setLid(rs.getInt("LID"));
					obj.setCid(rs.getInt("CID"));
					
					obj.setCcid(rs.getInt("CCID"));
					obj.setSemester(rs.getString("SEMESTER"));

					obj.setCapacity(rs.getInt("CAPACITY"));
					
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
				PreparedStatement pstmt=conn.prepareStatement(courseConductionDeletePstmt);
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
