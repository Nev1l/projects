package by.epam.template;

import javax.servlet.http.HttpServletRequest;

import by.epam.consts.ConstantsJSP;
import by.epam.dao.WorkServiceDAO;

public class MemberAddViewTemplate implements MemberViewTemplate {

	@Override
	public void execute(HttpServletRequest req,WorkServiceDAO workService, int project_id) {
		req.setAttribute(ConstantsJSP.EMPLOYEE_LIST, workService
				.getEmployeeNoProjectMember(project_id));
	}
}
