package com.tpbank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
// http://docs.spring.io/spring-boot/docs/current/reference/html/howto-security.html
// Switch off the Spring Boot security configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home", "/about","/demo","/login","/execute",
                		"/manual/search/all/*","/css/**","/js/**","/images/**","/fonts/**","/webjars/**"
                		,"/**/favicon.ico","/403","/elastic/search/resource/general/vay"
                		,"/rest/search/**","/elastic/search/general/**"
                		,"/elastic/search/general/title/vay"
                		,"/elastic/search/**"
                		,"/boost/**","/console/**","/changePassword").permitAll()
                //if admin is not authorized then forward to login page
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                //This line code point out that /url is default page
                //In any case url not match that web automatic roll back
                .formLogin()
                .loginPage("/demo")
                .permitAll()
                .and()
                //it is used to logout
                //clear all rememberMe() authentication
                //clear security configuration and redirect to login?success
                .logout()
                .permitAll()
                .and()
                //this line configure
                //Specify to allow any request that comes from the same origin 
                //to frame this application. For example, 
                //if the application was hosted on example.com, 
                //then example.com could frame the application, 
                //but evil.com could not frame the application. 
                .headers().frameOptions().sameOrigin().and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }


    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");
    }*/
    
    @Bean
    public UserDetailsService userDetailsService() {
      return new UserDetailsServiceImp();
    };
    
    public BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
    	//Add authentication based upon the custom UserDetailsService 
    	//that is passed in. It then returns a DaoAuthenticationConfigurer to allow customization of the authentication. 
    	
    	auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
    
    /*@Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("password")
            .roles("USER").build());
        return manager;
    }*/

    /*
    //Spring Boot configured this already.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }*/

    /*@Bean
    public PasswordEncoder encoder() {
    	return new BCryptPasswordEncoder();
    }*/
    
}
