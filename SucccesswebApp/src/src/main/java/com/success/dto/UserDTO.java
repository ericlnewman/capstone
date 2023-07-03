package com.success.dto;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.success.model.UserAddress;
import com.success.model.UserInformation;
import com.success.model.UserPersonOfConcern;
import com.success.model.UserRole;
import com.success.validatorandsecurity.ValidPassword;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
public class UserDTO implements UserDetails{
	
	@Id
	@SequenceGenerator(name = "user", sequenceName = "user", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "user")
	private Long id;
	@ValidPassword   // custom annotation see the validatorandsecutiy package
	@NotBlank
	private String password;
	@Email
	@NotBlank
	private String email;
	private int concernId;
	private String concerns;
	/*
	 * The fields below are for security using springframework.security
	 */
	@Enumerated(EnumType.STRING)
	private UserRole role;
	private Boolean enabled;
	private Boolean locked;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
		
		return Collections.singleton(authority);
	}
	@Override
	public String getPassword() {
		return password;
	}

	@Embedded
	private UserAddress userAddress;
	
	@Embedded
	private UserInformation userInformation;
	

	@Embedded
	private UserPersonOfConcern personOfConcern;
	
	public UserDTO() {
		super();
	}
	public UserDTO(@NotBlank String password, @Email @NotBlank String email, UserRole role) {
		super();
		this.password = password;
		this.email = email;
		this.role = role;
	}
	public UserDTO(@NotBlank String password, @Email @NotBlank String userName, int concernId, String concerns,
			UserRole role,  UserAddress userAddress, UserInformation userInformation,
			UserPersonOfConcern personOfConcern) {
		super();
		this.password = password;
		this.email = userName;
		this.concernId = concernId;
		this.concerns = concerns;
		this.role = role;
		this.userAddress = userAddress;
		this.userInformation = userInformation;
		this.personOfConcern = personOfConcern;
	}
	// getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	// getters and setters of embedded classes
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
	
	// these are from the UserDetails for security
	@Override
	public String getUsername() {
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", concernId=" + concernId + ", concerns=" + concerns + ", userAddress="
				+ userAddress + ", userInformation=" + userInformation + ", personOfConcern=" + personOfConcern + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(concernId, concerns, enabled, id, locked, password, personOfConcern, role, userAddress,
				userInformation, email);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return concernId == other.concernId && Objects.equals(concerns, other.concerns)
				&& Objects.equals(enabled, other.enabled) && Objects.equals(id, other.id)
				&& Objects.equals(locked, other.locked) && Objects.equals(password, other.password)
				&& Objects.equals(personOfConcern, other.personOfConcern) && role == other.role
				&& Objects.equals(userAddress, other.userAddress)
				&& Objects.equals(userInformation, other.userInformation) && Objects.equals(email, other.email);
	}
	
	/* Although not good practice, commented out as it is used for unit testing,
	 * and leaving for posterity, as well as if needed more testing.
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
	}*/

}
