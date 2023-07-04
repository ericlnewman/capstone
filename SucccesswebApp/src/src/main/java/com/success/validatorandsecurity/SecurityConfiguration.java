package com.success.validatorandsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import com.success.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	private  UserService userService;
	private  BCryptPasswordEncoder bCryptPasswordEncoder;

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
		
		requestHandler.setCsrfRequestAttributeName(null);
		http
			.authorizeHttpRequests((authz) -> authz
					// allows anyone to reach general webpages without signing ing
	                .requestMatchers("/index", "/signup", "/login", "/logout", "/forgotLoginInfo", "/api/**")
	                .permitAll()
	                    )
            		
                
			// set the name of the attribute the CsrfToken will be populated on
			.csrf((csrf) -> csrf
					.csrfTokenRequestHandler(requestHandler)
			);
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
	 * public UserDetailsService userDetailsService() {
		UserDetails userDetails = User
				.withUserDetails( )
		return null;
	}
	
	
	
	// requires a username and password to sign in
	                .requestMatchers("api/**").hasRole("ADMIN")
	                .requestMatchers("api/v/**").hasRole("USER")
	                .anyRequest() .authenticated()
	                ).formLogin(formLogin -> formLogin
	                        .loginPage("/login")
	                        .permitAll()
	                        
	                        .rememberMe(Customizer.withDefaults());
	 */
	
}
