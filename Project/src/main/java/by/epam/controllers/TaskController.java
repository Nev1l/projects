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
		Task task = null;
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
				task = workService.getTaskById(id);
				logger.info("task " + task);
				if (task != null) {
					Assignment assignee = workService
							.getAssignmentByTaskId(task.getId());
					req.setAttribute(ConstantsJSP.ASSIGNEE, assignee);
					req.setAttribute(ConstantsJSP.TASK, task);
				} else {
					req.setAttribute(ConstantsJSP.ERROR,
							ConstantsError.errorNotFound);
				}
			} else {
				req.setAttribute(ConstantsJSP.ERROR,
						ConstantsError.errorIncorrectId);
			}
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
		int projectId = 0;
		if (project_id != null) {
			try {
				projectId = Integer.parseInt(project_id);
				Project project = workService.getProjectById(projectId);
				req.setAttribute(ConstantsJSP.PROJECT,project);
				Task task = new Task();
				task.setProject(project);
				task.setDescription(description);
				task.setPlannedStartDate(psd);
				task.setPlannedEndDate(ped);
				task.setActualStartDate(asd);
				task.setActualEndDate(aed);
				for (Status s : statuses) {
					if (s.getName().equals(status)) {
						task.setStatus(s);
						break;
					}
				}
				req.setAttribute(ConstantsJSP.TASK, task);
				//http://stoflru.org/questions/25963720/how-to-compare-two-string-dates-in-java
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// yyyy/MM/dd
				try {
					boolean plannedCondition = sdf.parse(asd).before(sdf.parse(aed));
					boolean actualCondition = sdf.parse(psd).before(sdf.parse(ped));
					if(plannedCondition && actualCondition){
						workService.save(task);
						pageReturn = "forward:/" + ConstantsJSP.projectController;
					}else if(!plannedCondition){
						req.setAttribute(ConstantsJSP.ERROR, "Planned end date is incorrect (before planned start date)");
					}else{
						req.setAttribute(ConstantsJSP.ERROR, "Actual end date is incorrect (before actual start date)");
					}
				} catch (ParseException e) {
					req.setAttribute(ConstantsJSP.ERROR, ConstantsError.errorDateFormat);
				}
			} catch (NumberFormatException e) {
				req.setAttribute(ConstantsJSP.ERROR, ConstantsError.errorIncorrectId);
			}
		}
		return pageReturn;
	}

	@RequestMapping(value = "/taskNew.do", method = RequestMethod.POST)
	public String taskNew(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "id", required = false) String project_id) {
		int projectId = 0;
		if (project_id != null) {
			try {
				projectId = Integer.parseInt(project_id);
				Project project = workService.getProjectById(projectId);
				req.setAttribute(ConstantsJSP.PROJECT,project);
				List<Status> statuses = workService.getStatusList();
				req.setAttribute(ConstantsJSP.STATUS_LIST, statuses);
			} catch (NumberFormatException e) {
				req.setAttribute(ConstantsJSP.ERROR, ConstantsError.errorIncorrectId);
			}
		}
		return ConstantsJSP.taskNewPage;
	}

}
