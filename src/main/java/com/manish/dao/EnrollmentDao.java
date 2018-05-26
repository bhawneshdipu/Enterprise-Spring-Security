package com.manish.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.manish.model.Enrollment;

public class EnrollmentDao {

		@Autowired
		DataSource mysqlDataSource;
		
		private String tableName="enrollment";
		public final String insertValueList=" SID, CID , SEMESTER ";
		public final String valueOrderList=" EID, SID, CID , SEMESTER ";
		
		public final String values=" ?, ?, ?  ";
		public final String updateValueList=" SID=?, CID=? , SEMESTER=? ";
		
		public final String enrollmentInsertPstmt="INSERT INTO "+tableName+" ( "+insertValueList+")"+ "  VALUES ( "+values+" ) ";
		
		public final String enrollmentUpdatePstmt="UPDATE "+tableName+" SET "+updateValueList +" WHERE EID=? ";
		
		public final String enrollmentFindPstmt="SELECT * FROM "+tableName+" WHERE EID=? ";
		
		public final String enrollmentDeletePstmt="DELETE  FROM "+tableName+" WHERE EID=? ";
		
		public final String enrollmentAllPstmt="SELECT * FROM "+tableName+" ";
		
		
		public int insert(Enrollment obj) {
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(enrollmentInsertPstmt);
				pstmt.setInt(1, obj.getSid());
				pstmt.setInt(2, obj.getCid());
				pstmt.setString(3, obj.getSemester());
				
				
				int countInsert=pstmt.executeUpdate();
				
				if(countInsert>0){
						System.out.println("Enrollment Insert Success");
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
		public int update(Enrollment obj) {
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(enrollmentUpdatePstmt);
				pstmt.setInt(1, obj.getSid());
				pstmt.setInt(2, obj.getCid());
				pstmt.setString(3, obj.getSemester());
				pstmt.setInt(4, obj.getEid());
				
				int countUpdate=pstmt.executeUpdate();
				
				if(countUpdate>0){
						System.out.println("Enrollment Update Success");
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
		public Enrollment[] all(){
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(enrollmentAllPstmt);
				ResultSet rs=pstmt.executeQuery();
				List<Enrollment> arr=new ArrayList<Enrollment>();
				while(rs.next()){
					Enrollment obj=new Enrollment();
					
					obj.setEid(rs.getInt("EID"));
					obj.setSid(rs.getInt("SID"));
					
					obj.setCid(rs.getInt("CID"));
					obj.setSemester(rs.getString("SEMESTER"));
					arr.add(obj);
				}
				rs.close();
				return arr.toArray(new Enrollment[arr.size()]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			
		}
		public Enrollment find(int id){
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(enrollmentFindPstmt);
				pstmt.setInt(1, id);
				ResultSet rs=pstmt.executeQuery();
				
				Enrollment obj=new Enrollment();
				
				while(rs.next()){
					obj.setEid(rs.getInt("EID"));
					obj.setSid(rs.getInt("SID"));
					
					obj.setCid(rs.getInt("CID"));
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
				PreparedStatement pstmt=conn.prepareStatement(enrollmentDeletePstmt);
				pstmt.setInt(1, id);
				int deleteCount=pstmt.executeUpdate();
				return deleteCount;
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		}
		
		public Enrollment[] findBy(String column,String value){
			String enrollmentFindByPstmt="SELECT * FROM "+tableName+" WHERE "+column.toUpperCase()+" =? ";
			
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(enrollmentFindByPstmt);
				pstmt.setString(1, value);
				ResultSet rs=pstmt.executeQuery();
				
				
				List<Enrollment> arr=new ArrayList<Enrollment>();
				while(rs.next()){
					Enrollment obj=new Enrollment();
					
					obj.setEid(rs.getInt("EID"));
					obj.setSid(rs.getInt("SID"));
					
					obj.setCid(rs.getInt("CID"));
					obj.setSemester(rs.getString("SEMESTER"));
					
					arr.add(obj);
				}
			return arr.toArray(new Enrollment[arr.size()]);
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	
		public Enrollment[] findBy(String column,int value){
			String enrollmentFindByPstmt="SELECT * FROM "+tableName+" WHERE "+column.toUpperCase()+" =? ";
			
			try {
				Connection conn=mysqlDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(enrollmentFindByPstmt);
				pstmt.setInt(1, value);
				ResultSet rs=pstmt.executeQuery();
				
				
				List<Enrollment> arr=new ArrayList<Enrollment>();
				while(rs.next()){
					Enrollment obj=new Enrollment();
					
					obj.setEid(rs.getInt("EID"));
					obj.setSid(rs.getInt("SID"));
					
					obj.setCid(rs.getInt("CID"));
					obj.setSemester(rs.getString("SEMESTER"));
					
					arr.add(obj);
				}
			return arr.toArray(new Enrollment[arr.size()]);
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	
}
