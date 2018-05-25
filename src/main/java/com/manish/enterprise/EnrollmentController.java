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
public class EnrollmentController {
	
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

	/*----------------------------------------------------------------------------------------------*/

	@RequestMapping("/admin/enrollments")	
	String enrollments(HttpServletRequest request) {
		Enrollment[] enrollmentList=enrollmentDao.all();
		System.out.println("Enrollment List:\n:"+gson.toJson(enrollmentList));
		request.getSession().setAttribute("enrollmentList", enrollmentList);
		
		return "enrollments";
	}
	@RequestMapping(value="/admin/enrollment/add",method=RequestMethod.GET)	
	String enrollment_add(HttpServletRequest request) {
		Student[] studentList=studentDao.all();
		System.out.println("Student List:\n:"+gson.toJson(studentList));
		request.getSession().setAttribute("studentList", studentList);
		Course[] courseList=courseDao.all();
		System.out.println("Course List:\n:"+gson.toJson(courseList));
		request.getSession().setAttribute("courseList", courseList);

		return "enrollment_add";
	}
	@RequestMapping(value="/admin/enrollment/add",method=RequestMethod.POST)	
	public void enrollment_store(HttpServletRequest request,HttpServletResponse response) {
		Enrollment obj=new Enrollment();
		obj.setSid(Integer.parseInt(request.getParameter("sid")));
		obj.setCid(Integer.parseInt(request.getParameter("cid").toString()));
		obj.setSemester(request.getParameter("semester").toString());
		int result=enrollmentDao.insert(obj);
		 try {
			response.sendRedirect("/admin/enrollments");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	@RequestMapping(value="/admin/enrollment/view/{id}",method=RequestMethod.GET)	
	String enrollment_view(@PathVariable("id") int id,HttpServletRequest request) {
		Enrollment enrollment=enrollmentDao.find(id);
	
		Student[] studentList=studentDao.all();
		System.out.println("Student List:\n:"+gson.toJson(studentList));
		request.getSession().setAttribute("studentList", studentList);
		Course[] courseList=courseDao.all();
		System.out.println("Course List:\n:"+gson.toJson(courseList));
		request.getSession().setAttribute("courseList", courseList);

		request.getSession().setAttribute("enrollment", enrollment);
		return "enrollment_view";
	}
	@RequestMapping(value="/admin/enrollment/edit/{id}",method=RequestMethod.GET)	
	String enrollment_edit(@PathVariable("id") int id,HttpServletRequest request) {
		Enrollment enrollment=enrollmentDao.find(id);
		request.getSession().setAttribute("enrollment", enrollment);
		
		return "enrollment_edit";
	}
	@RequestMapping(value="/admin/enrollment/edit/{id}",method=RequestMethod.POST)	
	public void enrollment_update(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {
		Enrollment obj=new Enrollment();
		obj.setEid(id);
		obj.setSid(Integer.parseInt(request.getParameter("sid")));
		obj.setCid(Integer.parseInt(request.getParameter("cid").toString()));
		obj.setSemester(request.getParameter("semester").toString());
		int result=enrollmentDao.update(obj);
		 try {
			response.sendRedirect("/admin/enrollments");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	@RequestMapping(value="/admin/enrollment/delete/{id}",method=RequestMethod.GET)	
	String enrollment_delete(@PathVariable("id") int id,HttpServletRequest request) {
		Enrollment enrollment=enrollmentDao.find(id);
		request.getSession().setAttribute("enrollment", enrollment);
		return "enrollment_delete";
	}

	
	@RequestMapping(value="/admin/enrollment/delete/{id}",method=RequestMethod.POST)	
	public void enrollment_destroy(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {

		int deleteCount=enrollmentDao.delete(id);
		try {
			response.sendRedirect("/admin/enrollments");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	
	
	
	
	
	
	/*---------------------------------------------------------------------------------------------------*/
	

}
