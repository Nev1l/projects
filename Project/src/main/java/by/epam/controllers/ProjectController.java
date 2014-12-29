package by.epam.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import by.epam.beans.Assignment;
import by.epam.beans.Employee;
import by.epam.consts.ConstantsJSP;
import by.epam.consts.ConstantsLogger;
import by.epam.dao.WorkDAO;
import by.epam.dao.WorkServiceDAO;

@Controller
public class ProjectController {

	private static final Logger logger = LoggerFactory
			.getLogger(ProjectController.class);
	public final static String pageLogger = ConstantsLogger.loggerPrefix
			+ ConstantsJSP.homePage + ConstantsLogger.loggerSuffix;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired
	private WorkServiceDAO workService;

	@RequestMapping(value = "/project.do")
	public String home(HttpServletRequest req, HttpServletResponse res) {
		logger.info(pageLogger);
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
}