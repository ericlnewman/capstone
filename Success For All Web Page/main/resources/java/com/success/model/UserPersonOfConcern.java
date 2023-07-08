package com.success.model;

import javax.persistence.Embeddable;

@Embeddable
public class UserPersonOfConcern {
	
	private String personOfConcernName;
	private int ageOfPersonOfConcern;
	
	public String getPersonOfConcernName() {
		return personOfConcernName;
	}

	public void setPersonOfConcernName(String personOfConcernName) {
		this.personOfConcernName = personOfConcernName;
	}

	public int getAgeOfPersonOfConcern() {
		return ageOfPersonOfConcern;
	}

	public void setAgeOfPersonOfConcern(int ageOfPersonOfConcern) {
		this.ageOfPersonOfConcern = ageOfPersonOfConcern;
	}

	@Override
	public String toString() {
		return "UserPersonOfConcern [personOfConcern=" + personOfConcernName + ", ageOfPersonOfConcern="
				+ ageOfPersonOfConcern + "]";
	}

}
