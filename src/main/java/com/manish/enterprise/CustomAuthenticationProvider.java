package com.manish.enterprise;

import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.manish.dao.LecturerDao;
import com.manish.dao.StudentDao;
import com.manish.model.Lecturer;
import com.manish.model.Student;

@Component
public class CustomAuthenticationProvider
  implements AuthenticationProvider {

	@Autowired
	DataSource mysqlDataSource;
	@Autowired
	Gson gson;
	
	@Autowired
	LecturerDao lecturerDao;
	
	@Autowired
	StudentDao studentDao;
	
    @Override
    public Authentication authenticate(Authentication authentication)
      throws AuthenticationException {
    	System.out.println("Authentication:: going:");
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
    	ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		String group=request.getRequest().getParameter("group");
		
		if(group.equalsIgnoreCase("ADMIN")) {
			System.out.println("Username:"+username+"\nPassword:"+password+"\nGroup:"+group);
			if(username.equalsIgnoreCase("admin@mail.com") && password.equals("admin@123")) {
				Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		        authorities.add(new SimpleGrantedAuthority("ADMIN"));
		        User user=new User(username, password, authorities);
		        authorities=user.getAuthorities();
		        return new UsernamePasswordAuthenticationToken(user, password, authorities);
				
			}else {
				throw new BadCredentialsException("Invalid Credentials");
			}
			
		}else if(group.equalsIgnoreCase("LECTURER")) {
			System.out.println("Username:"+username+"\nPassword:"+password+"\nGroup:"+group);
			String email=username;
			Lecturer obj=lecturerDao.login(email, password);
			if(obj!=null) {
				Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		        authorities.add(new SimpleGrantedAuthority("LECTURER"));
		        User user=new User(username, password, authorities);
		        authorities=user.getAuthorities();
		        return new UsernamePasswordAuthenticationToken(user, password, authorities);
			}else {
				throw new BadCredentialsException("Invalid Credentials");
			}
			
		}else {
			System.out.println("Username:"+username+"\nPassword:"+password+"\nGroup:"+group);
			
			String email=username;
			Student obj=studentDao.login(email, password);
			if(obj!=null) {
				Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		        authorities.add(new SimpleGrantedAuthority("STUDENT"));
		        User user=new User(username, password, authorities);
		        authorities=user.getAuthorities();
		        return new UsernamePasswordAuthenticationToken(user, password, authorities);
			}else {
				throw new BadCredentialsException("Invalid Credentials");
			}
		}
    }




        @Override
        public boolean supports(Class<? extends Object> authentication) {
            return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
        }




} 