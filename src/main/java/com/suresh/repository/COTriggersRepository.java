package com.suresh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suresh.entities.COTriggers;

public interface COTriggersRepository extends JpaRepository<COTriggers, Integer> {

	public List<COTriggers> findByTriggerStatus(String status);
}
