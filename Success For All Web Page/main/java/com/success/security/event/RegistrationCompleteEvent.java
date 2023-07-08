package com.success.security.event;



import org.springframework.context.ApplicationEvent;

import com.success.dto.UserDTO;


public class RegistrationCompleteEvent extends ApplicationEvent {
    private UserDTO userDTO;
    private String applicationUrl;

    public RegistrationCompleteEvent(UserDTO userDTO, String applicationUrl) {
        super(userDTO);
        this.userDTO = userDTO;
        this.applicationUrl = applicationUrl;
    }

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getApplicationUrl() {
		return applicationUrl;
	}

	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}
}