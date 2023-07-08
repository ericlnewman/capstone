package com.success.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
@EnableWebSecurity
public class UserRegistrationSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
		
		requestHandler.setCsrfRequestAttributeName(null);
		http
			.authorizeHttpRequests((authz) -> authz
					// allows anyone to reach general webpages without signing ing
	                .requestMatchers("/index", "/signup", "/login", "/logout", "/forgotLoginInfo")
	                .permitAll()
	                // requires a username and password to sign in
	                .requestMatchers("api/**").hasRole("ADMIN")
	                .requestMatchers("api/v/**").hasRole("USER")
	                .anyRequest() .authenticated()
	                ).formLogin(formLogin -> formLogin
	                        .loginPage("/login")
	                        .permitAll()
	                    )
            		
                
			// set the name of the attribute the CsrfToken will be populated on
			.csrf((csrf) -> csrf
					.csrfTokenRequestHandler(requestHandler)
			);
		return http.build();
	}


}
