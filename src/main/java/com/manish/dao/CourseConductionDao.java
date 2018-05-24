package com.manish.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class CourseConductionDao {

	@Autowired
	DataSource mysqlDataSource;
	
	private String tableName="lecturer";
	
}
