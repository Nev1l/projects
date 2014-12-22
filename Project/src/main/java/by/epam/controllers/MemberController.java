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

import by.epam.beans.Assignment;
import by.epam.beans.Employee;
import by.epam.beans.Member;
import by.epam.beans.Project;
import by.epam.consts.ConstantsJSP;
import by.epam.consts.ConstantsLogger;
import by.epam.workimplements.WorkDAO;

@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory
			.getLogger(MemberController.class);
	public final static String pageLogger = ConstantsLogger.loggerPrefix
			+ ConstantsJSP.homePage + ConstantsLogger.loggerSuffix;
	@Autowired
	private WorkDAO workService;

	@RequestMapping(value = "/member.do")
	public String member(HttpServletRequest req, HttpServletResponse res) {
		logger.info(pageLogger);
		HttpSession session = req.getSession();
		if (session != null) {
			Member member = null;
			Employee employee = (Employee) session
					.getAttribute(ConstantsJSP.ATT_EMPLOYEE);
			if (employee != null) {
				if(employee.getPosition().isAdmin() || employee.getPosition().isLead() || employee.getPosition().isManager()){
					List<Member> memberList = workService.getAllMembers();
					req.setAttribute(ConstantsJSP.ALL_MEMBERS, memberList);
				}else if(employee.getPosition().isDeveloper()){
					member = workService.getMemberByEmployeeId(employee.getId());
					List<Project> projectList = workService.getProjectsByMemberId(member.getId());
					req.setAttribute(ConstantsJSP.MEMBER_PROJECTS, projectList);
				}
			}
		}
		return ConstantsJSP.memberPage;
	}
	
	@RequestMapping(value = "/memberAdd.do")
	public String addMember(HttpServletRequest req, HttpServletResponse res) {
		logger.info(pageLogger);
		return ConstantsJSP.memberPage;
	}
}