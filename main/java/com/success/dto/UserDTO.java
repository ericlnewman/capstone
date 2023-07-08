package com.success.dto;

import java.util.Objects;

import org.hibernate.annotations.NaturalId;

import com.success.model.UserAddress;
import com.success.model.UserInformation;
import com.success.model.UserPersonOfConcern;
import com.success.validator.ValidPassword;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name="users")
public class UserDTO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	
	@ValidPassword
	@NotBlank
	@Column(name="password", nullable = false, unique = true, length = 45)
	private String password;
	@NaturalId(mutable = true)
	@Email
	@NotBlank
	@Column(name="email", nullable = false, unique = true, length = 45)
	private String email;
	@Column(name="concern_id")
	private int concernId;
	@Column(name="concerns")
	private String concerns;
	
	 private String role;
	 private boolean isEnabled = false;

	@Embedded
	private UserAddress userAddress;
	
	@Embedded
	private UserInformation userInformation;
	

	@Embedded
	private UserPersonOfConcern personOfConcern;
	
	public UserDTO() {
		super();
	}
	public UserDTO(@NotBlank String password, @Email @NotBlank String email) {
		super();
		this.password = password;
		this.email = email;
		
	}
	public UserDTO(@NotBlank String password, @Email @NotBlank String userName, int concernId, String concerns,
			 UserAddress userAddress, UserInformation userInformation, UserPersonOfConcern personOfConcern) {
		super();
		this.password = password;
		this.email = userName;
		this.concernId = concernId;
		this.concerns = concerns;
		this.userAddress = userAddress;
		this.userInformation = userInformation;
		this.personOfConcern = personOfConcern;
	}

	
	
	
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", concernId=" + concernId + ", concerns=" + concerns + ", userAddress="
				+ userAddress + ", userInformation=" + userInformation + ", personOfConcern=" + personOfConcern + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getConcernId() {
		return concernId;
	}
	public void setConcernId(int concernId) {
		this.concernId = concernId;
	}
	public String getConcerns() {
		return concerns;
	}
	public void setConcerns(String concerns) {
		this.concerns = concerns;
	}
	public UserAddress getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	public UserInformation getUserInformation() {
		return userInformation;
	}
	public void setUserInformation(UserInformation userInformation) {
		this.userInformation = userInformation;
	}
	public UserPersonOfConcern getPersonOfConcern() {
		return personOfConcern;
	}
	public void setPersonOfConcern(UserPersonOfConcern personOfConcern) {
		this.personOfConcern = personOfConcern;
	}
	@Override
	public int hashCode() {
		return Objects.hash(concernId, concerns, id, password, personOfConcern, userAddress,
				userInformation, email);
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}
