package by.epam.controllers;

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

import by.epam.beans.Member;
import by.epam.beans.Project;
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

	@RequestMapping(value = "/project.do", method = RequestMethod.GET)
	public String project(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession(true);
		Member member = (Member) session.getAttribute(ConstantsJSP.MEMBER);
		if (member.getRole().isDeveloper()) {
			logger.info("true");
			req.setAttribute(ConstantsJSP.PROJECT_LIST,
					workService.getProjectsByMemberId(member.getId()));
		} else {
			logger.info("false");
			req.setAttribute(ConstantsJSP.PROJECT_LIST,
					workService.getAllProjects());
		}
		return ConstantsJSP.projectPage;
	}

	@RequestMapping(value = "/projectAdd.do", method = RequestMethod.POST)
	public String projectAdd(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "id", required = false) String identity) {
		return null;
	}

	@RequestMapping(value = "/project.do", method = RequestMethod.POST)
	public String project(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "id", required = false) String identity) {
		logger.info(ConstantsJSP.EMPTY);
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
					java.util.List<Task> list = workService
							.getTasksByProjectId(project.getId());
					logger.info("list="+list);
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