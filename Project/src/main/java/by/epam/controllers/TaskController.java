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

import by.epam.beans.Assignment;
import by.epam.beans.Employee;
import by.epam.beans.Member;
import by.epam.beans.Project;
import by.epam.beans.Status;
import by.epam.beans.Task;
import by.epam.consts.ConstantsError;
import by.epam.consts.ConstantsJSP;
import by.epam.dao.DaoException;
import by.epam.dao.WorkServiceDAO;
import by.epam.filter.TaskFilter;
import by.epam.filter.TaskFilterByProjectDecorator;
import by.epam.filter.TaskFilterByStatusDecorator;
import by.epam.filter.TaskFilterByTaskNameDecorator;
import by.epam.filter.TaskFilterDecorator;
import by.epam.services.ActivityService;
import by.epam.utils.PageNavigator;

@Controller
public class TaskController {
	private static final Logger logger = LoggerFactory
			.getLogger(TaskController.class);
	@Autowired
	private WorkServiceDAO workService;

	@Autowired
	private ActivityService activityService;

	@RequestMapping(value = "/task.do")
	public String task(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "id", required = false) String identity) {
		logger.info(ConstantsJSP.EMPTY);
		try {
			int id = Integer.parseInt(identity);
			Task task = workService.getTaskById(id);
			logger.info("task " + task);
			if (task != null) {
				Assignment assignee = workService.getLastAssigneeByTaskId(task
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
			@RequestParam(value = "assign_member_id", required = false) String assign_member_id,
			@RequestParam(value = "description", required = false) String description,
			//@RequestParam(value = "summary", required = false) String summary,
			@RequestParam(value = "psd", required = false) String psd,
			@RequestParam(value = "ped", required = false) String ped,
			@RequestParam(value = "asd", required = false) String asd,
			@RequestParam(value = "aed", required = false) String aed,
			@RequestParam(value = "status", required = false) String status) {
		String pageReturn = "forward:/" + ConstantsJSP.taskNewPage;
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
				req.setAttribute(ConstantsJSP.TASK, task);
				task = workService.save(task);
				HttpSession session = req.getSession();
				Employee employee = (Employee) session
						.getAttribute(ConstantsJSP.EMPLOYEE);
				int assignMemberId = Integer.parseInt(assign_member_id);
				Member member = workService.getMemberById(assignMemberId);
				Assignment assignment = new Assignment();
				assignment.setAssignDate(Assignment.getCurrentDateTime());
				assignment.setMember(member);
				assignment.setTask(task);
				//assignment.setDescription(summary);
				workService.save(assignment);
				Member activityMember = workService.getProjectMember(
						project.getId(), employee.getId());
				activityService.activityCreateAssignee(assignment,
						activityMember);
				pageReturn = "forward:/" + ConstantsJSP.projectController;
			} catch (DaoException e1) {
				req.setAttribute(ConstantsJSP.ERROR, e1.getMessage());
				return "forward:/" + ConstantsJSP.taskNewController;
			}
		} catch (NumberFormatException e) {
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.errorIncorrectId);
		}
		return pageReturn;
	}

	@RequestMapping(value = "/taskEdit.do", method = RequestMethod.POST)
	public String taskEdit(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam(value = "id", required = false) String project_id,
			@RequestParam(value = "assign_member_id", required = false) String assign_member_id,
			@RequestParam(value = "task_id", required = false) String task_id,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "psd", required = false) String psd,
			@RequestParam(value = "ped", required = false) String ped,
			@RequestParam(value = "asd", required = false) String asd,
			@RequestParam(value = "aed", required = false) String aed,
			@RequestParam(value = "status", required = false) String status) {
		logger.info("=============[taskEdit");
		String pageReturn = ConstantsJSP.taskEditPage;// "forward:/"+
		List<Status> statuses = workService.getStatusList();
		req.setAttribute(ConstantsJSP.STATUS_LIST, statuses);
		try {
			int projectId = Integer.parseInt(project_id);
			Project project = workService.getProjectById(projectId);
			req.setAttribute(ConstantsJSP.PROJECT, project);
			int taskId = Integer.parseInt(task_id);
			Task oldTask = workService.getTaskById(taskId);
			if (oldTask != null) {
				try {
					Task task = new Task();
					task.setId(oldTask.getId());
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
					workService.update(task);
					HttpSession session = req.getSession();
					Employee employee = (Employee) session
							.getAttribute(ConstantsJSP.EMPLOYEE);
					Member activityMember = workService.getProjectMember(task
							.getProject().getId(), employee.getId());
					activityService.activityChangeTask(oldTask, task,
							activityMember);
					int assignMemberId = Integer.parseInt(assign_member_id);
					Assignment oldAssignment = workService
							.getLastAssigneeByTaskId(task.getId());
					Member currentMember = workService
							.getMemberById(assignMemberId);
					if (oldAssignment.getMember().getId() != currentMember
							.getId()) {
						Assignment assignment = new Assignment();
						assignment.setAssignDate(Assignment
								.getCurrentDateTime());
						assignment.setMember(currentMember);
						assignment.setTask(task);
						workService.save(assignment);
						activityService.activityChangeAssignee(assignment,
								activityMember);
					}
					pageReturn = "forward:/" + ConstantsJSP.projectController;
				} catch (DaoException e1) {
					req.setAttribute(ConstantsJSP.ERROR, e1.getMessage());
					req.setAttribute(ConstantsJSP.id, task_id);
					return "forward:/" + ConstantsJSP.taskUpdateController;
				}
			}
		} catch (NumberFormatException e) {
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.errorIncorrectId);
		}
		return pageReturn;
	}

	@RequestMapping(value = "/taskUpdate.do", method = RequestMethod.POST)
	public String taskUpdate(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "id", required = false) String task_id) {
		logger.info("=============[taskUpdate");
		try {
			List<Status> statuses = workService.getStatusList();
			int taskId = Integer.parseInt(task_id);
			Task task = workService.getTaskById(taskId);
			HttpSession session = req.getSession();
			Employee employee = (Employee) session
					.getAttribute(ConstantsJSP.EMPLOYEE);
			Member member = workService.getProjectMember(task.getProject()
					.getId(), employee.getId());
			List<Member> memberList = workService.getMembersByProjectId(task
					.getProject().getId());
			if (member != null) {
				req.setAttribute(ConstantsJSP.MEMBER_ID, member.getId());
			}
			Assignment assignee = workService.getLastAssigneeByTaskId(task
					.getId());
			req.setAttribute(ConstantsJSP.ASSIGNEE, assignee);
			req.setAttribute(ConstantsJSP.STATUS_LIST, statuses);
			req.setAttribute(ConstantsJSP.TASK, task);
			req.setAttribute(ConstantsJSP.PROJECT_MEMBERS, memberList);
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
			List<Status> statuses = workService.getStatusList();
			HttpSession session = req.getSession();
			Employee employee = (Employee) session
					.getAttribute(ConstantsJSP.EMPLOYEE);
			Member member = workService.getProjectMember(projectId,
					employee.getId());
			List<Member> memberList = workService.getMembersByProjectId(project
					.getId());
			if (member != null) {
				req.setAttribute(ConstantsJSP.MEMBER_ID, member.getId());
			}
			req.setAttribute(ConstantsJSP.PROJECT, project);
			req.setAttribute(ConstantsJSP.STATUS_LIST, statuses);
			req.setAttribute(ConstantsJSP.PROJECT_MEMBERS, memberList);
		} catch (NumberFormatException e) {
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.errorIncorrectId);
		}
		return ConstantsJSP.taskNewPage;
	}

	@RequestMapping(value = "/tasks.do")
	public String tasks(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "fs", required = false) String filterStatus,
			@RequestParam(value = "fp", required = false) String filterProject,
			@RequestParam(value = "ftn", required = false) String filterTaskName,
			HttpServletRequest req, HttpServletResponse res) {
		logger.info("tasks.do");
		//workService.getCountAssignment(null);
		int start = 0;
		int cur_page = 1;
		try {
			cur_page = Integer.parseInt(page);
			if (cur_page > 1) {
				start = (cur_page - 1) * ConstantsJSP.RESULTS_ON_LOAD;
			}
		} catch (NumberFormatException e) {
			logger.info("ErrorParsePage:"+e.getMessage());
		}
		int recordCount = 0;
		try {
			TaskFilterDecorator filter = new TaskFilterDecorator(
					new TaskFilter());
			if (filterStatus != null) {
				filter = new TaskFilterByStatusDecorator(filter, filterStatus);
				req.setAttribute("fs", filterStatus);
			}
			if (filterProject != null) {
				filter = new TaskFilterByProjectDecorator(filter, filterProject);
				req.setAttribute("fp", filterProject);
			}
			if (filterTaskName != null) {
				filter = new TaskFilterByTaskNameDecorator(filter,
						filterTaskName);
				req.setAttribute("ftn", filterTaskName);
			}
			recordCount = workService.getCountAssignment(filter);
			logger.info("recordCount="+recordCount);
			int total = (int) Math.ceil(recordCount * (1.0d)
					/ ConstantsJSP.RESULTS_ON_LOAD * (1.0d));
			if (start > recordCount) {
				start = recordCount - ConstantsJSP.RESULTS_ON_LOAD;
				cur_page = total;
				req.setAttribute("page", cur_page);
			}
			List<Assignment> listAssignments = workService.getAssignment(
					filter, start, ConstantsJSP.RESULTS_ON_LOAD);
			logger.info("list:"+listAssignments);
			req.setAttribute(ConstantsJSP.ASSIGNMENTS, listAssignments);
			PageNavigator pageNavigator = new PageNavigator(total, cur_page);
			req.setAttribute(ConstantsJSP.PAGE_NAVIGATOR, pageNavigator);
			req.setAttribute(ConstantsJSP.PROJECT_LIST,
					workService.getAllProjects());
			req.setAttribute(ConstantsJSP.STATUS_LIST,
					workService.getStatusList());
		} catch (Exception e) {
			logger.info("Error:" + e.getMessage());
		}
		return ConstantsJSP.tasksPage;//ConstantsJSP.tasksPage;
	}
}
