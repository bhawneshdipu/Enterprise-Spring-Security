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
import com.manish.model.CourseConduction;
import com.manish.model.Enrollment;
import com.manish.model.Lecturer;
import com.manish.model.Student;

@Controller
public class LecturerController {
	
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
	
	
	@RequestMapping("/lecturer/courses")	
	String courses(HttpServletRequest request) {
		Course[] courseList=courseDao.getLecturerCourseBy("LID", Integer.parseInt(request.getSession().getAttribute("lecturer_id").toString()));
		System.out.println("Course List:\n:"+gson.toJson(courseList));
		request.getSession().setAttribute("courseList", courseList);
		
		return "lecturer_courses";
	}

	@RequestMapping("/lecturer/course_conductions")	
	String course_conductions(HttpServletRequest request) {
		CourseConduction[] courseConductionList=courseConductionDao.getLecturerCourseConductionBy("LID",Integer.parseInt(request.getSession().getAttribute("lecturer_id").toString()) );
		System.out.println("CourseConduction List:\n:"+gson.toJson(courseConductionList));
		request.getSession().setAttribute("courseConductionList", courseConductionList);
		
	
		return "lecturer_course_conductions";
	}
	
	@RequestMapping("/lecturer/course_conduction/view/{id}")	
	String course_conduction_view(@PathVariable("id") int id, HttpServletRequest request ) {
	CourseConduction course_conduction=courseConductionDao.find(id);
		
		Course[] courseList=courseDao.all();
		System.out.println("Course List:\n:"+gson.toJson(courseList));
		request.getSession().setAttribute("courseList", courseList);
		Lecturer[] lecturerList=lecturerDao.all();
		System.out.println("lecturer  List:\n:"+gson.toJson(lecturerList));
		request.getSession().setAttribute("lecturerList", lecturerList);
		request.getSession().setAttribute("course_conduction", course_conduction);
		
		return "lecturer_course_conduction_view";
	}
	@RequestMapping(value="/lecturer/edit_capacity/{id}",method=RequestMethod.GET)	
	String course_conduction_edit_capacity(@PathVariable ("id") int id,HttpServletRequest request) {
	
		CourseConduction course_conduction=courseConductionDao.find(id);
	
		Course[] courseList=courseDao.all();
		System.out.println("Course List:\n:"+gson.toJson(courseList));
		request.getSession().setAttribute("courseList", courseList);
		Lecturer[] lecturerList=lecturerDao.all();
		System.out.println("lecturer  List:\n:"+gson.toJson(lecturerList));
		request.getSession().setAttribute("lecturerList", lecturerList);
		request.getSession().setAttribute("course_conduction", course_conduction);

		
		return "lecturer_edit_capacity";
	}
	@RequestMapping(value="/lecturer/update_capacity/{id}",method=RequestMethod.POST)	
	public void course_conduction_edit_capacity_store(@PathVariable ("id") int id,HttpServletRequest request,HttpServletResponse response) {
		CourseConduction obj=courseConductionDao.find(id);
		obj.setCapacity(Integer.parseInt(request.getParameter("capacity").toString()));
		int result=courseConductionDao.update(obj);
		 try {
			response.sendRedirect("/lecturer/course_conductions");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		 return;

	}
	
	
	/*----------------------------------------------------------------------------------------------------------*/
	
	
	
	
	
	@RequestMapping("/lecturer/assessments")	
	String assessments(HttpServletRequest request) {
		int lid=Integer.parseInt(request.getSession().getAttribute("lecturer_id").toString());
		Assessment[] assessmentList=assessmentDao.findLecturerAssessments(lid);
		System.out.println("Assessment List:\n:"+gson.toJson(assessmentList));
		request.getSession().setAttribute("assessmentList", assessmentList);
		
		return "lecturer_assessments";
	}
	@RequestMapping(value="/lecturer/assessment/add",method=RequestMethod.GET)	
	String assessment_add(HttpServletRequest request) {
		int lid=Integer.parseInt(request.getSession().getAttribute("lecturer_id").toString());
			
		Student[] studentList=studentDao.getLecturerStudents(lid);
		System.out.println("Student List:\n:"+gson.toJson(studentList));
		request.getSession().setAttribute("studentList", studentList);
		
		Enrollment[] enrollmentList=enrollmentDao.findLecturerEnrollmentList(lid);
		System.out.println("Enrollment List:\n:"+gson.toJson(enrollmentList));
		request.getSession().setAttribute("enrollmentList", enrollmentList);

		return "lecturer_assessment_add";
	}
	@RequestMapping(value="/lecturer/assessment/add",method=RequestMethod.POST)	
	public void assessment_store(HttpServletRequest request,HttpServletResponse response) {
		Assessment obj=new Assessment();
		obj.setSid(Integer.parseInt(request.getParameter("sid")));
		obj.setEid(Integer.parseInt(request.getParameter("eid").toString()));
		obj.setA1(Integer.parseInt(request.getParameter("a1").toString()));
		obj.setA2(Integer.parseInt(request.getParameter("a2").toString()));
		
		int result=assessmentDao.insert(obj);
		 try {
			response.sendRedirect("/lecturer/assessments");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}
	@RequestMapping(value="/lecturer/assessment/view/{id}",method=RequestMethod.GET)	
	String assessment_view(@PathVariable("id") int id,HttpServletRequest request) {
		Assessment assessment=assessmentDao.find(id);
		request.getSession().setAttribute("assessment", assessment);
		return "lecturer_assessment_view";
	}
	@RequestMapping(value="/lecturer/assessment/edit/{id}",method=RequestMethod.GET)	
	String assessment_edit(@PathVariable("id") int id,HttpServletRequest request) {
		Assessment assessment=assessmentDao.find(id);
		request.getSession().setAttribute("assessment", assessment);
		int lid=Integer.parseInt(request.getSession().getAttribute("lecturer_id").toString());
		
		Student[] studentList=studentDao.getLecturerStudents(lid);
		System.out.println("Student List:\n:"+gson.toJson(studentList));
		request.getSession().setAttribute("studentList", studentList);
		Enrollment[] enrollmentList=enrollmentDao.findLecturerEnrollmentList(lid);
		System.out.println("Enrollment List:\n:"+gson.toJson(enrollmentList));
		request.getSession().setAttribute("enrollmentList", enrollmentList);
		
		return "lecturer_assessment_edit";
	}
	@RequestMapping(value="/lecturer/assessment/edit/{id}",method=RequestMethod.POST)	
	public void assessment_update(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {
		Assessment obj=new Assessment();
		obj.setAid(id);
		obj.setSid(Integer.parseInt(request.getParameter("sid")));
		obj.setEid(Integer.parseInt(request.getParameter("eid").toString()));
		obj.setA1(Integer.parseInt(request.getParameter("a1").toString()));
		obj.setA2(Integer.parseInt(request.getParameter("a2").toString()));
		
		int result=assessmentDao.update(obj);
		 try {
			response.sendRedirect("/lecturer/assessments");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return;
	}

	
	
	
}
