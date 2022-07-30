package com.suresh.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suresh.entities.COTriggers;
import com.suresh.entities.EligibilityDetails;
import com.suresh.repository.COTriggersRepository;
import com.suresh.repository.EligibilityDetailsRepository;
import com.suresh.utils.GenerateApprovedPlanPdf;
import com.suresh.utils.GenerateDeniedPlanPdf;

@RestController
public class CORestController {

	@Autowired
	private COTriggersRepository coTriggersRepository;

	@Autowired
	private EligibilityDetailsRepository eligibilityDetailsRepository;

	@Autowired
	private HttpServletResponse response;

	List<EligibilityDetails> edList = new ArrayList<>();
	EligibilityDetails ed = null;

	@GetMapping
	public void findALLPendingStatus() throws DocumentException, IOException {

		List<COTriggers> list = coTriggersRepository.findByTriggerStatus("Pending");
		for (COTriggers triggers : list) {
			ed = eligibilityDetailsRepository.findByCaseNo(triggers.getCaseNo());
			edList.add(ed);
		}
		List<EligibilityDetails> approvedList = new ArrayList<>();
		List<EligibilityDetails> deniedList = new ArrayList<>();
		for (EligibilityDetails details : edList) {

			if (details.getPlanStatus().equals("APPROVED")) {
				approvedList.add(details);

			} else {
				deniedList.add(details);
			}
		}

		exportToPDF(response, edList);
		exportToPDF(response, deniedList);
		
	}

	public void exportToPDF(HttpServletResponse response, List<EligibilityDetails> ed)
			throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Triggers_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		 GenerateApprovedPlanPdf pdf = new GenerateApprovedPlanPdf(ed);

		GenerateDeniedPlanPdf deniedPdf = new GenerateDeniedPlanPdf(ed);

		for (EligibilityDetails details : ed) {

			if (details.getPlanStatus().equals("APPROVED")) {
				pdf.buildApprovedPlanPdf(response);
				
			} else {
				deniedPdf.buildDeniedPlanPd(response);
			}

		}
	}
	
	
	

}









//			System.out.println(ed);
//(ed.getPlanStatus().equals("APPROVED")) ?  pdf.buildApprovedPlanPdf(ed) : pdf.buildDeniedPlanPd(ed);
/*
 * List<EligibilityDetails> approvedList = new ArrayList<>();
 * List<EligibilityDetails> deniedList = new ArrayList<>();
 * 
 * for (EligibilityDetails details : edList) {
 * 
 * if (details.getPlanStatus().equals("APPROVED")) { approvedList.add(details);
 * 
 * } else { deniedList.add(details); exportToPDF(response, deniedList); }
 * 
 * exportToPDF(response, approvedList); }
 */