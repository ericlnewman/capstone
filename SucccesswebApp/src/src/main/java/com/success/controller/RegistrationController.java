package com.success.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.success.dto.UserDTO;
import com.success.service.registration.RegistrationRequest;
import com.success.service.registration.RegistrationService;

@RestController
@RequestMapping(path = "signup")
public class RegistrationController {

	private RegistrationService registrationService;
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping
	public String register(@RequestBody RegistrationRequest request) {
		try {
			return registrationService.register(request);
		} catch (IllegalArgumentException e) {
			log.error("Invalid registration request.", e);
			return "error: " + e.getMessage();
		} catch (Exception e) {
			log.error("Unable to save user.", e);
			return "error";
		}
	}
}