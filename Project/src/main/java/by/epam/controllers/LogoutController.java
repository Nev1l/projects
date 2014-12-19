package by.epam.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import by.epam.consts.ConstantsJSP;
import by.epam.consts.ConstantsLogger;

@Controller
public class LogoutController {
	private static final Logger logger = LoggerFactory
			.getLogger(LogoutController.class);
	public final static String pageLogger = ConstantsLogger.loggerPrefix
			+ ConstantsJSP.logoutPage + ConstantsLogger.loggerSuffix;

	@RequestMapping(value = "/logout.do")
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(pageLogger);
		HttpSession session = request.getSession();
		session.invalidate();
		return ConstantsJSP.homePage;
	}

}
