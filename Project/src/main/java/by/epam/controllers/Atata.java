package by.epam.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import by.epam.beans.TimeActivity;

import java.util.Calendar;

///timejournal
@Controller
public class Atata {
	private static final Logger logger = LoggerFactory.getLogger(Atata.class);
	@Autowired
	private WorkServiceDAO workService;
	@Autowired
	private ActivityService activityService;

	/*
	 * @RequestMapping(value = "/atata.do", method = RequestMethod.POST) public
	 * String getTimeJournal( HttpServletRequest req, HttpServletResponse res,
	 * 
	 * @RequestParam(value = "id", required = false) String identity,
	 * 
	 * @RequestParam(value = "name", required = false) String name){ String
	 * pageReturn = "atata"; HashMap<> return pageReturn; }
	 */

	@RequestMapping(value = "/showatata.do")
	public String timeActivity(HttpServletRequest req, HttpServletResponse res) {
		String pageReturn = "atata";
		//workService.ge
		//for param
		String date = "date";//new Date(). use func from project convert date to string
		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();
		//logger.info(arg0);
		calendar.set(Calendar.DAY_OF_MONTH, currentDate.getDay());
		calendar.set(Calendar.MONTH, currentDate.getMonth());//-1
		calendar.set(Calendar.YEAR, currentDate.getYear());
		//format for dispay day [ Mo, Tu, We, Th, Fr, Sa, Su ]
		calendar.getDisplayName(Calendar.DAY_OF_WEEK,	Calendar.SHORT, Locale.ENGLISH);
		int dayCurrentWeek = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.roll(Calendar.DAY_OF_WEEK, dayCurrentWeek -1);
		int firstDayCurrentWeek = calendar.get(Calendar.DAY_OF_WEEK);
		Map<Integer, List<TimeActivity>> week = new HashMap();
		int i = 1;
		while(i<=dayCurrentWeek){
			//week.put(i, arg1)
			calendar.roll(Calendar.DAY_OF_WEEK, +1);
			i++;
		}
		return pageReturn;
	}
}
