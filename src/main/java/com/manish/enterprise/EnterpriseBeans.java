package com.manish.enterprise;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.gson.Gson;
import com.manish.dao.AssessmentDao;
import com.manish.dao.CourseAssessmentDao;
import com.manish.dao.CourseConductionDao;
import com.manish.dao.CourseDao;
import com.manish.dao.EnrollmentDao;
import com.manish.dao.LecturerDao;
import com.manish.dao.StudentDao;
@Configuration
public class EnterpriseBeans {
	@Autowired
	private Environment env;
	
	@Bean
	public Gson gson() {
		return new Gson();
	}
	
	 @Bean
	 @Primary
     public DataSource mysqlDataSource() throws SQLException{
		System.out.println("Init:DataSource:::::::::::\n");
        
		DriverManagerDataSource mysqlDataSource = new DriverManagerDataSource();
        mysqlDataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        mysqlDataSource.setUrl(env.getProperty("spring.datasource.url"));
        mysqlDataSource.setUsername(env.getProperty("spring.datasource.username"));
        mysqlDataSource.setPassword(env.getProperty("spring.datasource.password"));
        return mysqlDataSource;
     }
	 
	 @Bean
	 public LecturerDao lecturerDao() {
		System.out.println("Init:Lecturer Dao:::::::::::\n");
		return new LecturerDao();
     }
	 
	@Bean 
	public CourseDao coursesDao() {
		System.out.println("Init:CourseDao :::::::::::\n");
		return new CourseDao ();
     	
	}
	@Bean 
	public 	AssessmentDao assessmentDao() {
		System.out.println("Init:AssessmentDao :::::::::::\n");
		return new AssessmentDao  ();
	}
		
	@Bean 
	public CourseAssessmentDao courseAssessmentDao() {
		System.out.println("Init:CourseAssessmentDao :::::::::::\n");
		return new CourseAssessmentDao();
	};
	
	@Bean 
	public CourseConductionDao courseConductionDao() {
		System.out.println("Init:CourseConductionDao:::::::::::\n");
		return new CourseConductionDao();
	};
		
	@Bean 
	public EnrollmentDao enrollmentDao() {
		System.out.println("Init:EnrollmentDao:::::::::::\n");
		return new EnrollmentDao();	
	};
		
	@Bean 
	public StudentDao studentDao() {
		System.out.println("Init:StudentDao:::::::::::\n");
		return new StudentDao();
	};
		
	
	@Bean
	public InternalResourceViewResolver jspViewResolver() {
	    InternalResourceViewResolver resolver= new InternalResourceViewResolver();
	    resolver.setPrefix("/WEB-INF/view/");
	    resolver.setSuffix(".jsp");
	    return resolver;
	}  

	
	@Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
	@Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
	@Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }
	
	
}
