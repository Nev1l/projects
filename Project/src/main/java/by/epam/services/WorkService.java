package by.epam.services;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.beans.Activity;
import by.epam.beans.Assignment;
import by.epam.beans.Attachment;
import by.epam.beans.Employee;
import by.epam.beans.Member;
import by.epam.beans.Position;
import by.epam.beans.Project;
import by.epam.beans.Role;
import by.epam.beans.Status;
import by.epam.beans.Task;
import by.epam.controllers.HomeController;
import by.epam.workimplements.WorkDAO;

@Service
public class WorkService implements WorkDAO {
	private static final Logger logger = LoggerFactory
			.getLogger(WorkService.class);
	@Autowired
	private WorkDAO workDAO;

	@Transactional
	public void save(Activity object) {
		// TODO Auto-generated method stub
		workDAO.save(object);
	}

	@Transactional
	public void update(Activity object) {
		// TODO Auto-generated method stub
		workDAO.update(object);
	}

	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub
		workDAO.delete(id);
	}

	@Transactional
	public Activity getActivityById(int id) {
		// TODO Auto-generated method stub
		return workDAO.getActivityById(id);
	}

	@Transactional
	public void save(Assignment object) {
		// TODO Auto-generated method stub
		workDAO.save(object);
	}

	@Transactional
	public void update(Assignment object) {
		// TODO Auto-generated method stub
		workDAO.update(object);
	}

	@Transactional
	public Assignment getAssignmentById(int id) {
		// TODO Auto-generated method stub
		return workDAO.getAssignmentById(id);
	}

	@Transactional
	public void save(Attachment object) {
		// TODO Auto-generated method stub
		workDAO.save(object);
	}

	@Transactional
	public void update(Attachment object) {
		// TODO Auto-generated method stub
		workDAO.update(object);
	}

	@Transactional
	public Attachment getAttachmentById(int id) {
		// TODO Auto-generated method stub
		return workDAO.getAttachmentById(id);
	}

	@Transactional
	public void save(Employee object) {
		// TODO Auto-generated method stub
		workDAO.save(object);
	}

	@Transactional
	public void update(Employee object) {
		// TODO Auto-generated method stub
		workDAO.update(object);
	}

	@Transactional
	public Employee getEmployeeById(int id) {
		// TODO Auto-generated method stub
		return workDAO.getEmployeeById(id);
	}

	@Transactional
	public void save(Member object) {
		// TODO Auto-generated method stub
		workDAO.save(object);
	}

	@Transactional
	public void update(Member object) {
		// TODO Auto-generated method stub
		workDAO.update(object);
	}

	@Transactional
	public Member getMemberById(int id) {
		// TODO Auto-generated method stub
		return workDAO.getMemberById(id);
	}

	@Transactional
	public void save(Position object) {
		// TODO Auto-generated method stub
		workDAO.save(object);
	}

	@Transactional
	public void update(Position object) {
		// TODO Auto-generated method stub
		workDAO.update(object);
	}

	@Transactional
	public Position getPositionById(int id) {
		// TODO Auto-generated method stub
		return workDAO.getPositionById(id);
	}

	@Transactional
	public void save(Project object) {
		// TODO Auto-generated method stub
		workDAO.save(object);
	}

	@Transactional
	public void update(Project object) {
		// TODO Auto-generated method stub
		workDAO.update(object);
	}

	@Transactional
	public Project getProjectById(int id) {
		// TODO Auto-generated method stub
		return workDAO.getProjectById(id);
	}

	@Transactional
	public void save(Role object) {
		// TODO Auto-generated method stub
		workDAO.save(object);
	}

	@Transactional
	public void update(Role object) {
		// TODO Auto-generated method stub
		workDAO.update(object);
	}

	@Transactional
	public Role getRoleById(int id) {
		// TODO Auto-generated method stub
		return workDAO.getRoleById(id);
	}

	@Transactional
	public void save(Status object) {
		// TODO Auto-generated method stub
		workDAO.save(object);
	}

	@Transactional
	public void update(Status object) {
		// TODO Auto-generated method stub
		workDAO.update(object);
	}

	@Transactional
	public Status getStatusById(int id) {
		// TODO Auto-generated method stub
		return workDAO.getStatusById(id);
	}

	@Transactional
	public void save(Task object) {
		// TODO Auto-generated method stub
		workDAO.save(object);
	}

	@Transactional
	public void update(Task object) {
		// TODO Auto-generated method stub
		workDAO.update(object);
	}

	@Transactional
	public Task getTaskById(int id) {
		// TODO Auto-generated method stub
		return workDAO.getTaskById(id);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		// TODO Auto-generated method stub
		workDAO.setSessionFactory(sessionFactory);

	}

	public SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		return workDAO.getSessionFactory();
	}
}
