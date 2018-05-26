package com.manish.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.manish.model.Assessment;

public class AssessmentDao {

		@Autowired
		DataSource mysqlDataSource;
		
		private String tableName="assessment";
		public final String insertValueList=" SID, EID , A1 , A2 ";
		public final String valueOrderList=" AID , SID, EID , A1 , A2 ";
		
		public final String values=" ?, ?, ? , ?  ";
		public final String updateValueList=" SID=? , EID=?  , A1=?  , A2=? ";
		
		public final String assessmentInsertPstmt="INSERT INTO "+tableName+" ( "+insertValueList+")"+ "  VALUES ( "+values+" ) ";
		
		public final String assessmentUpdatePstmt="UPDATE "+tableName+" SET "+updateValueList +" WHERE AID=? ";
		
		public final String assessmentFindPstmt="SELECT * FROM "+tableName+" WHERE AID=? ";
		
		public final String assessmentDeletePstmt="DELETE  FROM "+tableName+" WHERE AID=? ";
		
		public final String assessmentAllPstmt="SELECT * FROM "+tableName+" ";
		
		
		public int insert(Assessment obj) {
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(assessmentInsertPstmt);
				
				pstmt.setInt(1, obj.getSid());
				pstmt.setInt(2, obj.getEid());
				
				pstmt.setInt(3, obj.getA1());
				pstmt.setInt(4, obj.getA2());
				
				
				
				int countInsert=pstmt.executeUpdate();
				
				if(countInsert>0){
						System.out.println("Assessment Insert Success");
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
		public int update(Assessment obj) {
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(assessmentUpdatePstmt);
				pstmt.setInt(1, obj.getSid());
				pstmt.setInt(2, obj.getEid());
				
				pstmt.setInt(3, obj.getA1());
				pstmt.setInt(4, obj.getA2());
				pstmt.setInt(5, obj.getAid());
				
				int countUpdate=pstmt.executeUpdate();
				
				if(countUpdate>0){
						System.out.println("Assessment Update Success");
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
		public Assessment[] all(){
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(assessmentAllPstmt);
				ResultSet rs=pstmt.executeQuery();
				List<Assessment> arr=new ArrayList<Assessment>();
				while(rs.next()){
					Assessment obj=new Assessment();
					
					obj.setAid(rs.getInt("AID"));
					obj.setSid(rs.getInt("SID"));
					
					obj.setEid(rs.getInt("EID"));

					obj.setA1(rs.getInt("A1"));
					obj.setA2(rs.getInt("A2"));
					arr.add(obj);
				}
				rs.close();
				return arr.toArray(new Assessment[arr.size()]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			
		}
		public Assessment find(int id){
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(assessmentFindPstmt);
				pstmt.setInt(1, id);
				ResultSet rs=pstmt.executeQuery();
				
				Assessment obj=new Assessment();
				
				while(rs.next()){
					obj.setAid(rs.getInt("AID"));
					obj.setSid(rs.getInt("SID"));
					
					obj.setEid(rs.getInt("EID"));

					obj.setA1(rs.getInt("A1"));
					obj.setA2(rs.getInt("A2"));
										
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
				PreparedStatement pstmt=conn.prepareStatement(assessmentDeletePstmt);
				pstmt.setInt(1, id);
				int deleteCount=pstmt.executeUpdate();
				return deleteCount;
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		}
		public Assessment[] findBy(String column,String value){
			String assessmentFindByPstmt="SELECT * FROM "+tableName+" WHERE "+column.toUpperCase()+" = ?";
			
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(assessmentFindByPstmt);
				pstmt.setString(1, value);
				ResultSet rs=pstmt.executeQuery();
				List<Assessment> arr=new ArrayList<Assessment>();
				while(rs.next()){
					Assessment obj=new Assessment();
					
					obj.setAid(rs.getInt("AID"));
					obj.setSid(rs.getInt("SID"));
					
					obj.setEid(rs.getInt("EID"));

					obj.setA1(rs.getInt("A1"));
					obj.setA2(rs.getInt("A2"));
					arr.add(obj);
				}
				rs.close();
				return arr.toArray(new Assessment[arr.size()]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			
		}
		public Assessment[] findBy(String column,int value){
			String assessmentFindByPstmt="SELECT * FROM "+tableName+" WHERE "+column.toUpperCase()+" = ?";
			
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(assessmentFindByPstmt);
				pstmt.setInt(1, value);
				ResultSet rs=pstmt.executeQuery();
				List<Assessment> arr=new ArrayList<Assessment>();
				while(rs.next()){
					Assessment obj=new Assessment();
					
					obj.setAid(rs.getInt("AID"));
					obj.setSid(rs.getInt("SID"));
					
					obj.setEid(rs.getInt("EID"));

					obj.setA1(rs.getInt("A1"));
					obj.setA2(rs.getInt("A2"));
					arr.add(obj);
				}
				rs.close();
				return arr.toArray(new Assessment[arr.size()]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		}
		
		public Assessment[] findLecturerAssessments(int lid){
			//String assessmentFindByLecturerPstmt="SELECT * FROM "+tableName+" JOIN enrollment using(eid) JOIN course_conduction using(cid) WHERE LID= ?";
			
			String assessmentFindByLecturerPstmt="select * from assessment where sid in "
					+ "( select distinct sid from enrollment " + 
					" where cid in ( 			" + 
					"select distinct cid from course_conduction where lid=?" + 
					")" + 
					")";
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(assessmentFindByLecturerPstmt);
				pstmt.setInt(1, lid);
				ResultSet rs=pstmt.executeQuery();
				List<Assessment> arr=new ArrayList<Assessment>();
				while(rs.next()){
					Assessment obj=new Assessment();
					
					obj.setAid(rs.getInt("AID"));
					obj.setSid(rs.getInt("SID"));
					
					obj.setEid(rs.getInt("EID"));

					obj.setA1(rs.getInt("A1"));
					obj.setA2(rs.getInt("A2"));
					arr.add(obj);
				}
				rs.close();
				return arr.toArray(new Assessment[arr.size()]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		}
		

		
		
	
}
