package com.manish.enterprise;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

		HttpSession session = httpServletRequest.getSession();
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		session.setAttribute("username", authUser.getUsername());
		session.setAttribute("authorities", authentication.getAuthorities());
		session.setAttribute("user", authUser);
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		System.out.println("Username:"+authUser.getUsername());
		System.out.println("ROLE:"+authUser.getAuthorities());
		String role=authUser.getAuthorities().toString();
		if(role.toUpperCase().indexOf("ADMIN")>=0) {
			httpServletResponse.sendRedirect("/admin");
			return;
				
		}else if(role.toUpperCase().indexOf("LECTURER")>=0) {
			httpServletResponse.sendRedirect("/lecturer");
			return;
				
		}else if(role.toUpperCase().indexOf("STUDENT")>=0) {
			httpServletResponse.sendRedirect("/student");
			return;
				
		}else {
			httpServletResponse.sendRedirect("/login");
			return;
			
		}
	}
}