package by.epam.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.epam.dao.WorkServiceDAO;
import by.epam.services.ActivityService;

@Controller
public class Atata {
	private static final Logger logger = LoggerFactory
			.getLogger(Atata.class);
	@Autowired
	private WorkServiceDAO workService;
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(value = "/timejournal.do", method = RequestMethod.POST)
	public String getTimeJournal(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam(value = "id", required = false) String identity,
			@RequestParam(value = "name", required = false) String name){
		
			return name;
		
	}
}
