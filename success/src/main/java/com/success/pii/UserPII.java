package com.success.pii;

//@Embeddable
public class UserPII {

	private String parentOrGuardian;
	private String gender;
	private int numberOfChildren;
	private String personOfConcern;
	private int ageOfPersonOfConcern;
	

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
