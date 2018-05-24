package com.manish.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manish.model.Lecturer;

@Controller
public class LecturerDao {
	
	@Autowired
	DataSource mysqlDataSource;
	
	private String tableName="lecturer";
	
/*
		 * get all the list of Lecturers
		 */
		
		public Lecturer[] all() {
			
			try {
				Connection con=mysqlDataSource.getConnection();
				PreparedStatement pstmt=con.prepareStatement("SELECT * FROM "+tableName+" ");
				
				ResultSet result = pstmt.executeQuery();
				List<Lecturer> list=new ArrayList<Lecturer>();
			
				
				while(result.next()) {
					Lecturer l=new Lecturer();
					l.setLId(result.getInt("lid"));
					l.setEmail(result.getString("email"));
					l.setGname(result.getString("gname"));
					l.setSurname(result.getString("surname"));
					l.setPassword(result.getString("password"));
					l.setPassword(result.getString("campus"));
					
					list.add(l);
				}
				return list.toArray(new Lecturer[list.size()]);	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}	
		}
		public int save(Lecturer l) {
			Connection con;
			try {
				con = mysqlDataSource.getConnection();
				PreparedStatement pstmt=con.prepareStatement("INSERT into "+tableName+"  ");
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
		}
		public Lecturer find(int lid) {
				return new Lecturer();
		}
		
}
