package by.epam.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import by.epam.beans.Activity;
import by.epam.beans.Employee;
import by.epam.consts.ConstantsJSP;
import by.epam.dao.WorkServiceDAO;

@Controller
public class ActivityController {
	private static final Logger logger = LoggerFactory
			.getLogger(ActivityController.class);
	@Autowired
	private WorkServiceDAO workService;

	// activity.do?count=30
	@RequestMapping(value = "/activity.do", method = RequestMethod.GET)
	public  @ResponseBody String activity(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "count", required = false) String param) {
		logger.info(ConstantsJSP.EMPTY);
		StringBuffer sb = new StringBuffer();
		try {
			int count = Integer.parseInt(param);
			int load = Math.min(count, workService.getActivityCount());
			logger.info("load="+load);
			List<Activity> activityList = workService.getLastActivity(load);
			if (!activityList.isEmpty()) {
				for (Activity activity : activityList) {
					Employee e = activity.getMember().getEmployee();
					sb.append("<activity>");
					sb.append("<id>" + activity.getMember().getEmployee().getId() + "</id>");
					sb.append("<date>" + activity.getDate() + "</date>");
					sb.append("<firstName>" + e.getFirstName() + "</firstName>");
					sb.append("<lastName>" + e.getLastName() + "</lastName>");
					sb.append("<comment>" + activity.getComment() + "</comment>");
					sb.append("</activity>");
				}
				/*res.setContentType("text/xml");
				res.setHeader("Cache-Control", "no-cache");
				res.setHeader("Accept","text/xml; charset=utf-8");
				try {
					res.getWriter().write(
							"<activities>" + sb.toString() + "</activities>");
					//logger.info("xml="+sb.toString());
					logger.info("send xml data is successfull");
				} catch (IOException e) {
					logger.info("Error:" + e.getMessage());
					res.setStatus(HttpServletResponse.SC_NO_CONTENT);
				}*/
			}
		} catch (NumberFormatException e) {
			logger.info("error:" + e.getMessage());
			res.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
		return "<activities>" + sb.toString() + "</activities>";//"home";
	}
}
