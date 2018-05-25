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
public class StudentController {
	
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
	
	
	@RequestMapping("/student/enroll")	
	String enroll() {
		return "enroll";
	}
	@RequestMapping("/student/enroll/view")	
	String enroll_view() {
		return "enroll_add";
	}
	@RequestMapping("/student/enroll/add")	
	String enroll_add() {
		return "enroll_add";
	}
	
	
	@RequestMapping("/student/assissment/view")	
	String assissment_view() {
		return "assissment_view";
	}

}
