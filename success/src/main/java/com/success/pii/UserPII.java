package com.success.pii;

//@Embeddable
public class UserPII {
	
	private String firstName;
	private String lastName;
	private int age;
	private String parentOrGuardian;
	private String gender;
	private int numberOfChildren;
	private String personOfConcern;
	private int ageOfPersonOfConcern;
	
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
	public String getPersonOfConcern() {
		return personOfConcern;
	}
	public void setPersonOfConcern(String personOfConcern) {
		this.personOfConcern = personOfConcern;
	}
	public int getAgeOfPersonOfConcern() {
		return ageOfPersonOfConcern;
	}
	public void setAgeOfPersonOfConcern(int ageOfPersonOfConcern) {
		this.ageOfPersonOfConcern = ageOfPersonOfConcern;
	}
	
}
