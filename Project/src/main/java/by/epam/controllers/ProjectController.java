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

import by.epam.beans.Employee;
import by.epam.beans.Member;
import by.epam.beans.Project;
import by.epam.beans.ProjectPosition;
import by.epam.beans.Role;
import by.epam.beans.Status;
import by.epam.beans.Task;
import by.epam.consts.ConstantsError;
import by.epam.consts.ConstantsJSP;
import by.epam.dao.DaoException;
import by.epam.dao.WorkServiceDAO;
import by.epam.services.ActivityService;

@Controller
public class ProjectController {

	private static final Logger logger = LoggerFactory
			.getLogger(ProjectController.class);
	@Autowired
	private WorkServiceDAO workService;
	@Autowired
	private ActivityService activityService;

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
		req.setAttribute(ConstantsJSP.STATUS_LIST, workService.getStatusList());
		String pageReturn = ConstantsJSP.projectEditPage;
		try {
			int id = Integer.parseInt(identity);
			Project project = workService.getProjectById(id);
			if (project != null) {
				req.setAttribute(ConstantsJSP.PROJECT, project);
			} else {
				req.setAttribute(ConstantsJSP.ERROR,
						ConstantsError.errorNotFound);
			}
		} catch (NumberFormatException e) {
			logger.info("error" + e.getMessage());
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.errorIncorrectId);
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
			@RequestParam(value = "status", required = false, defaultValue = ConstantsJSP.EMPTY) String status) {
		logger.info("==========[projectEdit]===========");
		String pageReturn = ConstantsJSP.projectEditPage;
		List<Status> statuses = workService.getStatusList();
		req.setAttribute(ConstantsJSP.STATUS_LIST, statuses);
		Status st = null;
		for (Status s : statuses) {
			if (s.getName().equals(status)) {
				st = s;
				break;
			}
		}
		try {
			int id = Integer.parseInt(identity);
			Project projectOld = workService.getProjectById(id);
			Project project = null;
			if (projectOld != null) {
				id = projectOld.getId();
				HttpSession session = req.getSession();
				Employee employee = (Employee) session
						.getAttribute(ConstantsJSP.EMPLOYEE);
				Member activityMember = workService.getProjectMember(id,
						employee.getId());
				try {
					if (activityMember == null) {
						activityMember = new Member();
						activityMember.setEmployee(employee);
						activityMember.setProject(projectOld);
						Role role = workService
								.getRoleName(ProjectPosition.ADMIN);
						activityMember.setRole(role);
						activityMember = workService.save(activityMember);// update
																			// id
																			// for
																			// activity
					}
					project = new Project();
					project.setDescription(description);
					project.setPlannedStartDate(psd);
					project.setPlannedEndDate(ped);
					project.setActualStartDate(asd);
					project.setActualEndDate(aed);
					project.setName(name);
					project.setStatus(st);
					project.setId(id);// id for update
					workService.update(project);
					activityService.activityChangeProject(projectOld, project,
							activityMember);
					pageReturn = "redirect:/" + ConstantsJSP.projectController;
				} catch (DaoException e1) {
					req.setAttribute(ConstantsJSP.ERROR, e1.getMessage());
					return "forward:/" + ConstantsJSP.projectUpdateController;
				}
				req.setAttribute(ConstantsJSP.PROJECT, project);
			} else {
				req.setAttribute(ConstantsJSP.ERROR,
						ConstantsError.errorNotFound);
			}
		} catch (NumberFormatException e) {
			logger.info("error" + e.getMessage());
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.errorIncorrectId);
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
		List<Status> statuses = workService.getStatusList();
		req.setAttribute(ConstantsJSP.STATUS_LIST, statuses);
		Status st = null;
		for (Status s : statuses) {
			if (s.getName().equals(status)) {
				st = s;
				break;
			}
		}
		Project project = null;
		try {
			project = new Project();
			project.setDescription(description);
			project.setPlannedStartDate(psd);
			project.setPlannedEndDate(ped);
			project.setActualStartDate(asd);
			project.setActualEndDate(aed);
			project.setName(name);
			project.setStatus(st);
			logger.info("project(without id)=" + project);
			project = workService.save(project); // get New project with id
			logger.info("project(with id)=" + project);
			HttpSession session = req.getSession();
			Employee employee = (Employee) session
					.getAttribute(ConstantsJSP.EMPLOYEE);
			Member member = new Member();
			member.setEmployee(employee);
			member.setProject(project);
			Role role = workService.getRoleName(ProjectPosition.ADMIN);
			member.setRole(role);
			workService.save(member);
			Member activityMember = workService.getProjectMember(
					project.getId(), employee.getId());
			activityService.activityCreateProject(project, activityMember);
			req.setAttribute(ConstantsJSP.PROJECT, project);
			pageReturn = "redirect:/" + ConstantsJSP.projectController;
		} catch (DaoException e1) {
			req.setAttribute(ConstantsJSP.ERROR, e1.getMessage());
			return "forward:/" + ConstantsJSP.projectUpdateController;
		}
		return pageReturn;
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
		try {
			int id = Integer.parseInt(identity);
			Project project = workService.getProjectById(id);
			if (project != null) {
				HttpSession session = req.getSession();
				Employee employee = (Employee) session
						.getAttribute(ConstantsJSP.EMPLOYEE);
				Member m = workService.getProjectMember(project.getId(),
						employee.getId());
				/*if(m==null){
					req.setAttribute(ConstantsJSP.ERROR,ConstantsError.projectErrorAccess);
					return "forward:/"+ConstantsJSP.error403Page;
				}*/
				req.setAttribute(ConstantsJSP.MEMBER, m);
				java.util.List<Task> list = workService
						.getTasksByProjectId(project.getId());
				logger.info("list=" + list);
				req.setAttribute(ConstantsJSP.PROJECT_TASKS, list);
				logger.info("project " + project);
				req.setAttribute(ConstantsJSP.PROJECT, project);
			}
		} catch (NumberFormatException e) {
			logger.info("error" + e.getMessage());
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.errorIncorrectId);
		}
		return ConstantsJSP.projectPage;
	}
}