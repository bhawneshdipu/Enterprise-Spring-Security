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
public class AdminController {
	
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
