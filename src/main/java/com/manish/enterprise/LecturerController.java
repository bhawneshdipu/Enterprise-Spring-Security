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
public class LecturerController {
	
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
	
	
	@RequestMapping("/lecturer/courses")	
	String courses() {
		return "courses";
	}
	@RequestMapping("/lecturer/course_conduction/view")	
	String course_conduction_view() {
		return "course_conduction_view";
	}
	@RequestMapping("/lecturer/course_conduction/edit_capacity")	
	String course_conduction_edit_capacity() {
		return "edit_capacity";
	}
	
	
	@RequestMapping("/teacher/assissment/view")	
	String assissment_view() {
		return "assissment_view";
	}
	@RequestMapping("/teacher/assissment/add_marks")	
	String assissment_add_marks() {
		return "assissment_add_marks";
	}

}
