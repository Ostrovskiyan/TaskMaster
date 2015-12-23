package com.taskmaster.secure;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taskmaster.entity.User;
import com.taskmaster.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService parentService;

	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = parentService.getByLogin(login);

		if (user == null || !user.getActivated()){
		    throw new UsernameNotFoundException("User not found");
		}

		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));

		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(),
				user.getPassword(), roles);
		return userDetails;
	}

}
