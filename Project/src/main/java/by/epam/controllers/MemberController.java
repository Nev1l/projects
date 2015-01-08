package by.epam.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.epam.beans.Assignment;
import by.epam.beans.Member;
import by.epam.beans.Project;
import by.epam.beans.Task;
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
		logger.info(ConstantsJSP.EMPTY);
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
					List<Member> memberList = workService.getMembersByProjectId(project.getId());
					req.setAttribute(ConstantsJSP.PROJECT_MEMBERS, memberList);
					logger.info("project members list="+memberList);
				} else {
					logger.info("project is null");
					req.setAttribute(ConstantsJSP.ERROR,
							ConstantsError.errorNotFound);
				}
			}else{
				req.setAttribute(ConstantsJSP.ERROR,
						ConstantsError.errorIncorrectId);
			}
		}
		
		return ConstantsJSP.memberPage;
	}

	@RequestMapping(value = "/memberAdd.do")
	public String addMember(HttpServletRequest req, HttpServletResponse res) {
		logger.info("");
		return ConstantsJSP.memberPage;
	}
}