package com.manish.enterprise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	@Autowired
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	@Autowired
	CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

	
		http.csrf().disable();
		
		http.authorizeRequests()
				.antMatchers("/admin").hasAnyAuthority("ADMIN")
				.antMatchers("/admin/**").hasAnyAuthority("ADMIN")
				.antMatchers("/student").hasAnyAuthority("ADMIN","STUDENT")
				.antMatchers("/student/**").hasAnyAuthority("ADMIN","STUDENT")
				.antMatchers("/lecturer").hasAnyAuthority("ADMIN","LECTURER")
				.antMatchers("/lecturer/**").hasAnyAuthority("ADMIN","LECTURER")
				.and().formLogin().loginPage("/login")
				.successHandler(customAuthenticationSuccessHandler).failureHandler(customAuthenticationFailureHandler)
				.permitAll()
				.usernameParameter("email").passwordParameter("password")
				.and().logout().logoutSuccessUrl("/login?logout").permitAll();
		
		http.authorizeRequests().anyRequest().fullyAuthenticated();
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.and().ignoring().antMatchers("/css/**")
			.and().ignoring().antMatchers("/static/**")
			.and().ignoring().antMatchers("/js/**");
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
	}

}