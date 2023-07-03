package com.success.service.registration;

import com.success.dto.UserDTO;
import com.success.model.UserRole;
import com.success.service.UserService;
import com.success.validatorandsecurity.EmailValidator;

public class RegistrationService {

	private EmailValidator emailValidator;
	private UserService userService;
	
	public String register(RegistrationRequest request) throws Exception {
		boolean isValidEmail = emailValidator.test(request.getEmail());
		if (!isValidEmail) {
			throw new IllegalArgumentException("Email not valid");
		}
		
		UserDTO userDTO = new UserDTO(
			request.getEmail(),
			request.getPassword(),
			UserRole.USER
		);
		
		return userService.signupUser(userDTO);
	}
}
