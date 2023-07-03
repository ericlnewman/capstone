package com.success.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.success.service.registration.RegistrationRequest;
import com.success.service.registration.RegistrationService;

@RestController
@RequestMapping(path = "signup")
public class RegistrationController {

	private RegistrationService registrationService;
	
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	}
}
