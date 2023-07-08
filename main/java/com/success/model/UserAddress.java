package com.success.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

@Embeddable
@Table(name="users")
public class UserAddress {
	
	@Column(name="state", nullable = true)
	private String userState;
	@Column(name="city", nullable = true)
	private String userTown;
	@Column(name="zip", nullable = true)
    private String userZip;
	@Column(name="street", nullable = true)
	private String userStreet;
	@Column(name="country", nullable = true)
	private String userCountry;
	
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getUserTown() {
		return userTown;
	}
	public void setUserTown(String userTown) {
		this.userTown = userTown;
	}
	public String getUserZip() {
		return userZip;
	}
	public void setUserZip(String userZip) {
		this.userZip = userZip;
	}
	public String getUserStreet() {
		return userStreet;
	}
	public void setUserStreet(String userStreet) {
		this.userStreet = userStreet;
	}
	public String getUserCountry() {
		return userCountry;
	}
	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}
	@Override
	public String toString() {
		return "UserAddress [userState=" + userState + ", userTown=" + userTown + ", userZip=" + userZip
				+ ", userStreet=" + userStreet + ", userCountry=" + userCountry + "]";
	}
}