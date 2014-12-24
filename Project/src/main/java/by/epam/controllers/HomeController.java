package by.epam.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import by.epam.beans.Assignment;
import by.epam.beans.Employee;
import by.epam.beans.Position;
import by.epam.beans.ProjectPosition;
import by.epam.beans.Role;
import by.epam.beans.RoleAccess;
import by.epam.beans.Status;
import by.epam.beans.Status.State;
import by.epam.consts.ConstantsJSP;
import by.epam.consts.ConstantsLogger;
import by.epam.dao.WorkDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	public final static String pageLogger = ConstantsLogger.loggerPrefix
			+ ConstantsJSP.homePage + ConstantsLogger.loggerSuffix;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired
	private WorkDAO workService;

	@RequestMapping(value = "/home.do")
	public String home(HttpServletRequest req, HttpServletResponse res) {
		logger.info(pageLogger);
		req.setAttribute("connectMessage", getConnection());
		//workService.getProjectById(id);
		
		//assigned to me
		HttpSession session = req.getSession();
		if(session!=null){
			Employee employee = (Employee) session.getAttribute(ConstantsJSP.ATT_EMPLOYEE);
			if (employee!=null){
				List<Assignment> list = workService.getEmployeeAssignments(employee.getId());
				req.setAttribute(ConstantsJSP.EMPLOYEE_ASSIGNMENT, list);
				req.setAttribute("obj", "LIST");
			}
		}
		//workService.save(new Position(ProjectPosition.MANAGER));
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
