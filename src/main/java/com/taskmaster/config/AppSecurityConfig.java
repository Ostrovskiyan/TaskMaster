package com.taskmaster.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.taskmaster.secure.Sha512Encoder;
import com.taskmaster.secure.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new Sha512Encoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/register/**", "/mobile_authorization/**", "/mobile_get_point/**", "/restore_password/**")
				.permitAll();
		http.authorizeRequests()
				.antMatchers("/add_child/**", "/view_points/**", "/area_editor/**", "/personal_cabinet/**",
						"/change_password/**", "/edit_profile/**", "/edit_child/**")
				.access("hasRole('ROLE_USER')").and();

		http.formLogin().loginPage("/login").loginProcessingUrl("/j_spring_security_check").failureUrl("/login?error")
				.usernameParameter("j_username").passwordParameter("j_password").permitAll();

		http.logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/login?logout").invalidateHttpSession(true);
	}
}
