package by.epam.template;

import java.util.List;

import by.epam.beans.Employee;
import by.epam.beans.Member;
import by.epam.dao.WorkServiceDAO;

public interface MemberTemplate {
	void execute(WorkServiceDAO workService, Member memeber);
}
