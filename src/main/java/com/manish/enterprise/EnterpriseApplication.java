package com.manish.enterprise;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@Configuration
@SpringBootApplication
public class EnterpriseApplication extends SpringBootServletInitializer {

	@Autowired
	DataSource mysqlDataSource;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EnterpriseApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(EnterpriseApplication.class, args);
	}
	
	
}
