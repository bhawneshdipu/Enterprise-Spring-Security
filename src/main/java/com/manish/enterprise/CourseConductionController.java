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
import com.manish.dao.CourseConductionDao;
import com.manish.dao.CourseConductionDao;
import com.manish.dao.CourseDao;
import com.manish.dao.LecturerDao;
import com.manish.dao.StudentDao;
import com.manish.model.Assessment;
import com.manish.model.Course;
import com.manish.model.CourseConduction;
import com.manish.model.Lecturer;
import com.manish.model.Student;

@Controller
public class CourseConductionController{
	
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
	CourseConductionDao courseAssessmentDao;
	
	@Autowired
	CourseConductionDao courseConductionDao;
	
	@Autowired
	StudentDao studentDao;

	/*-------------------------------------------123---------------------------------------------------*/

	@RequestMapping("/admin/course_conductions")	
	String courseConductions(HttpServletRequest request) {
		CourseConduction[] courseConductionList=courseConductionDao.all();
		System.out.println("CourseConduction List:\n:"+gson.toJson(courseConductionList));
		request.getSession().setAttribute("courseConductionList", courseConductionList);
		
		return "course_conductions";
	}
	@RequestMapping(value="/admin/course_conduction/add",method=RequestMethod.GET)	
	String courseConduction_add(HttpServletRequest request) {
		Course[] courseList=courseDao.all();
		System.out.println("Course List:\n:"+gson.toJson(courseList));
		request.getSession().setAttribute("courseList", courseList);
		Lecturer[] lecturerList=lecturerDao.all();
		System.out.println("lecturer  List:\n:"+gson.toJson(lecturerList));
		request.getSession().setAttribute("lecturerList", lecturerList);

		return "course_conduction_add";
	}
	@RequestMapping(value="/admin/course_conduction/add",method=RequestMethod.POST)	
	public void courseConduction_store(HttpServletRequest request,HttpServletResponse response) {
		CourseConduction obj=new CourseConduction();
		obj.setLid(Integer.parseInt(request.getParameter("lid")));
		obj.setCid(Integer.parseInt(request.getParameter("cid").toString()));
		obj.setSemester(request.getParameter("semester").toString());
		obj.setCapacity(Integer.parseInt(request.getParameter("capacity").toString()));
		
		int result=courseConductionDao.insert(obj);
		 try {
			response.sendRedirect("/admin/course_conductions");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	@RequestMapping(value="/admin/course_conduction/view/{id}",method=RequestMethod.GET)	
	String courseConduction_view(@PathVariable("id") int id,HttpServletRequest request) {
		CourseConduction course_conduction=courseConductionDao.find(id);
	
		Course[] courseList=courseDao.all();
		System.out.println("Course List:\n:"+gson.toJson(courseList));
		request.getSession().setAttribute("courseList", courseList);
		Lecturer[] lecturerList=lecturerDao.all();
		System.out.println("lecturer  List:\n:"+gson.toJson(lecturerList));
		request.getSession().setAttribute("lecturerList", lecturerList);
		request.getSession().setAttribute("course_conduction", course_conduction);

		return "course_conduction_view";
	}
	@RequestMapping(value="/admin/course_conduction/edit/{id}",method=RequestMethod.GET)	
	String courseConduction_edit(@PathVariable("id") int id,HttpServletRequest request) {
		CourseConduction course_conduction=courseConductionDao.find(id);
		
		Course[] courseList=courseDao.all();
		System.out.println("Course List:\n:"+gson.toJson(courseList));
		request.getSession().setAttribute("courseList", courseList);
		Lecturer[] lecturerList=lecturerDao.all();
		System.out.println("lecturer  List:\n:"+gson.toJson(lecturerList));
		request.getSession().setAttribute("lecturerList", lecturerList);
		request.getSession().setAttribute("course_conduction", course_conduction);
		
		
		return "course_conduction_edit";
	}
	@RequestMapping(value="/admin/course_conduction/edit/{id}",method=RequestMethod.POST)	
	public void courseConduction_update(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {
		CourseConduction obj=new CourseConduction();
		obj.setCcid(id);
		obj.setLid(Integer.parseInt(request.getParameter("lid")));
		obj.setCid(Integer.parseInt(request.getParameter("cid").toString()));
		obj.setSemester(request.getParameter("semester").toString());
		obj.setCapacity(Integer.parseInt(request.getParameter("capacity").toString()));
		int result=courseConductionDao.update(obj);
		 try {
			response.sendRedirect("/admin/course_conductions");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	@RequestMapping(value="/admin/course_conduction/delete/{id}",method=RequestMethod.GET)	
	String courseConduction_delete(@PathVariable("id") int id,HttpServletRequest request) {
		CourseConduction course_conduction=courseConductionDao.find(id);
		
		Course[] courseList=courseDao.all();
		System.out.println("Course List:\n:"+gson.toJson(courseList));
		request.getSession().setAttribute("courseList", courseList);
		Lecturer[] lecturerList=lecturerDao.all();
		System.out.println("lecturer  List:\n:"+gson.toJson(lecturerList));
		request.getSession().setAttribute("lecturerList", lecturerList);

		request.getSession().setAttribute("course_conduction", course_conduction);
		return "course_conduction_delete";
	}

	
	@RequestMapping(value="/admin/course_conduction/delete/{id}",method=RequestMethod.POST)	
	public void courseConduction_destroy(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {

		int deleteCount=courseConductionDao.delete(id);
		try {
			response.sendRedirect("/admin/course_conductions");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	
	
	
	
	
	
	/*---------------------------------------------------------------------------------------------------*/
	

}
