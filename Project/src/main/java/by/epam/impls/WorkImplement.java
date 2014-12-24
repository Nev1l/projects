package by.epam.impls;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
import by.epam.beans.User;
import by.epam.dao.WorkDAO;

@Repository
public class WorkImplement implements WorkDAO {

	public WorkImplement() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		// TODO Auto-generated method stub
		this.sessionFactory = sessionFactory;
	}

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public void save(Activity object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(object);
	}

	@Override
	public void update(Activity object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(object);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Activity getActivityById(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from activity";
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Activity.class);
		return (Activity) sqlquery.uniqueResult();

	}

	@Override
	public void save(Assignment object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(object);
	}

	@Override
	public void update(Assignment object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(object);
	}

	@Override
	public void save(Attachment object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(object);
	}

	@Override
	public void update(Attachment object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(object);
	}

	@Override
	public Attachment getAttachmentById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Employee object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(object);
	}

	@Override
	public void update(Employee object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(object);
	}

	@Override
	public Employee getEmployeeByUserName(String userName) {
		// TODO Auto-generated method stub
		Session session = getSessionFactory().getCurrentSession();
		Criteria cr = session.createCriteria(Employee.class)
				.add(Restrictions.eq("login", userName));
		return (Employee) cr.uniqueResult();
	}

	@Override
	public Employee getEmployee(User user) {
		// TODO Auto-generated method stub
		Session session = getSessionFactory().getCurrentSession();
		Criteria cr = session.createCriteria(Employee.class)
				.add(Restrictions.eq("login", user.getLogin()))
				.add(Restrictions.eq("password", user.getPassword()));
		return (Employee) cr.uniqueResult();
	}

	@Override
	public void save(Member object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(object);
	}

	@Override
	public void update(Member object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(object);
	}

	@Override
	public Member getMemberByEmployeeId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Position object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(object);
	}

	@Override
	public void update(Position object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(object);
	}

	@Override
	public Position getPositionById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Project object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(object);
	}

	@Override
	public void update(Project object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(object);
	}

	@Override
	public List<Project> getProjectsByMemberId(int id) {
		String sql = "select * from member,project where member.project_id=project.id and member.id="
				+ id;
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Assignment.class);
		return (List<Project>) sqlquery.list();
	}

	@Override
	public void save(Role object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(object);
	}

	@Override
	public void update(Role object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(object);
	}

	@Override
	public Role getRoleById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Status object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(object);
	}

	@Override
	public void update(Status object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(object);
	}

	@Override
	public Status getStatusById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Task object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(object);
	}

	@Override
	public void update(Task object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(object);
	}

	@Override
	public List<Assignment> getEmployeeAssignments(int employeeId) {
		// TODO Auto-generated method stub
		String sql = "select * from assignment,member where assignment.member_id=member.id and member.employee_id="
				+ employeeId;
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Assignment.class);
		return (List<Assignment>) sqlquery.list();
	}

	@Override
	public List<Member> getAllMembers() {
		// TODO Auto-generated method stub
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from member").addEntity(Member.class);
		return (List<Member>) sqlquery.list();

	}



}
