package com.manish.enterprise;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.manish.dao.AssessmentDao;
import com.manish.dao.CourseAssessmentDao;
import com.manish.dao.CourseConductionDao;
import com.manish.dao.CourseDao;
import com.manish.dao.EnrollmentDao;
import com.manish.dao.LecturerDao;
import com.manish.dao.StudentDao;
import com.manish.model.Assessment;
import com.manish.model.Course;
import com.manish.model.Enrollment;
import com.manish.model.Lecturer;
import com.manish.model.Student;

@Controller
public class AdminController {
	
	@Autowired
	DataSource mysqlDataSource;
	@Autowired
	Gson gson;
	
	@Autowired
	LecturerDao lecturerDao;
	
	@Autowired
	CourseDao courseDao;
	
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
	
		
	

	
	
}
