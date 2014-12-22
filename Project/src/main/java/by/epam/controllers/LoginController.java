package by.epam.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import by.epam.beans.Employee;
import by.epam.beans.Member;
import by.epam.beans.User;
import by.epam.consts.ConstantsError;
import by.epam.consts.ConstantsJSP;
import by.epam.consts.ConstantsLogger;
import by.epam.workimplements.WorkDAO;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	public final static String pageLogger = ConstantsLogger.loggerPrefix
			+ ConstantsJSP.loginPage + ConstantsLogger.loggerSuffix;

	@Autowired
	private WorkDAO workService;

	@RequestMapping(value = "/login.do")
	public String login(HttpServletRequest req, HttpServletResponse res) {
		logger.info(pageLogger);
		String errorMessage = ConstantsJSP.EMPTY;
		Employee employee = null;
		String pageReturn = ConstantsJSP.loginPage;
		try {
			String login = req.getParameter(ConstantsJSP.KEY_LOGIN);
			String password = req.getParameter(ConstantsJSP.KEY_PASSWORD);
			if (login != null && password != null) {
				employee = workService.getEmployee(new User(login, password));
			} else {
				logger.info(pageLogger + ConstantsError.errorNull);
			}
		} catch (Exception e) {
			logger.info(pageLogger + e.getMessage());
			errorMessage = ConstantsError.errorServer;
		}
		if (employee == null) {
			errorMessage = ConstantsError.errorInputData;
		} else {
			pageReturn = ConstantsJSP.homePage;
		}
		req.setAttribute(ConstantsJSP.ATT_ERROR, errorMessage);
		HttpSession session = req.getSession();
		session.setAttribute(ConstantsJSP.ATT_EMPLOYEE, employee);
		// workService.get last activity
		return pageReturn;
	}
}
