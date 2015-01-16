package by.epam.template;

import by.epam.beans.Member;
import by.epam.dao.DaoException;
import by.epam.dao.WorkServiceDAO;

public interface MemberTemplate {
	void execute(WorkServiceDAO workService, Member memeber);
	int getId(WorkServiceDAO workService,String param) throws DaoException;
}
