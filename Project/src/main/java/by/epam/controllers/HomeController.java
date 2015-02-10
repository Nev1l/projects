package by.epam.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

import by.epam.beans.Activity;
import by.epam.beans.Assignment;
import by.epam.beans.Employee;
import by.epam.beans.Member;
import by.epam.consts.ConstantsJSP;
import by.epam.dao.WorkServiceDAO;
import by.epam.utils.PageNavigator;
import by.epam.utils.ProjectByMemberAccess;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	@Autowired
	private WorkServiceDAO workService;

	@RequestMapping(value = "/home.do")
	public String home(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "page", required = false) String page) {
		logger.info(ConstantsJSP.EMPTY);
		//List<Assignment> assignments = workService.getAssignment(null, 0, 10);
		//logger.info("list:"+assignments);
		int start = 0;
		int cur_page = 1;
		try {
			cur_page = Integer.parseInt(page);
			if (cur_page > 1) {
				start = (cur_page - 1) * ConstantsJSP.RESULTS_ON_LOAD;
			}
		} catch (NumberFormatException e) {
			logger.info(e.getMessage());
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
			// =======================[pagination]========================
			int total = (int) Math.ceil(recordCount * (1.0d)
					/ ConstantsJSP.RESULTS_ON_LOAD * (1.0d));
			if (start > recordCount) {
				start = recordCount - ConstantsJSP.RESULTS_ON_LOAD;
				cur_page = total;
				req.setAttribute("page", cur_page);
			}
			logger.info("change select start=" + start);
			List<Assignment> listAssignments = workService
					.getEmployeeAssignments(employee.getId(), start,
							ConstantsJSP.RESULTS_ON_LOAD);
			logger.info("after method getEmployeeAssignments="
					+ listAssignments);
			req.setAttribute(ConstantsJSP.EMPLOYEE_ASSIGNMENT, listAssignments);
			PageNavigator pageNavigator = new PageNavigator(total, cur_page);
			req.setAttribute(ConstantsJSP.PAGE_NAVIGATOR, pageNavigator);
			// =========================[Activity]=========================
			logger.info("param="
					+ req.getParameter(ConstantsJSP.PAGE_NAVIGATOR));
			List<Activity> listActivity = workService
					.getLastActivity(ConstantsJSP.ACTIVITY_ON_LOAD);
			logger.info("after method getLastActivity" + listActivity);
			req.setAttribute(ConstantsJSP.LAST_ACTIVITY, listActivity);
			// ======================[Access to button create new task]
			// ===============
			List<Member> memberProjectAccess = workService
					.getMembersByEmployeeId(employee.getId());
			boolean hasAccess = ProjectByMemberAccess
					.hasProjectsWhereAccessMoreThanDeveloper(memberProjectAccess);
			req.setAttribute(ConstantsJSP.hasAccessCreateGlobalTask, hasAccess);

		} catch (Exception e) {
			logger.info("Error:" + e.getMessage());
		}
		return ConstantsJSP.homePage;
	}

	public String getConnection() {
		// model.addAttribute("connectMessage", getConnection());
		Connection connection;
		String URL = "jdbc:derby://localhost:1527/db";
		String driver = "org.apache.derby.jdbc.ClientDriver";
		String user = "user";
		String pass = "123";
		String message = "Unknown";
		try {
			Class.forName(driver);
			connection = (Connection) DriverManager.getConnection(URL, user,
					pass);
			if (connection != null) {
				message = "Connecting sucess";
			}
		} catch (ClassNotFoundException ex) {
			message = "Connecting DB error: " + ex;
		} catch (SQLException ex) {
			message = "SQL error: " + ex;
		}
		return message;
	}
}
