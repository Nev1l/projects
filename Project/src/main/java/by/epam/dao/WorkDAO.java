package by.epam.dao;

import org.hibernate.SessionFactory;

public interface WorkDAO extends ActivityDAO, AssigmentDAO, AttachmentDAO,
		EmployeeDAO, MemberDAO, PositionDAO, ProjectDAO, RoleDAO, StatusDAO,
		TaskDAO {

	public void setSessionFactory(SessionFactory sessionFactory);

	public SessionFactory getSessionFactory();
	
}
