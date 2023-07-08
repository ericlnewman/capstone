package com.success.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

@Embeddable
@Table(name="users")
public class UserPersonOfConcern {
	
	@Column(name="name_of_person_of_concern", nullable = true)
	private String personOfConcernName;
	@Column(name="age_of_person_concern", nullable = true)
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