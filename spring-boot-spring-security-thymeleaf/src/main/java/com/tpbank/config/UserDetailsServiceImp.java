package com.tpbank.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailsServiceImp implements UserDetailsService {
	  @Override
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    /*Here we are using dummy data, you need to load user data from
	     database or other third party application*/
	    User user = findUserbyUername(username);

	    //this class is used to add another User
	    UserBuilder builder = null;
	    if (user != null) {
	      builder = org.springframework.security.core.userdetails.User.withUsername(username);
	      builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
	      builder.roles(user.getRoles());
	    } else {
	      throw new UsernameNotFoundException("User not found.");
	    }

	    return builder.build();
	  }

	  //user find User that is available
	  private User findUserbyUername(String username) {
		  
		  try {
			  
			Connection connection=DriverManager.getConnection("jdbc:h2:file:~/test", "sa", "");
			
			Statement stmt=connection.createStatement();
			
			ResultSet rs=stmt.executeQuery("Select * from Account");
			
			while(rs.next()) {
				
				System.out.println(rs.getString(1)+" "+rs.getString(2));
				
				if(username.equalsIgnoreCase(rs.getString(1))) {
				      return new User(username, rs.getString(2), "ADMIN");
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				  
	    return null;
	  }
	}
