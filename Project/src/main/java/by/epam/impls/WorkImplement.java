package by.epam.impls;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.epam.beans.Activity;
import by.epam.beans.Assignment;
import by.epam.beans.Attachment;
import by.epam.beans.Employee;
import by.epam.beans.Member;
import by.epam.beans.Position;
import by.epam.beans.Project;
import by.epam.beans.ProjectPosition;
import by.epam.beans.Role;
import by.epam.beans.Status;
import by.epam.beans.Task;
import by.epam.beans.User;
import by.epam.dao.TaskFilterDAO;
import by.epam.dao.WorkDAO;

@Repository
public class WorkImplement implements WorkDAO {
	private static final Logger logger = LoggerFactory
			.getLogger(WorkImplement.class);

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
	public boolean delete(Member member) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(member);
		return true;
	}

	@Override
	public Activity getActivityById(int id) {
		// TODO Auto-generated method stub
		return (Activity) sessionFactory.getCurrentSession()
				.createCriteria(Activity.class).add(Restrictions.eq("id", id))
				.uniqueResult();
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
		SQLQuery sqlquery = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"select * from employee where login='" + userName + "'")
				.addEntity(Employee.class);
		return (Employee) sqlquery.uniqueResult();
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
	public Member save(Member object) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(object);
		return (Member) session.get(Member.class, object.getId());
	}

	@Override
	public void update(Member object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(object);
	}

	@Override
	public List<Member> getMembersByEmployeeId(int id) {
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from member where employee_id=" + id)
				.addEntity(Member.class);
		return sqlquery.list();
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

	/*
	 * @Override public Position getPositionByEmployeeId(int id) { String sql =
	 * "select * from employee,role where employee.position_id=position.id where employee.id="
	 * + id; SQLQuery sqlquery = sessionFactory.getCurrentSession()
	 * .createSQLQuery(sql).addEntity(Position.class); return (Position)
	 * sqlquery.uniqueResult(); }
	 */

	@Override
	public Project save(Project object) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(object);
		return (Project) session.get(Project.class, object.getId());
	}

	@Override
	public void update(Project object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(object);
	}

	@Override
	public List<Project> getProjectsByMemberId(int id) {
		String sql = "select * from project,member where project.id=member.project_id and member.id="
				+ id;
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Project.class);
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
	public Task save(Task object) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(object);
		return (Task) session.get(Task.class, object.getId());
	}

	@Override
	public void update(Task object) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Assignment> getEmployeeAssignments(int employeeId, int start,
			int count) {
		// TODO Auto-generated method stub
		String sql = "select * from assignment where assignment.member_id in (select member_id from assignment,member,employee where assignment.member_id=member.id and member.employee_id="
				+ +employeeId + ")";
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Assignment.class);
		sqlquery.setFirstResult(start);
		sqlquery.setMaxResults(count);
		return (List<Assignment>) sqlquery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getAllMembers() {
		// TODO Auto-generated method stub
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from member").addEntity(Member.class);
		return (List<Member>) sqlquery.list();

	}

	@Override
	public List<Activity> getLastActivity(int count) {
		// TODO Auto-generated method stub
		String sql = "select * from activity order by activity_date desc";
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Activity.class);
		return sqlquery.setMaxResults(count).list();
	}

	@Override
	public int getCountAssignmentsByEmployeeId(int employeeId) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from assignment,member where assignment.member_id=member.id and member.employee_id="
				+ employeeId;
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		int count = (Integer) query.uniqueResult();
		return count;
	}

	@Override
	public int getActivityCount() {
		// TODO Auto-generated method stub
		String sql = "select count(*) from activity";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return (Integer) query.uniqueResult();
	}

	@Override
	public Task getTaskById(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from task where id=" + id;
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Task.class);
		return (Task) sqlquery.uniqueResult();
	}

	@Override
	public List<Task> getTasksByProjectId(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from task where project_id=" + id;
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Task.class);
		return sqlquery.list();
	}

	@Override
	public List<Project> getAllProjects() {
		// TODO Auto-generated method stub
		String sql = "select * from project";
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Project.class);
		return (List<Project>) sqlquery.list();
	}

	@Override
	public Project getProjectById(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from project where id=" + id;
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Project.class);
		return (Project) sqlquery.uniqueResult();
	}

	@Override
	public List<Member> getMembersByProjectId(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from member where project_id=" + id;
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Member.class);
		return sqlquery.list();
	}

	@Override
	public List<Status> getStatusList() {
		// TODO Auto-generated method stub
		String sql = "select * from status";
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Status.class);
		return sqlquery.list();
	}

	@Override
	public Member getProjectMember(int projectId, int employeeId) {
		// TODO Auto-generated method stub
		String sql = "select * from member where project_id=" + projectId
				+ " and employee_id=" + employeeId;
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Member.class);
		return (Member) sqlquery.uniqueResult();
	}

	@Override
	public List<Employee> getEmployeeNoProjectMember(int projectId) {
		// TODO Auto-generated method stub
		String sql = "select * from employee where employee.id not in (select member.employee_id from member where member.project_id="
				+ projectId + ")";
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Employee.class);
		return sqlquery.list();
	}

	@Override
	public List<Role> getRoleList() {
		// TODO Auto-generated method stub
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from role").addEntity(Role.class);
		return sqlquery.list();
	}

	@Override
	public Employee getEmployee(int id) {
		// TODO Auto-generated method stub
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from employee where id=" + id)
				.addEntity(Employee.class);
		return (Employee) sqlquery.uniqueResult();
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Role getRoleName(ProjectPosition role) {
		// TODO Auto-generated method stub
		SQLQuery sqlquery = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"select * from role where name='" + role.getPosition()
								+ "'").addEntity(Role.class);
		return (Role) sqlquery.uniqueResult();
	}

	@Override
	public Member getMemberById(int id) {
		// TODO Auto-generated method stub
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from member where id=" + id)
				.addEntity(Member.class);
		return (Member) sqlquery.uniqueResult();
	}

	@Override
	public Assignment getLastAssigneeByTaskId(int id) {
		// TODO Auto-generated method stub
		SQLQuery sqlquery = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"select * from assignment where task_id=" + id
								+ " order by assign_date desc")
				.addEntity(Assignment.class);
		@SuppressWarnings("unchecked")
		List<Assignment> list = sqlquery.list();
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public boolean hasActivity(int member_id) {
		// TODO Auto-generated method stub
		SQLQuery sqlquery = sessionFactory.getCurrentSession().createSQLQuery(
				"select count(*) from activity where member_id=" + member_id);
		return (Integer) sqlquery.uniqueResult() > 0 ? true : false;
	}

	@Override
	public boolean hasAssignment(int member_id) {
		// TODO Auto-generated method stub
		SQLQuery sqlquery = sessionFactory.getCurrentSession().createSQLQuery(
				"select count(*) from assignment where member_id=" + member_id);
		logger.info("res=" + sqlquery.uniqueResult());
		return (Integer) sqlquery.uniqueResult() > 0 ? true : false;
	}

	public WorkImplement(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getTasks() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession()
				.createSQLQuery("select * from task").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getTasks(int start, int count) {
		SQLQuery sqlquery = sessionFactory.getCurrentSession().createSQLQuery(
				"select * from task");
		sqlquery.setFirstResult(start);
		sqlquery.setMaxResults(count);
		return sqlquery.list();
	}

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="address_id",
	 * referencedColumnName="addrUniqueFieldName") private Address address; and
	 * then you can create criteria:
	 * criteria.add(restriction.eq("address.addrUniqueFieldName", 123);
	 * //assignment.member_id=member.id //assignment.task_id=task.id
	 * //task.project_id=project.id //member.employee_id=employee.id
	 * //------//crit.createCriteria("students", "s");---//createAlias
	 * //crit.add(Restrictions.eq("a.member_id","m.id"));
	 * //crit.add(Restrictions.eq("a.task_id","t.id"));
	 * //crit.add(Restrictions.eq("t.project_id","p.id"));
	 * //crit.add(Restrictions.eq("m.employee_id","e.id"));
	 * //.add(Projections.max("assign_date"))
	 * //crit.createAlias("assignment.assign_date", "assign_date");
	 */
	/*
	 * Criteria crit = sessionFactory.getCurrentSession().createCriteria(
	 * Assignment.class,"a"); crit.addOrder(Order.desc("assign_date"));
	 * crit.createAlias("member", "m"); crit.createAlias("task", "t");
	 * crit.createAlias("t.project", "p"); crit.createAlias("t.status", "s");
	 * crit.createAlias("m.employee", "e");
	 * crit.setProjection(Projections.projectionList().add(
	 * Projections.distinct(
	 * Projections.property("t.id"))));//add(Projections.max("a.assign_date"))
	 * crit.add(Restrictions.eq("p.name", "Airport1"));
	 * crit.add(Restrictions.eq("s.name", "No started"));
	 * crit.add(Restrictions.like("t.description", "%dat%"));
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Assignment> getAssignment(TaskFilterDAO filter, int start,
			int count) {
		SQLQuery sqlquery = sessionFactory.getCurrentSession().createSQLQuery(
				filter.doFilter(sessionFactory.getCurrentSession())).addEntity(Assignment.class);
		sqlquery.setFirstResult(start);
		sqlquery.setMaxResults(count);
		List<Assignment> list = sqlquery.list();
		return list;
	}

	@Override
	public int getCountAssignment(TaskFilterDAO filter) {
		// TODO Auto-generated method stub
		//crit = filter.doFilter(sessionFactory.getCurrentSession());
		String sql = filter.doFilter(sessionFactory.getCurrentSession());
		logger.info("sql:"+sql);
		SQLQuery sqlquery = sessionFactory.getCurrentSession().createSQLQuery(
				sql).addEntity(Assignment.class);
		return sqlquery.list().size();
	}
}
