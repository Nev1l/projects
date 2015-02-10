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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import by.epam.beans.Activity;
import by.epam.beans.Assignment;
import by.epam.beans.Employee;
import by.epam.beans.Member;
import by.epam.beans.Project;
import by.epam.consts.ConstantsJSP;
import by.epam.dao.TaskFilterDAO;
import by.epam.dao.WorkServiceDAO;
import by.epam.filter.TaskFilter;
import by.epam.filter.TaskFilterDecorator;
import by.epam.utils.PageNavigator;
import by.epam.utils.ProjectByMemberAccess;

@Controller
public class AngularController {
	private static final Logger logger = LoggerFactory
			.getLogger(AngularController.class);

	@Autowired
	private WorkServiceDAO workService;

	@RequestMapping(value = "/ajax.projects.do", method = RequestMethod.GET)
	public @ResponseBody
	List<Project> projects(HttpServletRequest req, HttpServletResponse res) {
		logger.info(ConstantsJSP.EMPTY);
		res.setHeader("Accept", "application/json");
		HttpSession session = req.getSession();
		Employee employee = (Employee) session
				.getAttribute(ConstantsJSP.EMPLOYEE);
		List<Project> list = null;
		if (employee.getPosition().isAdmin()) {
			list = workService.getAllProjects();
		} else {
			List<Member> memberProjectAccess = workService
					.getMembersByEmployeeId(employee.getId());
			list = ProjectByMemberAccess
					.getProjectsWhereAccessMoreThanDeveloper(memberProjectAccess);
		}
		return list;
	}

	@RequestMapping(value = "/ajax.members.do", method = RequestMethod.GET)
	public @ResponseBody
	List<Member> members(@RequestParam(value = "id", required = false) int id,
			HttpServletRequest req, HttpServletResponse res) {
		res.setHeader("Accept", "application/json");
		logger.info(ConstantsJSP.EMPTY);
		return workService.getMembersByProjectId(id);
	}

	@RequestMapping(value = "/ajax.tasks.do", method = RequestMethod.GET)
	public @ResponseBody
	List<Member> tasks(
			@RequestParam(value = "page", required = false) String page,
			HttpServletRequest req, HttpServletResponse res) {
		res.setHeader("Accept", "application/json");
		logger.info(ConstantsJSP.EMPTY);
		int start = 0;
		int cur_page = 1;
		try {
			cur_page = Integer.parseInt(page);
			if (cur_page > 1) {
				start = (cur_page - 1) * ConstantsJSP.RESULTS_ON_LOAD;
			}
		} catch (NumberFormatException e) {
			logger.info(e.getMessage());
		}
		int recordCount = 0;
		try {
			recordCount = 0;//work.getCount
			int total = (int) Math.ceil(recordCount * (1.0d)
					/ ConstantsJSP.RESULTS_ON_LOAD * (1.0d));
			if (start > recordCount) {
				start = recordCount - ConstantsJSP.RESULTS_ON_LOAD;
				cur_page = total;
				req.setAttribute("page", cur_page);
			}
			TaskFilterDecorator filter = new TaskFilterDecorator(new TaskFilter());
			List<Assignment> listAssignments = workService.getAssignment(filter, start, ConstantsJSP.RESULTS_ON_LOAD)
			logger.info("after method getEmployeeAssignments="
					+ listAssignments);
			req.setAttribute(ConstantsJSP.EMPLOYEE_ASSIGNMENT, listAssignments);
			PageNavigator pageNavigator = new PageNavigator(total, cur_page);
			req.setAttribute(ConstantsJSP.PAGE_NAVIGATOR, pageNavigator);
		} catch (Exception e) {
			logger.info("Error:" + e.getMessage());
		}
		return listAssignments;
	}
}

/*
 * copy-past form Task NEW
 * 
 * @RequestParam(value = "id", required = false) String project_id,
 * 
 * @RequestParam(value = "assign_member_id", required = false) String
 * assign_member_id,
 * 
 * @RequestParam(value = "description", required = false) String description,
 * 
 * @RequestParam(value = "psd", required = false) String psd,
 * 
 * @RequestParam(value = "ped", required = false) String ped,
 * 
 * @RequestParam(value = "asd", required = false) String asd,
 * 
 * @RequestParam(value = "aed", required = false) String aed,
 * 
 * @RequestParam(value = "status", required = false) String statu
 */