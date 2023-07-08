package com.success.service.registration;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.success.service.registration.token.ConfirmationToken;
import com.success.service.registration.token.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenService {
	
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	public void saveConfirmationToken(ConfirmationToken token) {
		confirmationTokenRepository.save(token);
	}
	
	 public Optional<ConfirmationToken> getToken(String token) {
	        return confirmationTokenRepository.findByToken(token);
	    }

	    public int setConfirmedAt(String token) {
	        return confirmationTokenRepository.updateConfirmedAt(
	                token, LocalDateTime.now());
	    }

}
