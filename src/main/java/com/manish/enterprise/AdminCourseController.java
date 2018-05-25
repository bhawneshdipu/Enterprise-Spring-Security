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
public class AdminCourseController {
	
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

	@RequestMapping("/admin/courses")	
	String courses(HttpServletRequest request) {
		Course[] courseList=courseDao.all();
		System.out.println("Course List:\n:"+gson.toJson(courseList));
		request.getSession().setAttribute("courseList", courseList);
		
		return "courses";
	}
	@RequestMapping(value="/admin/course/add",method=RequestMethod.GET)	
	String course_add() {
		return "course_add";
	}
	@RequestMapping(value="/admin/course/add",method=RequestMethod.POST)	
	public void course_store(HttpServletRequest request,HttpServletResponse response) {
		Course obj=new Course();
		obj.setTitle(request.getParameter("title").toString());
		obj.setPrerequisites(request.getParameter("prerequisites").toString());
		int result=courseDao.insert(obj);
		 try {
			response.sendRedirect("/admin/courses");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	@RequestMapping(value="/admin/course/view/{id}",method=RequestMethod.GET)	
	String course_view(@PathVariable("id") int id,HttpServletRequest request) {
		Course course=courseDao.find(id);
		request.getSession().setAttribute("course", course);
		return "course_view";
	}
	@RequestMapping(value="/admin/course/edit/{id}",method=RequestMethod.GET)	
	String course_edit(@PathVariable("id") int id,HttpServletRequest request) {
		Course course=courseDao.find(id);
		request.getSession().setAttribute("course", course);
		
		return "course_edit";
	}
	@RequestMapping(value="/admin/course/edit/{id}",method=RequestMethod.POST)	
	public void course_update(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {
		Course obj=new Course();
		obj.setCid(id);
		obj.setTitle(request.getParameter("title").toString());
		obj.setPrerequisites(request.getParameter("prerequisites").toString());
		int result=courseDao.update(obj);
		 try {
			response.sendRedirect("/admin/courses");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	@RequestMapping(value="/admin/course/delete/{id}",method=RequestMethod.GET)	
	String course_delete(@PathVariable("id") int id,HttpServletRequest request) {
		Course course=courseDao.find(id);
		request.getSession().setAttribute("course", course);
		return "course_delete";
	}

	
	@RequestMapping(value="/admin/course/delete/{id}",method=RequestMethod.POST)	
	public void course_destroy(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {

		int deleteCount=courseDao.delete(id);
		try {
			response.sendRedirect("/admin/courses");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
		
}
