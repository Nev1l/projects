package by.epam.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.epam.beans.Assignment;
import by.epam.beans.Project;
import by.epam.beans.Status;
import by.epam.beans.Task;
import by.epam.consts.ConstantsError;
import by.epam.consts.ConstantsJSP;
import by.epam.dao.DaoException;
import by.epam.dao.WorkServiceDAO;

@Controller
public class TaskController {
	private static final Logger logger = LoggerFactory
			.getLogger(TaskController.class);
	@Autowired
	private WorkServiceDAO workService;

	@RequestMapping(value = "/task.do")
	public String task(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "id", required = false) String identity) {
		logger.info(ConstantsJSP.EMPTY);
		try {
			int id = Integer.parseInt(identity);
			Task task = workService.getTaskById(id);
			logger.info("task " + task);
			if (task != null) {
				Assignment assignee = workService.getAssignmentByTaskId(task
						.getId());
				req.setAttribute(ConstantsJSP.ASSIGNEE, assignee);
				req.setAttribute(ConstantsJSP.TASK, task);
			} else {
				req.setAttribute(ConstantsJSP.ERROR,
						ConstantsError.errorNotFound);
			}
		} catch (NumberFormatException e) {
			logger.info("error" + e.getMessage());
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.errorIncorrectId);
		}
		return ConstantsJSP.taskPage;
	}

	@RequestMapping(value = "/taskAdd.do", method = RequestMethod.POST)
	public String taskAdd(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam(value = "id", required = false) String project_id,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "psd", required = false) String psd,
			@RequestParam(value = "ped", required = false) String ped,
			@RequestParam(value = "asd", required = false) String asd,
			@RequestParam(value = "aed", required = false) String aed,
			@RequestParam(value = "status", required = false) String status) {
		String pageReturn = ConstantsJSP.taskNewPage;
		List<Status> statuses = workService.getStatusList();
		req.setAttribute(ConstantsJSP.STATUS_LIST, statuses);
		try {
			int projectId = Integer.parseInt(project_id);
			Project project = workService.getProjectById(projectId);
			req.setAttribute(ConstantsJSP.PROJECT, project);
			Task task = new Task();
			try {
				task.setProject(project);
				task.setDescription(description);
				task.setPlannedStartDate(psd);
				task.setPlannedEndDate(ped);
				task.setActualStartDate(asd);
				task.setActualEndDate(aed);
				Status taskStatus = null;
				for (Status s : statuses) {
					if (s.getName().equals(status)) {
						taskStatus = s;
						break;
					}
				}
				task.setStatus(taskStatus);
				// task is null if it doesn't add to request
				req.setAttribute(ConstantsJSP.TASK, task);
				workService.save(task);
				pageReturn = "forward:/" + ConstantsJSP.projectController;
			} catch (DaoException e1) {
				req.setAttribute(ConstantsJSP.ERROR, e1.getMessage());
			}
		} catch (NumberFormatException e) {
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.errorIncorrectId);
		}
		return pageReturn;
	}

	@RequestMapping(value = "/taskEdit.do", method = RequestMethod.POST)
	public String taskAdd(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam(value = "id", required = false) String project_id,
			@RequestParam(value = "task_id", required = false) String task_id,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "psd", required = false) String psd,
			@RequestParam(value = "ped", required = false) String ped,
			@RequestParam(value = "asd", required = false) String asd,
			@RequestParam(value = "aed", required = false) String aed,
			@RequestParam(value = "status", required = false) String status) {
		logger.info("=============[taskAdd");
		String pageReturn = ConstantsJSP.taskNewPage;
		List<Status> statuses = workService.getStatusList();
		req.setAttribute(ConstantsJSP.STATUS_LIST, statuses);
		try {
			int projectId = Integer.parseInt(project_id);
			Project project = workService.getProjectById(projectId);
			req.setAttribute(ConstantsJSP.PROJECT, project);
			int taskId = Integer.parseInt(task_id);
			Task task = new Task();
			try {
				task.setId(taskId);
				task.setProject(project);
				task.setDescription(description);
				task.setPlannedStartDate(psd);
				task.setPlannedEndDate(ped);
				task.setActualStartDate(asd);
				task.setActualEndDate(aed);
				Status taskStatus = null;
				for (Status s : statuses) {
					if (s.getName().equals(status)) {
						taskStatus = s;
						break;
					}
				}
				task.setStatus(taskStatus);
				// task is null if it doesn't add to request
				req.setAttribute(ConstantsJSP.TASK, task);
				workService.update(task);
				pageReturn = "forward:/" + ConstantsJSP.projectController;
			} catch (DaoException e1) {
				req.setAttribute(ConstantsJSP.ERROR, e1.getMessage());
			}
		} catch (NumberFormatException e) {
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.errorIncorrectId);
		}
		return pageReturn;
	}

	@RequestMapping(value = "/taskUpdate.do", method = RequestMethod.POST)
	public String taskUpdate(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam(value = "id", required = false) String task_id) {
		logger.info("=============[taskUpdate");
		try {
			List<Status> statuses = workService.getStatusList();
			req.setAttribute(ConstantsJSP.STATUS_LIST, statuses);
			int taskId = Integer.parseInt(task_id);
			Task task = workService.getTaskById(taskId);
			req.setAttribute(ConstantsJSP.TASK, task);
		} catch (NumberFormatException e) {
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.errorIncorrectId);
		}
		return ConstantsJSP.taskEditPage;
	}

	@RequestMapping(value = "/taskNew.do", method = RequestMethod.POST)
	public String taskNew(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "id", required = false) String project_id) {
		try {
			int projectId = Integer.parseInt(project_id);
			Project project = workService.getProjectById(projectId);
			req.setAttribute(ConstantsJSP.PROJECT, project);
			List<Status> statuses = workService.getStatusList();
			req.setAttribute(ConstantsJSP.STATUS_LIST, statuses);
		} catch (NumberFormatException e) {
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.errorIncorrectId);
		}
		return ConstantsJSP.taskNewPage;
	}

}
