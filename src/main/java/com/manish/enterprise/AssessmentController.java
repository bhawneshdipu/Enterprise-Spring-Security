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
public class AssessmentController {
	
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

	@RequestMapping("/admin/assessments")	
	String assessments(HttpServletRequest request) {
		Assessment[] assessmentList=assessmentDao.all();
		System.out.println("Assessment List:\n:"+gson.toJson(assessmentList));
		request.getSession().setAttribute("assessmentList", assessmentList);
		
		return "assessments";
	}
	@RequestMapping(value="/admin/assessment/add",method=RequestMethod.GET)	
	String assessment_add(HttpServletRequest request) {
		Student[] studentList=studentDao.all();
		System.out.println("Student List:\n:"+gson.toJson(studentList));
		request.getSession().setAttribute("studentList", studentList);
		Enrollment[] enrollmentList=enrollmentDao.all();
		System.out.println("Enrollment List:\n:"+gson.toJson(enrollmentList));
		request.getSession().setAttribute("enrollmentList", enrollmentList);

		return "assessment_add";
	}
	@RequestMapping(value="/admin/assessment/add",method=RequestMethod.POST)	
	public void assessment_store(HttpServletRequest request,HttpServletResponse response) {
		Assessment obj=new Assessment();
		obj.setSid(Integer.parseInt(request.getParameter("sid")));
		obj.setEid(Integer.parseInt(request.getParameter("eid").toString()));
		obj.setA1(Integer.parseInt(request.getParameter("a1").toString()));
		obj.setA2(Integer.parseInt(request.getParameter("a2").toString()));
		
		int result=assessmentDao.insert(obj);
		 try {
			response.sendRedirect("/admin/assessments");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	@RequestMapping(value="/admin/assessment/view/{id}",method=RequestMethod.GET)	
	String assessment_view(@PathVariable("id") int id,HttpServletRequest request) {
		Assessment assessment=assessmentDao.find(id);
		request.getSession().setAttribute("assessment", assessment);
		return "assessment_view";
	}
	@RequestMapping(value="/admin/assessment/edit/{id}",method=RequestMethod.GET)	
	String assessment_edit(@PathVariable("id") int id,HttpServletRequest request) {
		Assessment assessment=assessmentDao.find(id);
		request.getSession().setAttribute("assessment", assessment);
		Student[] studentList=studentDao.all();
		System.out.println("Student List:\n:"+gson.toJson(studentList));
		request.getSession().setAttribute("studentList", studentList);
		Enrollment[] enrollmentList=enrollmentDao.all();
		System.out.println("Enrollment List:\n:"+gson.toJson(enrollmentList));
		request.getSession().setAttribute("enrollmentList", enrollmentList);
		
		return "assessment_edit";
	}
	@RequestMapping(value="/admin/assessment/edit/{id}",method=RequestMethod.POST)	
	public void assessment_update(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {
		Assessment obj=new Assessment();
		obj.setAid(id);
		obj.setSid(Integer.parseInt(request.getParameter("sid")));
		obj.setEid(Integer.parseInt(request.getParameter("eid").toString()));
		obj.setA1(Integer.parseInt(request.getParameter("a1").toString()));
		obj.setA2(Integer.parseInt(request.getParameter("a2").toString()));
		
		int result=assessmentDao.update(obj);
		 try {
			response.sendRedirect("/admin/assessments");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	@RequestMapping(value="/admin/assessment/delete/{id}",method=RequestMethod.GET)	
	String assessment_delete(@PathVariable("id") int id,HttpServletRequest request) {
		Assessment assessment=assessmentDao.find(id);
		request.getSession().setAttribute("assessment", assessment);
		return "assessment_delete";
	}

	
	@RequestMapping(value="/admin/assessment/delete/{id}",method=RequestMethod.POST)	
	public void assessment_destroy(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {

		int deleteCount=assessmentDao.delete(id);
		try {
			response.sendRedirect("/admin/assessments");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	
	
	
	
	
	
	/*---------------------------------------------------------------------------------------------------*/

}
