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
import by.epam.beans.Status;
import by.epam.consts.ConstantsError;
import by.epam.consts.ConstantsJSP;
import by.epam.dao.WorkServiceDAO;

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
				project = workService.getProjectById(id);
				if (project != null) {
					logger.info("project " + project);
					req.setAttribute(ConstantsJSP.PROJECT, project);
					List<Member> memberList = workService
							.getMembersByProjectId(project.getId());
					req.setAttribute(ConstantsJSP.PROJECT_MEMBERS, memberList);
					logger.info("project members list=" + memberList);
					HttpSession session = req.getSession();
					Employee employee = (Employee) session.getAttribute(ConstantsJSP.EMPLOYEE);
					Member member = workService.getProjectMember(project.getId(), employee.getId());
					req.setAttribute(ConstantsJSP.MEMBER, member);
				} else {
					logger.info("project is null");
					req.setAttribute(ConstantsJSP.ERROR,
							ConstantsError.errorNotFound);
				}
			} else {
				req.setAttribute(ConstantsJSP.ERROR,
						ConstantsError.errorIncorrectId);
			}
		}

		return ConstantsJSP.memberPage;
	}

	@RequestMapping(value = "/memberAdd.do", method = RequestMethod.POST)
	public String memberAdd(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam(value = "id", required = false) String project_id,
			@RequestParam(value = "employee_id[]", required = false) String[] employee_id,
			@RequestParam(value = "role[]", required = false) String[] role,
			@RequestParam(value = "check[]", required = false) String[] check) {
		String pageReturn = "forward:/"+ConstantsJSP.memberController;
		if (project_id == null || employee_id == null || role == null
				|| check == null) {
			req.setAttribute(ConstantsJSP.ERROR, "Any param is null.");
			return pageReturn;
		}
		StringBuilder error = new StringBuilder();
		int employeeId = 0;
		try {
			int projectId = Integer.parseInt(project_id);
			Project project = workService.getProjectById(projectId);
			req.setAttribute(ConstantsJSP.PROJECT, project);
			List<Employee> employeeList = workService
					.getEmployeeNoProjectMember(project.getId());
			req.setAttribute(ConstantsJSP.EMPLOYEE_LIST, employeeList);
			List<Role> roleList = workService.getRoleList();
			req.setAttribute(ConstantsJSP.ROLE_LIST, roleList);
			for (int i = 0; i < employee_id.length; i++) {
				if (check[i] != null) {
					if (check[i].equals(ConstantsJSP.CHECKED)) {
						if (employee_id[i] != null) {
							try {
								employeeId = Integer.parseInt(employee_id[i]);
								Employee employee = workService
										.getEmployee(employeeId);
								if (employee != null) {
									Member member = new Member();
									member.setProject(project);
									member.setEmployee(employee);
									Role memberRole = null;
									for (Role r : roleList) {
										if (r.getName().equals(role[i])) {
											memberRole = r;
											break;
										}
									}
									if (memberRole != null) {
										member.setRole(memberRole);
										workService.save(member);
									} else {
										error.append("RoleError(Role not exist!):employee_id="
												+ employeeId + ";");
									}
								}
							} catch (NumberFormatException e) {
								logger.info("error:" + e);
								error.append("ParseError:employee_id="
										+ employeeId + ";");
							}
						}
					}
				}
			}
		} catch (NumberFormatException e) {
			logger.info("error:" + e);
			error.append("ParseError:project_id=" + project_id+";");
		}
		req.setAttribute(ConstantsJSP.ERROR, error.toString());
		return pageReturn;
	}

	@RequestMapping(value = "/memberNew.do", method = RequestMethod.POST)
	public String memberNew(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "id", required = false) String project_id) {
		logger.info("=====[memberNew]=====");
		int projectId = 0;
		if (project_id != null) {
			try {
				projectId = Integer.parseInt(project_id);
				Project project = workService.getProjectById(projectId);
				req.setAttribute(ConstantsJSP.PROJECT, project);
				List<Employee> employeeList = workService
						.getEmployeeNoProjectMember(project.getId());
				logger.info("employee list=" + employeeList);
				req.setAttribute(ConstantsJSP.EMPLOYEE_LIST, employeeList);
				List<Role> roleList = workService.getRoleList();
				logger.info("Role list=" + roleList);
				req.setAttribute(ConstantsJSP.ROLE_LIST, roleList);
			} catch (NumberFormatException e) {
				req.setAttribute(ConstantsJSP.ERROR,
						ConstantsError.errorIncorrectId);
			}
		}
		return ConstantsJSP.memberNewPage;
	}
}