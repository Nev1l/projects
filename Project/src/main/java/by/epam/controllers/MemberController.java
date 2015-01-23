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
import by.epam.beans.Role;
import by.epam.consts.ConstantsError;
import by.epam.consts.ConstantsJSP;
import by.epam.dao.DaoException;
import by.epam.dao.WorkServiceDAO;
import by.epam.template.MemberAddTemplate;
import by.epam.template.MemberAddViewTemplate;
import by.epam.template.MemberEditTemplate;
import by.epam.template.MemberEditViewTemplate;
import by.epam.template.MemberTemplate;
import by.epam.template.MemberViewTemplate;

@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory
			.getLogger(MemberController.class);
	@Autowired
	private WorkServiceDAO workService;

	@RequestMapping(value = "/member.do")
	public String member(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "id", required = false) String identity) {
		logger.info("=====[member]=====");
		Project project = null;
		try {
			int id = Integer.parseInt(identity);
			project = workService.getProjectById(id);
			if (project != null) {
				logger.info("project " + project);
				req.setAttribute(ConstantsJSP.PROJECT, project);
				List<Member> memberList = workService
						.getMembersByProjectId(project.getId());
				req.setAttribute(ConstantsJSP.PROJECT_MEMBERS, memberList);
				logger.info("project members list=" + memberList);
				HttpSession session = req.getSession();
				Employee employee = (Employee) session
						.getAttribute(ConstantsJSP.EMPLOYEE);
				Member member = workService.getProjectMember(project.getId(),
						employee.getId());
				req.setAttribute(ConstantsJSP.MEMBER, member);
			} else {
				req.setAttribute(ConstantsJSP.ERROR,
						ConstantsError.errorNotFound);
			}
		} catch (NumberFormatException e) {
			logger.info("error" + e.getMessage());
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.errorIncorrectId);
		}
		return ConstantsJSP.memberPage;
	}

	@RequestMapping(value = "/memberDelete.do", method = RequestMethod.POST)
	public String memberDelete(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam(value = "id", required = false) String project_id,
			@RequestParam(value = "employee_id", required = false) String employee_id) {
		logger.info("=====[memberDelete]=====");
		String pageReturn = "forward:/" + ConstantsJSP.memberController;
		try {
			int projectId = Integer.parseInt(project_id);
			Project project = workService.getProjectById(projectId);
			req.setAttribute(ConstantsJSP.PROJECT, project);
			int employeeId = Integer.parseInt(employee_id);
			Member member = workService.getProjectMember(projectId, employeeId);
			if (member != null) {
				if(!workService.delete(member)){
					req.setAttribute(ConstantsJSP.ERROR,
							ConstantsError.memberDeleteError);
				}
			}
		} catch (NumberFormatException e) {
			logger.info("error:" + e);
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.errorIncorrectId);
		}
		return pageReturn;
	}

	@RequestMapping(value = "/memberAdd.do", method = RequestMethod.POST)
	public String memberAdd(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam(value = "id", required = false) String project_id,
			@RequestParam(value = "member_id[]", required = false) String[] member_id,
			@RequestParam(value = "employee_id[]", required = false) String[] employee_id,
			@RequestParam(value = "role[]", required = false) String[] role,
			@RequestParam(value = "check[]", required = false) String[] check) {
		logger.info("=============[memberAdd]==============");
		return memberTemplate(req, project_id, member_id, employee_id, role,
				check, new MemberAddTemplate());
	}

	@RequestMapping(value = "/memberEdit.do", method = RequestMethod.POST)
	public String memberEdit(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam(value = "id", required = false) String project_id,
			@RequestParam(value = "member_id[]", required = false) String[] member_id,
			@RequestParam(value = "employee_id[]", required = false) String[] employee_id,
			@RequestParam(value = "role[]", required = false) String[] role,
			@RequestParam(value = "check[]", required = false) String[] check) {
		logger.info("=============[memberEdit]==============");
		return memberTemplate(req, project_id, member_id, employee_id, role,
				check, new MemberEditTemplate());
	}

	public String memberTemplate(HttpServletRequest req, String project_id,
			String[] member_id, String[] employee_id, String[] role,
			String[] check, MemberTemplate membertemplate) {
		String pageReturn = "forward:/" + ConstantsJSP.memberController;
		if (employee_id == null || member_id == null || role == null
				|| check == null) {
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.errorParamIsNull);
			return pageReturn;
		}
		StringBuilder error = new StringBuilder();
		try {
			int projectId = Integer.parseInt(project_id);
			Project project = workService.getProjectById(projectId);
			req.setAttribute(ConstantsJSP.PROJECT, project);
			List<Role> roleList = workService.getRoleList();
			req.setAttribute(ConstantsJSP.ROLE_LIST, roleList);
			for (int i = 0; i < employee_id.length; i++) {
				if (check[i] != null) {
					if (check[i].equals(ConstantsJSP.CHECKED)) {
						try {
							int employeeId = Integer.parseInt(employee_id[i]);
							Employee employee = workService
									.getEmployee(employeeId);
							Role memberRole = null;
							for (Role r : roleList) {
								if (r.getName().equals(role[i])) {
									memberRole = r;
									break;
								}
							}
							try {
								//save or update (0 || [any id] in db)
								int memberId = membertemplate.getId(workService, member_id[i]);
								Member member = new Member();
								member.setId(memberId);
								member.setProject(project);
								member.setEmployee(employee);
								member.setRole(memberRole);
								membertemplate.execute(workService, member);
							} catch (DaoException e) {
								logger.info("error:" + e);
								error.append(e.getMessage());
							}
						} catch (NumberFormatException e) {
							logger.info("error:" + e);
							error.append("ParseError:employee_id;");
						}
					}
				}
			}
		} catch (NumberFormatException e) {
			logger.info("error:" + e);
			error.append("ParseError:project_id=" + project_id + ";");
		}
		req.setAttribute(ConstantsJSP.ERROR, error.toString());
		return pageReturn;
	}

	@RequestMapping(value = "/memberNew.do", method = RequestMethod.POST)
	public String memberNew(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "id", required = false) String project_id) {
		logger.info("=====[memberNew]=====");
		memberView(req, project_id, new MemberAddViewTemplate());
		return ConstantsJSP.memberNewPage;
	}

	@RequestMapping(value = "/memberUpdate.do", method = RequestMethod.POST)
	public String memberUpdate(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "id", required = false) String project_id) {
		logger.info("=====[memberUpdate]=====");
		memberView(req, project_id, new MemberEditViewTemplate());
		return ConstantsJSP.memberEditPage;
	}

	public void memberView(HttpServletRequest req, String project_id,
			MemberViewTemplate membertemplate) {
		try {
			int projectId = Integer.parseInt(project_id);
			Project project = workService.getProjectById(projectId);
			req.setAttribute(ConstantsJSP.PROJECT, project);
			List<Role> roleList = workService.getRoleList();
			req.setAttribute(ConstantsJSP.ROLE_LIST, roleList);
			membertemplate.execute(req, workService, projectId);
		} catch (NumberFormatException e) {
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.errorIncorrectId);
		}
	}
}