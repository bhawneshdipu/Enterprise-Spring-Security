package com.manish.enterprise;

import java.util.ArrayList;
import java.util.Collection;

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

@Component
public class CustomAuthenticationProvider
  implements AuthenticationProvider {

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
			
			Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
	        authorities.add(new SimpleGrantedAuthority("LECTURER"));
	        User user=new User(username, password, authorities);
	        authorities=user.getAuthorities();
	        return new UsernamePasswordAuthenticationToken(user, password, authorities);

		}else {
			System.out.println("Username:"+username+"\nPassword:"+password+"\nGroup:"+group);
			
	        Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
	        authorities.add(new SimpleGrantedAuthority("STUDENT"));
	        User user=new User(username, password, authorities);
	        authorities=user.getAuthorities();
	        return new UsernamePasswordAuthenticationToken(user, password, authorities);
		}
    }




        @Override
        public boolean supports(Class<? extends Object> authentication) {
            return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
        }




} 