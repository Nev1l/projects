package by.epam.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.epam.consts.ConstantsJSP;
import by.epam.dao.WorkServiceDAO;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	@Autowired
	private WorkServiceDAO workService;

	//http://docs.spring.io/spring-security/site/docs/3.0.x/reference/technical-overview.html
	
	@RequestMapping(value = "/login.do")
	public String login(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "error", required = false) String error) {
		String pageReturn = ConstantsJSP.loginPage;
		if(error!=null){
			req.setAttribute(ConstantsJSP.ERROR,ConstantsJSP.NO_AUTH);
		}
		logger.info(pageReturn);
		return pageReturn;
	}
}
