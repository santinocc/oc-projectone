//package com.oc.projectone.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//
//
//import com.oc.projectone.service.JsonLoadingService;
//
//public class ProjectoneController {
//
//	private JsonLoadingService jsonLoadingService;
//	
//	private final Logger logger = LoggerFactory.getLogger(ProjectoneController.class);
//	
//	@Autowired
//	public ProjectoneController(JsonLoadingService jsonLoadingService) {
//		super();
//		this.jsonLoadingService = jsonLoadingService;
//	}
//	
//	@GetMapping("/communityEmail")
//	public ModelAndView showCityEmails(@RequestParam(required = false) String city) {
//		
//		logger.info("HTTP GET request received at /communityEmail URL");
//		
//		String viewName = "cityEmails";
//		
//		Map<String, Object> model = new HashMap<String, Object>();
//		
//		CityEmail cityEmail = jsonLoadingService.
//	}
//	
//	
//}
