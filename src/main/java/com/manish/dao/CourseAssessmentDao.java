package com.manish.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.manish.model.CourseAssessment;

public class CourseAssessmentDao {

		@Autowired
		DataSource mysqlDataSource;
		
		private String tableName="course_assessment";
		public final String insertValueList=" CID, AID , SEMESTER ";
		public final String valueOrderList=" CAID, CID, AID , SEMESTER ";
		
		public final String values=" ?, ?, ?  ";
		public final String updateValueList=" CID=?, AID=? , SEMESTER=? ";
		
		public final String courseAssessmentInsertPstmt="INSERT INTO "+tableName+" ( "+insertValueList+")"+ "  VALUES ( "+values+" ) ";
		
		public final String courseAssessmentUpdatePstmt="UPDATE "+tableName+" SET "+updateValueList +" WHERE CAID=? ";
		
		public final String courseAssessmentFindPstmt="SELECT * FROM "+tableName+" WHERE CAID=? ";
		
		public final String courseAssessmentDeletePstmt="DELETE  FROM "+tableName+" WHERE CAID=? ";
		
		public final String courseAssessmentAllPstmt="SELECT * FROM "+tableName+" ";
		
		
		public int insert(CourseAssessment obj) {
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(courseAssessmentInsertPstmt);
				pstmt.setInt(1, obj.getCid());
				pstmt.setInt(2, obj.getAid());
				pstmt.setString(3, obj.getSemester());
				
				
				int countInsert=pstmt.executeUpdate();
				
				if(countInsert>0){
						System.out.println("CourseAssessment Insert Success");
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
		public int update(CourseAssessment obj) {
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(courseAssessmentUpdatePstmt);
				pstmt.setInt(1, obj.getCid());
				pstmt.setInt(2, obj.getAid());
				pstmt.setString(3, obj.getSemester());
				pstmt.setInt(4, obj.getCaid());
				
				int countUpdate=pstmt.executeUpdate();
				
				if(countUpdate>0){
						System.out.println("CourseAssessment Update Success");
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
		public CourseAssessment[] all(){
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(courseAssessmentAllPstmt);
				ResultSet rs=pstmt.executeQuery();
				List<CourseAssessment> arr=new ArrayList<CourseAssessment>();
				while(rs.next()){
					CourseAssessment obj=new CourseAssessment();
					
					obj.setCid(rs.getInt("CID"));
					obj.setAid(rs.getInt("AID"));
					
					obj.setCaid(rs.getInt("CAID"));
					obj.setSemester(rs.getString("SEMESTER"));
					arr.add(obj);
				}
				rs.close();
				return arr.toArray(new CourseAssessment[arr.size()]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			
		}
		public CourseAssessment find(int id){
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(courseAssessmentFindPstmt);
				pstmt.setInt(1, id);
				ResultSet rs=pstmt.executeQuery();
				
				CourseAssessment obj=new CourseAssessment();
				
				while(rs.next()){
					obj.setCid(rs.getInt("CID"));
					obj.setAid(rs.getInt("AID"));
					
					obj.setCaid(rs.getInt("CAID"));
					obj.setSemester(rs.getString("SEMESTER"));
					
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
				PreparedStatement pstmt=conn.prepareStatement(courseAssessmentDeletePstmt);
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
