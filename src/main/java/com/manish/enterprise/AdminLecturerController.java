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
public class AdminLecturerController {
	
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
	
	@RequestMapping("/admin/lecturers")	
	String lecturers(HttpServletRequest request) {
		Lecturer[] lecturerList=lecturerDao.all();
		System.out.println("Lecturer List:\n:"+gson.toJson(lecturerList));
		request.getSession().setAttribute("lecturerList", lecturerList);
		
		return "lecturers";
	}
	@RequestMapping(value="/admin/lecturer/add",method=RequestMethod.GET)	
	String lecturer_add() {
		return "lecturer_add";
	}
	@RequestMapping(value="/admin/lecturer/add",method=RequestMethod.POST)	
	public void lecturer_store(HttpServletRequest request,HttpServletResponse response) {
		Lecturer obj=new Lecturer();
		obj.setGname(request.getParameter("gname").toString());
		obj.setSurname(request.getParameter("surname").toString());
		obj.setCampus(request.getParameter("campus").toString());
		obj.setEmail(request.getParameter("email").toString());
		obj.setPassword(request.getParameter("password").toString());
		
		int result=lecturerDao.insert(obj);
		 try {
			response.sendRedirect("/admin/lecturers");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	@RequestMapping(value="/admin/lecturer/view/{id}",method=RequestMethod.GET)	
	String lecturer_view(@PathVariable("id") int id,HttpServletRequest request) {
		Lecturer lecturer=lecturerDao.find(id);
		request.getSession().setAttribute("lecturer", lecturer);
		return "lecturer_view";
	}
	@RequestMapping(value="/admin/lecturer/edit/{id}",method=RequestMethod.GET)	
	String lecturer_edit(@PathVariable("id") int id,HttpServletRequest request) {
		Lecturer lecturer=lecturerDao.find(id);
		request.getSession().setAttribute("lecturer", lecturer);
		
		return "lecturer_edit";
	}
	@RequestMapping(value="/admin/lecturer/edit/{id}",method=RequestMethod.POST)	
	public void lecturer_update(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {
		Lecturer obj=new Lecturer();
		obj.setLid(id);
		obj.setGname(request.getParameter("gname").toString());
		obj.setSurname(request.getParameter("surname").toString());
		obj.setCampus(request.getParameter("campus").toString());
		obj.setEmail(request.getParameter("email").toString());
		obj.setPassword(request.getParameter("password").toString());
		int result=lecturerDao.update(obj);
		 try {
			response.sendRedirect("/admin/lecturers");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	@RequestMapping(value="/admin/lecturer/delete/{id}",method=RequestMethod.GET)	
	String lecturer_delete(@PathVariable("id") int id,HttpServletRequest request) {
		Lecturer lecturer=lecturerDao.find(id);
		request.getSession().setAttribute("lecturer", lecturer);
		return "lecturer_delete";
	}

	
	@RequestMapping(value="/admin/lecturer/delete/{id}",method=RequestMethod.POST)	
	public void lecturer_destroy(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {

		int deleteCount=lecturerDao.delete(id);
		try {
			response.sendRedirect("/admin/lecturers");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	
	
	
	/*---------------------------------------------------------------------------------------------*/
}
