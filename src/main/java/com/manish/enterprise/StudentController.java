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
import com.manish.model.Student;

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
	
	@Autowired
	CourseDao courseDao;
	
	@Autowired
	Gson gson;
	
	

	@RequestMapping("/student/enrolls")	
	String enrolls(HttpServletRequest request) {
		Enrollment[] enrollList=enrollmentDao.findBy("SID",Integer.parseInt(request.getSession().getAttribute("student_id").toString()));
		System.out.println("Enrollment List:\n:"+gson.toJson(enrollList));
		request.getSession().setAttribute("enrollList", enrollList);
		
		return "enrolls";
	}

	
	@RequestMapping(value="/student/enroll/add",method=RequestMethod.GET)	
	String enroll_add(HttpServletRequest request) {
		System.out.println("Student: "+request.getSession().getAttribute("login_student"));
		Course[] courseList=courseDao.all();
		System.out.println("Course List:\n:"+gson.toJson(courseList));
		request.getSession().setAttribute("courseList", courseList);

		return "enroll_add";
	}
	@RequestMapping(value="/student/enroll/add",method=RequestMethod.POST)	
	public void enroll_store(HttpServletRequest request,HttpServletResponse response) {
		Enrollment obj=new Enrollment();
		Student student=(Student)request.getSession().getAttribute("login_student");
		obj.setSid(student.getSid());
		obj.setCid(Integer.parseInt(request.getParameter("cid").toString()));
		obj.setSemester(request.getParameter("semester").toString());
		int result=enrollmentDao.insert(obj);
		 try {
			response.sendRedirect("/student/enrolls");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}

	@RequestMapping(value="/student/enroll/view/{id}",method=RequestMethod.GET)	
	String enrollment_view(@PathVariable("id") int id,HttpServletRequest request) {
		Enrollment enroll=enrollmentDao.find(id);
	
		Course[] courseList=courseDao.all();
		System.out.println("Course List:\n:"+gson.toJson(courseList));
		request.getSession().setAttribute("courseList", courseList);

		request.getSession().setAttribute("enroll", enroll);
		return "enroll_view";
	}
	@RequestMapping(value="/student/assessments",method=RequestMethod.GET)	
	String assessments(HttpServletRequest request) {
		
		Assessment[] assessmentList=assessmentDao.findBy("SID",Integer.parseInt(request.getSession().getAttribute("student_id").toString()));
		request.getSession().setAttribute("assessmentList", assessmentList);
		
		return "student_assessments";
	}

	@RequestMapping(value="/student/assessment/view/{id}",method=RequestMethod.GET)	
	String assessment_edit(@PathVariable("id") int id,HttpServletRequest request) {
		Assessment assessment=assessmentDao.find(id);
		request.getSession().setAttribute("assessment", assessment);
		Student[] studentList=studentDao.all();
		System.out.println("Student List:\n:"+gson.toJson(studentList));
		request.getSession().setAttribute("studentList", studentList);
		Enrollment[] enrollmentList=enrollmentDao.all();
		System.out.println("Enrollment List:\n:"+gson.toJson(enrollmentList));
		request.getSession().setAttribute("enrollmentList", enrollmentList);
		
		return "student_assessment_view";
	}

	
}
