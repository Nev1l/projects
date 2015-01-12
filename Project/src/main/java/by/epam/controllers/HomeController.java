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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.epam.beans.Activity;
import by.epam.beans.Assignment;
import by.epam.beans.Employee;
import by.epam.beans.Member;
import by.epam.consts.ConstantsJSP;
import by.epam.dao.WorkServiceDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	@Autowired
	private WorkServiceDAO workService;

	public final static int MAX_RESULTS = 10;

	@RequestMapping(value = "/home.do")
	public String home(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "page", required = false) String page) {
		logger.info(ConstantsJSP.EMPTY);
		// workService.getProjectById(id);
		// workService.save(new Position(ProjectPosition.MANAGER));
		int start = 0;
		if (page != null) {
			start = Integer.parseInt(page);
			if (start > 0) {
				start = (start - 1) * MAX_RESULTS;
			}
		}
		HttpSession session = req.getSession(true);
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authenticate = context.getAuthentication();
		String userName = authenticate.getName();
		Employee employee = null;
		List<Member> members = null;
		List<Assignment> listAssignments = null;
		List<Activity> listActivity = null;
		int maxPageCount = 0;
		try {
			employee = workService.getEmployeeByUserName(userName);
			session.setAttribute(ConstantsJSP.EMPLOYEE, employee);
			members = workService.getMembersByEmployeeId(employee.getId());
			//==========HARDCODE===================
			//member.setRole(new Role(RoleAccess.DEVELOPER));
			//=============[Member-a больше нет работать со списком для получения прав для разных проектов]
			session.setAttribute(ConstantsJSP.MEMBERS, members);
			maxPageCount = workService.getCountAssignmentsByEmployeeId(employee.getId());
			start = Math.min(start,maxPageCount);
			listAssignments = workService.getEmployeeAssignments(employee.getId(), start,
					MAX_RESULTS);
			req.setAttribute(ConstantsJSP.EMPLOYEE_ASSIGNMENT, listAssignments);
			//продумать прогрузку AJAX(B)-ом
			listActivity = workService.getLastActivity(MAX_RESULTS);
			req.setAttribute(ConstantsJSP.LAST_ACTIVITY, listActivity);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		//pagination
		req.setAttribute(ConstantsJSP.RECORD_ON_PAGE,MAX_RESULTS);
		req.setAttribute(ConstantsJSP.RECORD_COUNT,maxPageCount);
		req.setAttribute(ConstantsJSP.CUR_PAGE,start);
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
