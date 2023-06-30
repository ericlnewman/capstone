package com.success.dto;

public class UserDTO {
	
	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private int age;
	private String password;
	private String concerns;
	private int concernId;
	
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

		public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
		return "UserDTO [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", age=" + age + ", concerns=" + concerns + "]";
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
