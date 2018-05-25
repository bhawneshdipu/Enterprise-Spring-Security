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
public class AdminStudentController {
	
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
	

/*------------------------------------------------------------------------------------------------------*/
	
	@RequestMapping("/admin/students")	
	String students(HttpServletRequest request) {
		Student[] studentList=studentDao.all();
		System.out.println("Student List:\n:"+gson.toJson(studentList));
		request.getSession().setAttribute("studentList", studentList);
		
		return "students";
	}
	@RequestMapping(value="/admin/student/add",method=RequestMethod.GET)	
	String student_add() {
		return "student_add";
	}
	@RequestMapping(value="/admin/student/add",method=RequestMethod.POST)	
	public void student_store(HttpServletRequest request,HttpServletResponse response) {
		Student obj=new Student();
		obj.setFname(request.getParameter("fname").toString());
		obj.setSurname(request.getParameter("surname").toString());
		obj.setMajor(request.getParameter("major").toString());
		obj.setEmail(request.getParameter("email").toString());
		obj.setPassword(request.getParameter("password").toString());
		
		int result=studentDao.insert(obj);
		 try {
			response.sendRedirect("/admin/students");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	@RequestMapping(value="/admin/student/view/{id}",method=RequestMethod.GET)	
	String student_view(@PathVariable("id") int id,HttpServletRequest request) {
		Student student=studentDao.find(id);
		request.getSession().setAttribute("student", student);
		return "student_view";
	}
	@RequestMapping(value="/admin/student/edit/{id}",method=RequestMethod.GET)	
	String student_edit(@PathVariable("id") int id,HttpServletRequest request) {
		Student student=studentDao.find(id);
		request.getSession().setAttribute("student", student);
		
		return "student_edit";
	}
	@RequestMapping(value="/admin/student/edit/{id}",method=RequestMethod.POST)	
	public void student_update(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {
		Student obj=new Student();
		obj.setSid(id);
		obj.setFname(request.getParameter("fname").toString());
		obj.setSurname(request.getParameter("surname").toString());
		obj.setMajor(request.getParameter("major").toString());		
		obj.setEmail(request.getParameter("email").toString());
		obj.setPassword(request.getParameter("password").toString());
		int result=studentDao.update(obj);
		 try {
			response.sendRedirect("/admin/students");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	@RequestMapping(value="/admin/student/delete/{id}",method=RequestMethod.GET)	
	String student_delete(@PathVariable("id") int id,HttpServletRequest request) {
		Student student=studentDao.find(id);
		request.getSession().setAttribute("student", student);
		return "student_delete";
	}

	
	@RequestMapping(value="/admin/student/delete/{id}",method=RequestMethod.POST)	
	public void student_destroy(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {

		int deleteCount=studentDao.delete(id);
		try {
			response.sendRedirect("/admin/students");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	
	
	
	/*---------------------------------------------------------------------------------------------*/
	
		
}