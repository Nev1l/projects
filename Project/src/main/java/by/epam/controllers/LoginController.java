package by.epam.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import by.epam.beans.Employee;
import by.epam.beans.User;
import by.epam.consts.ConstantsError;
import by.epam.consts.ConstantsJSP;
import by.epam.consts.ConstantsLogger;
import by.epam.workimplements.WorkDAO;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private WorkDAO workService;

	@RequestMapping(value = "/login.do")
	public String login(HttpServletRequest req, HttpServletResponse res) {
		logger.info(ConstantsLogger.loggerPrefix+ConstantsJSP.loginPage);
		User user = null;
		try {
			user = new User(req.getParameter(ConstantsJSP.KEY_LOGIN),
					req.getParameter(ConstantsJSP.KEY_PASSWORD));
		} catch (Exception e) {
			logger.info(ConstantsLogger.loggerPrefix+ConstantsJSP.loginPage+ConstantsLogger.loggerSuffix+e.getMessage());
			req.setAttribute(ConstantsJSP.ATT_ERROR,ConstantsError.errorLoginPage);
			return "forward:" + ConstantsJSP.homePage;
		}
		Employee employee = workService.getEmployee(user);
		req.setAttribute(ConstantsJSP.ATT_EMPLOYEE, employee);
		return "forward:" + ConstantsJSP.homePage;
	}
}
