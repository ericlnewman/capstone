package com.success.dto;

public class LawsDTO {
	
	private int lawId;
	private String jurisdiction;
	private String lawName;
	private String lawDescription;
	private String lawYear;
	private String state;
	
	public int getLawId() {
		return lawId;
	}
	public void setLawId(int lawId) {
		this.lawId = lawId;
	}
	public String getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	public String getLawName() {
		return lawName;
	}
	public void setLawName(String lawName) {
		this.lawName = lawName;
	}
	public String getLawDescription() {
		return lawDescription;
	}
	public void setLawDescription(String lawDescription) {
		this.lawDescription = lawDescription;
	}
	public String getLawYear() {
		return lawYear;
	}
	public void setLawYear(String lawYear) {
		this.lawYear = lawYear;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "LawsDTO [lawId=" + lawId + ", jurisdiction=" + jurisdiction + ", lawName=" + lawName
				+ ", lawDescription=" + lawDescription + ", lawYear=" + lawYear + ", state=" + state + "]";
	}

}
