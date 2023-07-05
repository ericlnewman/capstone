package com.success.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.success.service.registration.RegistrationRequest;
import com.success.service.registration.RegistrationService;

@RestController
@RequestMapping(path = "/signup")
public class RegistrationController {

	Logger log = LoggerFactory.getLogger(this.getClass());
    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        try {
			return registrationService.register(request);
		} catch (Exception e) {
			log.error("Unable to save user.", e);
			 return "error";
		}
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
