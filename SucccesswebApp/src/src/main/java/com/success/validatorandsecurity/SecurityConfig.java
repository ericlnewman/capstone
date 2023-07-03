package com.success.validatorandsecurity;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.context.DelegatingApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.success.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration{
	
	private  UserService userService;
	private  BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests((authorizeHttpRequests) ->
				authorizeHttpRequests
					.requestMatchers("/**")
					.permitAll()
					.anyRequest()
					.authenticated()
			)
			.formLogin((formLogin) ->
            formLogin
            .loginPage("/login")
            .usernameParameter("email")
            .permitAll()
					)
		    .logout((formLogout) ->
		    	formLogout.permitAll());
        return http.build();
	}



	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(bCryptPasswordEncoder);
		provider.setUserDetailsService(userService);
		return provider;
	}
	
	/*
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
			.username("user")
			.password("password")
			.roles("USER")
			.build();
		UserDetails admin = User.withDefaultPasswordEncoder()
			.username("admin")
			.password("password")
			.roles("ADMIN", "USER")
			.build();
		return new InMemoryUserDetailsManager(user, admin);
	}*/

}
