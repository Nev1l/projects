package by.epam.template;

import javax.servlet.http.HttpServletRequest;

import by.epam.dao.WorkServiceDAO;

public interface MemberViewTemplate {
	void execute(HttpServletRequest req,WorkServiceDAO workService, int project_id);
}
