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
import by.epam.beans.ProjectPosition;
import by.epam.beans.Role;
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

	
	@RequestMapping(value = "/projectUpdate.do", method = RequestMethod.POST)
	public String projectUpdate(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam(value = "id", required = false) String identity,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "psd", required = false) String psd,
			@RequestParam(value = "ped", required = false) String ped,
			@RequestParam(value = "asd", required = false) String asd,
			@RequestParam(value = "aed", required = false) String aed,
			@RequestParam(value = "status", required = false) String status) {
		logger.info("==========[projectUpdate]===========");
		req.setAttribute(ConstantsJSP.STATUS_LIST,
		workService.getStatusList());
		String pageReturn = ConstantsJSP.projectEditPage;
		Project project = projectCheckId(req, identity);
		if (project != null) {
			req.setAttribute(ConstantsJSP.PROJECT, project);
		}
		return pageReturn;
	}
	
	@RequestMapping(value = "/projectEdit.do", method = RequestMethod.POST)
	public String projectEdit(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam(value = "id", required = false) String identity,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "psd", required = false) String psd,
			@RequestParam(value = "ped", required = false) String ped,
			@RequestParam(value = "asd", required = false) String asd,
			@RequestParam(value = "aed", required = false) String aed,
			@RequestParam(value = "status", required = false) String status) {
		logger.info("==========[projectEdit]===========");
		// ======[this method in ---> projectCheck]======
		// req.setAttribute(ConstantsJSP.STATUS_LIST,
		// workService.getStatusList());
		String pageReturn = ConstantsJSP.projectEditPage;
		// 1.projectCheck method always uses first (init status list)
		// 2.projectCheckId
		Project project = projectCheck(req, res, name, description, psd, ped,
				asd, aed, status);
		if (project != null) {
			//
			Project updatedProjectId = projectCheckId(req, identity);
			if (updatedProjectId != null) {
				project.setId(updatedProjectId.getId());// id for update
				workService.update(project);
				pageReturn = "redirect:/" + ConstantsJSP.projectController;
			}
		}
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
		logger.info("==========[projectAdd]===========");
		String pageReturn = ConstantsJSP.projectNewPage;
		Project project = projectCheck(req, res, name, description, psd, ped,
				asd, aed, status);
		if (project != null) {
			project = workService.save(project); //get New project with id
			logger.info("project="+project);
			HttpSession session = req.getSession();
			Employee employee = (Employee) session.getAttribute(ConstantsJSP.EMPLOYEE);
			Member member = new Member();
			member.setEmployee(employee);
			member.setProject(project);
			Role role=workService.getRoleName(ProjectPosition.ADMIN);
			member.setRole(role);
			workService.save(member);
			pageReturn = "redirect:/" + ConstantsJSP.projectController;
		}
		return pageReturn;
	}

	private Project projectCheck(HttpServletRequest req,
			HttpServletResponse res, String name, String description,
			String psd, String ped, String asd, String aed, String status) {
		List<Status> statuses = workService.getStatusList();
		req.setAttribute(ConstantsJSP.STATUS_LIST, statuses);
		Project project = new Project();
		project.setName(name);
		project.setDescription(description);
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
				return project;
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
		return null;
	}

	@RequestMapping(value = "/projectNew.do", method = RequestMethod.POST)
	public String projectNew(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute(ConstantsJSP.STATUS_LIST, workService.getStatusList());
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
		Project project = projectCheckId(req, identity);
		if (project != null) {
			HttpSession session = req.getSession();
			Employee employee = (Employee) session
					.getAttribute(ConstantsJSP.EMPLOYEE);
			req.setAttribute(
					ConstantsJSP.MEMBER,
					workService.getProjectMember(project.getId(),
							employee.getId()));
			java.util.List<Task> list = workService.getTasksByProjectId(project
					.getId());
			logger.info("list=" + list);
			req.setAttribute(ConstantsJSP.PROJECT_TASKS, list);
			logger.info("project " + project);
			req.setAttribute(ConstantsJSP.PROJECT, project);
		}
		return ConstantsJSP.projectPage;
	}

	public Project projectCheckId(HttpServletRequest req, String identity) {
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
					return project;
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
		return null;
	}
}