package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//specify the authentication type here....
		auth.jdbcAuthentication().dataSource(dataSource).
		withDefaultSchema().withUser(User.withUsername("user").password("user").roles("USER")).
		withUser(User.withUsername("admin").password("admin").roles("ADMIN"));
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//Applying Authorization for the users..
		
		  http.authorizeRequests(). antMatchers("/admin").hasRole("ADMIN").
		  antMatchers("/user").hasAnyRole("ADMIN","USER"). antMatchers("/").permitAll()
		  .and().exceptionHandling().accessDeniedPage("/unauthorized")
		  .and().formLogin();
		  
		 
		/*
		 * http.csrf().disable() .authorizeRequests()
		 * .antMatchers("/admin").hasRole("ADMIN")
		 * .antMatchers("/user").hasAnyRole("USER","ADMIN")
		 * .antMatchers("/").fullyAuthenticated()
		 * .and().logout().clearAuthentication(true).logoutSuccessUrl("/logoutSuccess")
		 * .and().exceptionHandling().accessDeniedPage("/unauthorized")
		 * .and().formLogin();
		 */
	}
	
	
	  @Bean protected PasswordEncoder getPasswordEncoder() { return
	  NoOpPasswordEncoder.getInstance(); }
	 
	
}
