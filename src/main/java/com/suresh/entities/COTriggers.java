package com.suresh.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CO_TRIGGERS")
@Data
public class COTriggers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer triggerId;
	private Integer caseNo;
	private String triggerStatus;
}
