package com.suresh.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="eligibility_details")
@Data
public class EligibilityDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer eligibilityId;
	
	private Integer caseNo;
	private String ssn;
	private String name;
	private String planName;
	private String planStatus;
	private String startDate;
	private String endDate;
	private Double benefitAmt;
	private String denialReason;
}
