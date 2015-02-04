package by.epam.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import by.epam.beans.Assignment;
import by.epam.beans.Employee;
import by.epam.consts.ConstantsJSP;
import by.epam.dao.WorkServiceDAO;
import by.epam.utils.PageNavigator;

@Controller
public class AssignmentController {
	private static final Logger logger = LoggerFactory
			.getLogger(AssignmentController.class);
	@Autowired
	private WorkServiceDAO workService;
//@ResponseBody List<Assignment> 
	
	/*@RequestMapping(value = "/assignment.do")
	public String assigneeAjax(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "page", required = false) String page){
	}*/
	
	@RequestMapping(value = "/assignment.do")
	public String assignee(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "page", required = false) String page) {
		logger.info(ConstantsJSP.EMPTY);
		List<Assignment> listAssignments = null;
		int start = 0;
		int cur_page = 1;
		try {
			cur_page = Integer.parseInt(page);
			if (cur_page > 1) {
				start = (cur_page - 1) * ConstantsJSP.RESULTS_ON_LOAD;
			}
		} catch (NumberFormatException e) {
			logger.info("Error:"+e.getMessage());
		}
		logger.info("cur_page=" + cur_page);
		logger.info("select start=" + start);
		int recordCount = 0;
		try {
			HttpSession session = req.getSession(false);
			Employee employee = (Employee) session
					.getAttribute(ConstantsJSP.EMPLOYEE);
			recordCount = workService.getCountAssignmentsByEmployeeId(employee
					.getId());
			//=======================[pagination]========================
			int total = (int) Math.ceil(recordCount * (1.0d)
					/ ConstantsJSP.RESULTS_ON_LOAD * (1.0d));
			if (start > recordCount) {
				start = recordCount - ConstantsJSP.RESULTS_ON_LOAD;
				cur_page = total;
				req.setAttribute("page", cur_page);
				logger.info("change select start=" + start);
				logger.info("change cur_page=" + cur_page);
			}
			
			listAssignments = workService.getEmployeeAssignments(
					employee.getId(), start, ConstantsJSP.RESULTS_ON_LOAD);
			logger.info("getEmployeeAssignments="
					+ listAssignments);
			req.setAttribute(ConstantsJSP.EMPLOYEE_ASSIGNMENT, listAssignments);
			PageNavigator pageNavigator = new PageNavigator(total, cur_page);
			req.setAttribute(ConstantsJSP.PAGE_NAVIGATOR, pageNavigator);
		} catch (Exception e) {
			logger.info("Error:"+e.getMessage());
		}
		String uri = (String)req.getAttribute("caller");
		logger.info("referer="+uri);
		return  "forward:/"+uri;
	}
}
