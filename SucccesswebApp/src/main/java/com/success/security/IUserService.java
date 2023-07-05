package com.success.security;


import java.util.Optional;

import com.success.dto.UserDTO;
import com.success.security.registration.RegistrationRequest;

public interface IUserService {
	 	
		Iterable<UserDTO> getUsers();
	    UserDTO registerUser(RegistrationRequest request);
	    Optional<UserDTO> findByEmail(String email);

	    void saveUserVerificationToken(UserDTO oser, String verificationToken);

	    String validateToken(String theToken);
}
