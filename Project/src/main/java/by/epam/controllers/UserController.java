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
import org.springframework.web.bind.annotation.RequestParam;

import by.epam.beans.Assignment;
import by.epam.beans.Employee;
import by.epam.consts.ConstantsError;
import by.epam.consts.ConstantsJSP;
import by.epam.dao.WorkServiceDAO;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	@Autowired
	private WorkServiceDAO workService;

	@RequestMapping(value = "/userprofile.do")
	public String user(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "id", required = false) String identity,
			@RequestParam(value = "page", required = false) String page) {
		List<Assignment> listAssignments = null;
		int start = 0;
		try {
			Employee employee = workService.getEmployee(Integer
					.parseInt(identity));
			req.setAttribute(ConstantsJSP.EMPLOYEE_PROFILE, employee);
			int maxPageCount = workService
					.getCountAssignmentsByEmployeeId(employee.getId());
			start = 0;//Integer.parseInt(page);
			if (start > 0) {
				start = (start - 1) * ConstantsJSP.RESULTS_ON_LOAD;
			}
			listAssignments = workService.getEmployeeAssignments(
					employee.getId(), 0, ConstantsJSP.RESULTS_ON_LOAD);
			req.setAttribute(ConstantsJSP.EMPLOYEE_ASSIGNMENT, listAssignments);
			req.setAttribute(ConstantsJSP.RECORD_ON_PAGE,
					ConstantsJSP.RESULTS_ON_LOAD);
			req.setAttribute(ConstantsJSP.RECORD_COUNT, maxPageCount);
			req.setAttribute(ConstantsJSP.CUR_PAGE, start);
		} catch (NumberFormatException e) {
			logger.info("error:"+e.getMessage());
			req.setAttribute(ConstantsJSP.ERROR, ConstantsError.paramError);
		}
		return ConstantsJSP.userProfilePage;
	}
}
