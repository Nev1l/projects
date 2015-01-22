package by.epam.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import by.epam.consts.ConstantsJSP;

@Controller
public class LogoutController {
	private static final Logger logger = LoggerFactory
			.getLogger(LogoutController.class);

	@RequestMapping(value = "/logout.do")
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("");
		HttpSession session = request.getSession(false);
		if (session != null) {
		    session.invalidate();
		}
		Cookie cookie = new Cookie(TokenBasedRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, null);
		String contextPath = request.getContextPath();
		cookie.setPath(contextPath != null && contextPath.length() > 0 ? contextPath : "/");
		return ConstantsJSP.loginPage;
	}

}
