package com.success.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class UserInformation {


	private String firstName;
	private String lastName;
	private int age;
	private String parentOrGuardian;
	private String gender;
	private int numberOfChildren;

	
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
	public String getParentOrGuardian() {
		return parentOrGuardian;
	}
	public void setParentOrGuardian(String parentOrGuardian) {
		this.parentOrGuardian = parentOrGuardian;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getNumberOfChildren() {
		return numberOfChildren;
	}
	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}
	@Override
	public String toString() {
		return "UserInformation [firstName=" + firstName + ", lastName=" + lastName + ", age="
				+ age + ", parentOrGuardian=" + parentOrGuardian + ", gender=" + gender + ", numberOfChildren="
				+ numberOfChildren + "]";
	}
	
	
}
