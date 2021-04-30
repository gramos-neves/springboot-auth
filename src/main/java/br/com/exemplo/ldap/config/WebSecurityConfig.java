package br.com.exemplo.ldap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	
	
	   @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        
			        .authorizeRequests().antMatchers("/h2-console/**").permitAll()
			        .and()
			        .headers().frameOptions().disable()
			        .and()
			        .csrf().ignoringAntMatchers("/h2-console/**")
			        .and()
			        .cors().disable()  
	                .authorizeRequests()
	                .antMatchers("/user").hasRole("USER")
	                .antMatchers("/admin").hasAnyRole("ADMIN", "USER")
	                .anyRequest().fullyAuthenticated()
	                .and()
	                .formLogin()
	                .loginPage("/login").permitAll();
	    } 
	
	
	
	
	/*
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        
		        .authorizeRequests().antMatchers("/h2-console/**").permitAll()
		        .and()
		        .headers().frameOptions().disable()
		        .and()
		        .csrf().ignoringAntMatchers("/h2-console/**")
		        .and()
		        .cors().disable()  
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll();
    } */
	
	

    
    @Bean()
    public PasswordEncoder getPasswordEncoder() {
    	 DelegatingPasswordEncoder delPasswordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
         BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
         delPasswordEncoder.setDefaultPasswordEncoderForMatches(bCryptPasswordEncoder);
         return delPasswordEncoder;
    }
    
    
    
    /*

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.authorizeRequests()
				.antMatchers("/signin")
				.hasAnyRole("USER","ADMIN")
				.antMatchers("/signin")
				.hasRole("ADMIN")
				.and()
				.formLogin()

				.and()
				.logout();
	}*/
    
   /* 
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
    	UserDetails user = User.withDefaultPasswordEncoder()
    			      .username("user")
    			      .password("password")
    			      .roles("USER")
    			      .build();
    	
    	return new InMemoryUserDetailsManager(user);
    }*/
    
    
    /*
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource()
                .url("ldap://localhost:8389/dc=springframework,dc=org")
                .and()
                .passwordCompare()
                .passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("userPassword");
    }*/


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/css/**")
                .antMatchers("/img/**")
                .antMatchers("/js/**");
    }



}
