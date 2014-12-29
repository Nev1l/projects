package by.epam.controllers;

import java.util.Enumeration;

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

import by.epam.beans.Employee;
import by.epam.beans.Member;
import by.epam.beans.User;
import by.epam.consts.ConstantsError;
import by.epam.consts.ConstantsJSP;
import by.epam.consts.ConstantsLogger;
import by.epam.dao.WorkDAO;
import by.epam.dao.WorkServiceDAO;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	@Autowired
	private WorkServiceDAO workService;

	@RequestMapping(value = "/login.do")
	public String login(HttpServletRequest req, HttpServletResponse res) {
		logger.info("");
		String pageReturn = ConstantsJSP.homePage;
		Employee employee = null;
		Member member = null;
		HttpSession session = req.getSession(true);
		String errorMessage = ConstantsJSP.EMPTY;
		Exception ex = (Exception) session
				.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if (ex!=null) {
			errorMessage = ex.getMessage();
			pageReturn = ConstantsJSP.loginPage;
		}
		logger.info("Exeption:"+errorMessage);
		try {
			SecurityContext context = SecurityContextHolder.getContext();//(SecurityContext) session.getAttribute(ConstantsJSP.SECURUTY_CONTEXT);
			Authentication authenticate = context.getAuthentication();
			String userName = authenticate.getName();
			logger.info("userName="+userName);
			if(!userName.equals(ConstantsJSP.USER_GUEST)){
				employee = workService.getEmployeeByUserName(userName);
				logger.info("employee="+employee.toString());
				member = workService.getMemberByEmployeeId(employee.getId());
				logger.info("member"+member.getRole().getName());
			} else {
				pageReturn = ConstantsJSP.loginPage;
				logger.info("guest!");
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			errorMessage = ConstantsError.errorServer;
		}
		session.setAttribute(ConstantsJSP.ATT_ERROR, errorMessage);
		session.setAttribute(ConstantsJSP.ATT_EMPLOYEE, employee);
		// workService.get last activity
		return pageReturn;
	}
}
