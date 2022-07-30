package com.suresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suresh.entities.EligibilityDetails;

public interface EligibilityDetailsRepository extends JpaRepository<EligibilityDetails, Integer> {

	public EligibilityDetails findByCaseNo(Integer caseNo);
}
