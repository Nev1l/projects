package by.epam.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import by.epam.beans.Employee;
import by.epam.beans.Member;
import by.epam.beans.Project;
import by.epam.beans.Status;
import by.epam.beans.Task;
import by.epam.consts.ConstantsError;
import by.epam.consts.ConstantsJSP;
import by.epam.dao.WorkServiceDAO;

@Controller
public class ProjectController {

	private static final Logger logger = LoggerFactory
			.getLogger(ProjectController.class);
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired
	private WorkServiceDAO workService;

	@RequestMapping(value = "/projectEdit.do", method = RequestMethod.POST)
	public String projectEdit(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "id", required = false) String identity) {
		List<Status> statuses = workService.getStatusList();
		req.setAttribute(ConstantsJSP.STATUS_LIST, statuses);
		logger.info("==========[projectEdit]===========");
		if (identity != null) {
			int id = 0;
			try {
				id = Integer.parseInt(identity);
			} catch (NumberFormatException e) {
				logger.info("error" + e.getMessage());
				req.setAttribute(ConstantsJSP.ERROR,
						ConstantsError.errorIncorrectId);
			}
			if (id > 0) {
				Project project = workService.getProjectById(id);
				if (project != null) {
					req.setAttribute(ConstantsJSP.PROJECT, project);

				} else {
					req.setAttribute(ConstantsJSP.ERROR,
							ConstantsError.errorNotFound);
				}
			} else {
				req.setAttribute(ConstantsJSP.ERROR,
						ConstantsError.errorIncorrectId);
			}
		} else {
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.errorParamIsNull);
		}
		return ConstantsJSP.projectEditPage;
	}

	@RequestMapping(value = "/projectChange.do", method = RequestMethod.POST)
	public String projectChange(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "psd", required = false) String psd,
			@RequestParam(value = "ped", required = false) String ped,
			@RequestParam(value = "asd", required = false) String asd,
			@RequestParam(value = "aed", required = false) String aed,
			@RequestParam(value = "status", required = false) String status) {
		logger.info("==========[projectChange]===========");
		List<Status> statuses = workService.getStatusList();
		req.setAttribute(ConstantsJSP.STATUS_LIST, statuses);
		String pageReturn = ConstantsJSP.projectEditPage;// good
		pageReturn = "redirect:/" + ConstantsJSP.projectController;// bad
		projectAdd(req, res, name, description, psd, ped, asd, aed, status);
		return pageReturn;
	}



	@RequestMapping(value = "/projectAdd.do", method = RequestMethod.POST)
	public String projectAdd(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "psd", required = false) String psd,
			@RequestParam(value = "ped", required = false) String ped,
			@RequestParam(value = "asd", required = false) String asd,
			@RequestParam(value = "aed", required = false) String aed,
			@RequestParam(value = "status", required = false) String status) {
		String pageReturn = ConstantsJSP.projectNewPage;
		List<Status> statuses = workService.getStatusList();
		req.setAttribute(ConstantsJSP.STATUS_LIST, statuses);
		Project project = new Project();
		project.setName(name);
		project.setDescription(description);
		// date format 2015-01-18 (check format and convert)
		// ========[HARDCODE]==============
		// psd = "2012-01-18";
		// ped = "2012-05-21";
		// asd = "2012-02-18";
		// aed = "2012-04-24";
		// ========[END HARDCODE]===========
		project.setPlannedStartDate(psd);
		project.setPlannedEndDate(ped);
		project.setActualStartDate(asd);
		project.setActualEndDate(aed);
		for (Status s : statuses) {
			if (s.getName().equals(status)) {
				project.setStatus(s);
				break;
			}
		}
		req.setAttribute(ConstantsJSP.PROJECT, project);
		// http://stoflru.org/questions/25963720/how-to-compare-two-string-dates-in-java
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// yyyy/MM/dd
		try {
			boolean plannedCondition = sdf.parse(asd).before(sdf.parse(aed));
			boolean actualCondition = sdf.parse(psd).before(sdf.parse(ped));
			if (plannedCondition && actualCondition) {
				workService.save(project);
				pageReturn = "redirect:/" + ConstantsJSP.projectController;
			} else if (!plannedCondition) {
				req.setAttribute(ConstantsJSP.ERROR,
						"Planned end date is incorrect (before planned start date)");
			} else {
				req.setAttribute(ConstantsJSP.ERROR,
						"Actual end date is incorrect (before actual start date)");
			}
		} catch (ParseException e) {
			req.setAttribute(ConstantsJSP.ERROR, ConstantsError.errorDateFormat);
		}
		return pageReturn;
	}

	@RequestMapping(value = "/projectNew.do", method = RequestMethod.POST)
	public String projectNew(HttpServletRequest req, HttpServletResponse res) {
		List<Status> statuses = workService.getStatusList();
		req.setAttribute(ConstantsJSP.STATUS_LIST, statuses);
		return ConstantsJSP.projectNewPage;
	}

	@RequestMapping(value = "/project.do", method = RequestMethod.GET)
	public String project(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		Employee employee = (Employee) session
				.getAttribute(ConstantsJSP.EMPLOYEE);
		if (employee.getPosition().isAdmin()) {
			req.setAttribute(ConstantsJSP.PROJECT_LIST,
					workService.getAllProjects());
		} else {
			req.setAttribute(ConstantsJSP.MEMBER_LIST,
					workService.getMembersByEmployeeId(employee.getId()));
		}
		return ConstantsJSP.projectPage;
	}
	
	@RequestMapping(value = "/project.do", method = RequestMethod.POST)
	public String project(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "id", required = false) String identity) {
		logger.info(ConstantsJSP.EMPTY);
		HttpSession session = req.getSession();
		Employee employee = (Employee) session
				.getAttribute(ConstantsJSP.EMPLOYEE);
		if (identity != null) {
			int id = 0;
			try {
				id = Integer.parseInt(identity);
			} catch (NumberFormatException e) {
				logger.info("error" + e.getMessage());
				req.setAttribute(ConstantsJSP.ERROR,
						ConstantsError.errorIncorrectId);
			}
			if (id > 0) {
				Project project = workService.getProjectById(id);
				if (project != null) {
					req.setAttribute(ConstantsJSP.MEMBER,
							workService.getProjectMember(project.getId(),
									employee.getId()));
					java.util.List<Task> list = workService
							.getTasksByProjectId(project.getId());
					logger.info("list=" + list);
					req.setAttribute(ConstantsJSP.PROJECT_TASKS, list);
					logger.info("project " + project);
					req.setAttribute(ConstantsJSP.PROJECT, project);
				} else {
					req.setAttribute(ConstantsJSP.ERROR,
							ConstantsError.errorNotFound);
				}
			} else {
				req.setAttribute(ConstantsJSP.ERROR,
						ConstantsError.errorIncorrectId);
			}
		} else {
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.errorParamIsNull);
		}
		return ConstantsJSP.projectPage;
	}
}