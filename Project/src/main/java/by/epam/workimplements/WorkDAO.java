package by.epam.workimplements;

import org.hibernate.SessionFactory;

import by.epam.dao.ActivityDAO;
import by.epam.dao.AssigmentDAO;
import by.epam.dao.AttachmentDAO;
import by.epam.dao.EmployeeDAO;
import by.epam.dao.MemberDAO;
import by.epam.dao.PositionDAO;
import by.epam.dao.ProjectDAO;
import by.epam.dao.RoleDAO;
import by.epam.dao.StatusDAO;
import by.epam.dao.TaskDAO;

public interface WorkDAO extends ActivityDAO, AssigmentDAO, AttachmentDAO,
		EmployeeDAO, MemberDAO, PositionDAO, ProjectDAO, RoleDAO, StatusDAO,
		TaskDAO {

	public void setSessionFactory(SessionFactory sessionFactory);

	public SessionFactory getSessionFactory();

}
