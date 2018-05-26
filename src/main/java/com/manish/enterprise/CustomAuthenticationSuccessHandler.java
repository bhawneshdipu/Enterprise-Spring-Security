package com.manish.enterprise;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.manish.dao.LecturerDao;
import com.manish.dao.StudentDao;
import com.manish.model.Lecturer;
import com.manish.model.Student;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	

	@Autowired
	LecturerDao lecturerDao;
	
	@Autowired
	StudentDao studentDao;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

		HttpSession session = httpServletRequest.getSession();
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		session.setAttribute("username", authUser.getUsername());
		session.setAttribute("authorities", authentication.getAuthorities());
		session.setAttribute("role", authentication.getAuthorities().toString());
		session.setAttribute("user", authUser);
		
		
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		System.out.println("Username:"+authUser.getUsername());
		System.out.println("ROLE:"+authUser.getAuthorities());
		String role=authUser.getAuthorities().toString();
		if(role.toUpperCase().indexOf("ADMIN")>=0) {
			httpServletResponse.sendRedirect("/admin");
			return;
				
		}else if(role.toUpperCase().indexOf("LECTURER")>=0) {
			Lecturer obj=lecturerDao.getByEmail(authUser.getUsername());
			System.out.println(obj);
			System.out.println("Username:"+authUser.getUsername());
			System.out.println("Password:"+authUser.getPassword());
			//session.setAttribute("role", "lecturer");
					
			session.setAttribute("login_lecturer", obj);
			session.setAttribute("lecturer_id", obj.getLid());
			
			httpServletResponse.sendRedirect("/lecturer");
			return;
				
		}else if(role.toUpperCase().indexOf("STUDENT")>=0) {
			Student obj=studentDao.getByEmail(authUser.getUsername());
			System.out.println(obj);
			System.out.println("Username:"+authUser.getUsername());
			
			System.out.println("Password:"+authUser.getPassword());
			//session.setAttribute("role", "student");
					
			session.setAttribute("login_student", obj);
			session.setAttribute("student_id", obj.getSid());
			
			httpServletResponse.sendRedirect("/student");
			return;
				
		}else {
			httpServletResponse.sendRedirect("/login");
			return;
			
		}
	}
}