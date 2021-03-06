package com.manish.enterprise;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manish.dao.AssessmentDao;
import com.manish.dao.CourseAssessmentDao;
import com.manish.dao.CourseConductionDao;
import com.manish.dao.CourseDao;
import com.manish.dao.EnrollmentDao;
import com.manish.dao.LecturerDao;
import com.manish.dao.StudentDao;

@Controller
public class EnterpriseController {
	
	@Autowired
	DataSource mysqlDataSource;
	
	@Autowired
	LecturerDao lecturerDao;
	
	@Autowired
	CourseDao coursesDao;
	
	@Autowired
	AssessmentDao assessmentDao;
	
	@Autowired
	CourseAssessmentDao courseAssessmentDao;
	
	@Autowired
	CourseConductionDao courseConductionDao;
	
	@Autowired
	EnrollmentDao enrollmentDao;
	
	@Autowired
	StudentDao studentDao;
	
	@RequestMapping("/")
	String index() {
		return "index";
	}
	@RequestMapping("/login")	
	String login() {
		return "login";
	}
	@RequestMapping("/admin")	
	String admin() {
		return "admin";
	}
	
		
	@RequestMapping("/lecturer")	
	String lecturer() {
		return "lecturer";
	}
	
	@RequestMapping("/student")	
	String student() {
		return "student";
	}
	@RequestMapping("/about-us")	
	String about_us() {
		return "aboutus";
	}
	
	
	

}
