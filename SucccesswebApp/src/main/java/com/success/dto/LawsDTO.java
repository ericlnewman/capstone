package com.success.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name="laws")
public class LawsDTO {
	
	@Id
	@GeneratedValue
	@SerializedName("lawsId")
	@Expose
	@Column(name="law_id", nullable = false)
	private int lawId;
	@SerializedName("jurisdiction")
	@Expose
	@Column(name="jurisdiction", nullable = false)
	private String jurisdiction;
	@SerializedName("lawName")
	@Expose
	@Column(name="law_name", nullable = false)
	private String lawName;
	@SerializedName("lawDescription")
	@Expose
	@Column(name="law_description", nullable = false)
	private String lawDescription;
	@SerializedName("lawYear")
	@Expose
	@Column(name="law_year", nullable = false)
	private String lawYear;
	
	@OneToOne(mappedBy="userDTO")
	@SerializedName("state")
	@Expose
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