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
import com.manish.dao.LecturerDao;
import com.manish.dao.StudentDao;
import com.manish.model.Assessment;
import com.manish.model.Course;
import com.manish.model.CourseAssessment;
import com.manish.model.Lecturer;
import com.manish.model.Student;

@Controller
public class CourseAssessmentController {
	
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
	StudentDao studentDao;

	/*----------------------------------------------------------------------------------------------*/

	@RequestMapping("/admin/course_assessments")	
	String courseAssessments(HttpServletRequest request) {
		CourseAssessment[] courseAssessmentList=courseAssessmentDao.all();
		System.out.println("CourseAssessment List:\n:"+gson.toJson(courseAssessmentList));
		request.getSession().setAttribute("courseAssessmentList", courseAssessmentList);
		
		return "course_assessments";
	}
	@RequestMapping(value="/admin/course_assessment/add",method=RequestMethod.GET)	
	String courseAssessment_add(HttpServletRequest request) {
		Course[] courseList=courseDao.all();
		System.out.println("Course List:\n:"+gson.toJson(courseList));
		request.getSession().setAttribute("courseList", courseList);
		Assessment[] assessmentList=assessmentDao.all();
		System.out.println("Assessment  List:\n:"+gson.toJson(assessmentList));
		request.getSession().setAttribute("assessmentList", assessmentList);

		return "course_assessment_add";
	}
	@RequestMapping(value="/admin/course_assessment/add",method=RequestMethod.POST)	
	public void courseAssessment_store(HttpServletRequest request,HttpServletResponse response) {
		CourseAssessment obj=new CourseAssessment();
		obj.setAid(Integer.parseInt(request.getParameter("aid")));
		obj.setCid(Integer.parseInt(request.getParameter("cid").toString()));
		obj.setSemester(request.getParameter("semester").toString());
		int result=courseAssessmentDao.insert(obj);
		 try {
			response.sendRedirect("/admin/course_assessments");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	@RequestMapping(value="/admin/course_assessment/view/{id}",method=RequestMethod.GET)	
	String courseAssessment_view(@PathVariable("id") int id,HttpServletRequest request) {
		CourseAssessment course_assessment=courseAssessmentDao.find(id);
	
		Student[] studentList=studentDao.all();
		System.out.println("Student List:\n:"+gson.toJson(studentList));
		request.getSession().setAttribute("studentList", studentList);
		Course[] courseList=courseDao.all();
		System.out.println("Course List:\n:"+gson.toJson(courseList));
		request.getSession().setAttribute("courseList", courseList);

		request.getSession().setAttribute("course_assessment", course_assessment);
		return "course_assessment_view";
	}
	@RequestMapping(value="/admin/course_assessment/edit/{id}",method=RequestMethod.GET)	
	String courseAssessment_edit(@PathVariable("id") int id,HttpServletRequest request) {
		CourseAssessment course_assessment=courseAssessmentDao.find(id);
		
		Student[] studentList=studentDao.all();
		System.out.println("Student List:\n:"+gson.toJson(studentList));
		request.getSession().setAttribute("studentList", studentList);
		Course[] courseList=courseDao.all();
		System.out.println("Course List:\n:"+gson.toJson(courseList));
		request.getSession().setAttribute("courseList", courseList);

		request.getSession().setAttribute("course_assessment", course_assessment);
		
		
		return "course_assessment_edit";
	}
	@RequestMapping(value="/admin/course_assessment/edit/{id}",method=RequestMethod.POST)	
	public void courseAssessment_update(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {
		CourseAssessment obj=new CourseAssessment();
		obj.setCaid(id);
		obj.setAid(Integer.parseInt(request.getParameter("aid")));
		obj.setCid(Integer.parseInt(request.getParameter("cid").toString()));
		obj.setSemester(request.getParameter("semester").toString());
		int result=courseAssessmentDao.update(obj);
		 try {
			response.sendRedirect("/admin/course_assessments");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	@RequestMapping(value="/admin/course_assessment/delete/{id}",method=RequestMethod.GET)	
	String courseAssessment_delete(@PathVariable("id") int id,HttpServletRequest request) {
		CourseAssessment course_assessment=courseAssessmentDao.find(id);
		
		Student[] studentList=studentDao.all();
		System.out.println("Student List:\n:"+gson.toJson(studentList));
		request.getSession().setAttribute("studentList", studentList);
		Course[] courseList=courseDao.all();
		System.out.println("Course List:\n:"+gson.toJson(courseList));
		request.getSession().setAttribute("courseList", courseList);

		request.getSession().setAttribute("course_assessment", course_assessment);
		return "course_assessment_delete";
	}

	
	@RequestMapping(value="/admin/course_assessment/delete/{id}",method=RequestMethod.POST)	
	public void courseAssessment_destroy(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {

		int deleteCount=courseAssessmentDao.delete(id);
		try {
			response.sendRedirect("/admin/course_assessments");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	
	
	
	
	
	
	/*---------------------------------------------------------------------------------------------------*/
	

}
