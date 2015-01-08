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
		/*Employee employee = null;
		Member member = null;
		String errorMessage = null;
		HttpSession session = req.getSession(true);
		session.setAttribute("LOGIN_TEST", "LOGIN_TEST!");
		*/
		//====================coppy to HomeController
		//SecurityContext context = SecurityContextHolder.getContext();
		// (SecurityContext)
		//// session.getAttribute(ConstantsJSP.SECURUTY_CONTEXT);//
		//String userName = null;// "guest";if (context != null) {
		//Authentication authenticate = context.getAuthentication();
		//userName = authenticate.getName();
		//+возможно нужно брать из сессии контекст т.к. он должен быть всегда, если логин успешный
		//======================================================
		/*logger.info("auth=" + authenticate.isAuthenticated());
		logger.info("userName=" + userName);
		if (error != null) {
			errorMessage = ConstantsJSP.NO_AUTH;
			logger.info("error=" + errorMessage);
			session.setAttribute(ConstantsJSP.ERROR, errorMessage);
		} else {
			try {
				if (!userName.equals(ConstantsJSP.GUEST)) {
					//====================remove to HomeController
					employee = workService.getEmployeeByUserName(userName);
					logger.info("employee=" + employee.toString());
					member = workService
							.getMemberByEmployeeId(employee.getId());
					logger.info("member=" + member.getRole().getName());
					session.setAttribute(ConstantsJSP.EMPLOYEE, employee);
					//====================//подставить другую страницу, будет ли редирект?
					pageReturn = "forward:/" + ConstantsJSP.homeController;
				}
			} catch (Exception e) {
				logger.info(e.getMessage());
				errorMessage = ConstantsError.errorServer;
			}
		}*/
		// workService.get last activity
		logger.info(pageReturn);
		return pageReturn;
	}
}
