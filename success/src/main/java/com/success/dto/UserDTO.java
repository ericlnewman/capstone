package com.success.dto;

import com.success.pii.Address;
import com.success.pii.UserPII;

public class UserDTO {
	
	private Long id;
	private String email;
	
	private String password;
	private String concerns;
	private int concernId;
	
	//@Embedded
	private Address address;
	//@Embedded
	private UserPII userPII;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
		public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConcerns() {
		return concerns;
	}

	public void setConcerns(String concerns) {
		this.concerns = concerns;
	}

	
	public int getConcernId() {
		return concernId;
	}

	public void setConcernId(int concernId) {
		this.concernId = concernId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", concerns=" + concerns + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// assume doesn't match
		boolean value = false;
		if(obj instanceof UserDTO) {
			UserDTO incomingUser = (UserDTO) obj;
			// if the value is equal, return true
			value = incomingUser.getId() == getId();
		} // else return false
		return value;
	}


}
