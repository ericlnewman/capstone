package com.success.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

@Embeddable
@Table(name="users")
public class UserInformation {

	@Column(name="first_name", nullable = true)
	private String firstName;
	@Column(name="last_name", nullable = true)
	private String lastName;
	@Column(name="age", nullable = true)
	private int age;
	@Column(name="parent_or_other", nullable = true)
	private String parentOrGuardian;
	@Column(name="gender", nullable = true)
	private String gender;
	@Column(name="number_of_children", nullable = true)
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
