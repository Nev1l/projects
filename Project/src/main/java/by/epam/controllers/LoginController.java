package by.epam.controllers;

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

import by.epam.beans.Employee;
import by.epam.consts.ConstantsJSP;
import by.epam.dao.WorkServiceDAO;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	@Autowired
	private WorkServiceDAO workService;

	// http://docs.spring.io/spring-security/site/docs/3.0.x/reference/technical-overview.html

	@RequestMapping(value = "/login.do")
	public String login(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "error", required = false) String error) {
		logger.info(ConstantsJSP.EMPTY);
		if (error != null) {
			req.setAttribute(ConstantsJSP.ERROR, ConstantsJSP.NO_AUTH);
			return ConstantsJSP.loginPage;
		}
		HttpSession session = req.getSession(true);
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authenticate = context.getAuthentication();
		String userName = authenticate.getName();
		Employee employee = null;
		String pageReturn = ConstantsJSP.loginPage;
		try {
			employee = workService.getEmployeeByUserName(userName);
			if(employee!=null){
				session.setAttribute(ConstantsJSP.EMPLOYEE, employee);
				pageReturn = "redirect:/"+ConstantsJSP.homeController;
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return pageReturn;
	}
}
