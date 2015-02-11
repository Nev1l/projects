package by.epam.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import by.epam.dao.WorkServiceDAO;

//================================================
//=========[Добавь к новому методу @Transactional]
//================================================

@Service
@Transactional
public class WorkService implements WorkServiceDAO {
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
	public Employee getEmployee(User user) {
		// TODO Auto-generated method stub
		return workDAO.getEmployee(user);
	}

	@Transactional
	public Member save(Member object) {
		return workDAO.save(object);
	}

	@Transactional
	public void update(Member object) {
		// TODO Auto-generated method stub
		workDAO.update(object);
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

	/*
	 * @Transactional public Position getPositionByEmployeeId(int id){ return
	 * workDAO.getPositionByEmployeeId(id); }
	 */
	@Transactional
	public Project save(Project object) {
		return workDAO.save(object);
	}

	@Transactional
	public void update(Project object) {
		// TODO Auto-generated method stub
		workDAO.update(object);
	}

	@Transactional
	public List<Project> getProjectsByMemberId(int id) {
		// TODO Auto-generated method stub
		return workDAO.getProjectsByMemberId(id);
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
	public Task save(Task object) {
		return workDAO.save(object);
	}

	@Transactional
	public void update(Task object) {
		// TODO Auto-generated method stub
		workDAO.update(object);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		// TODO Auto-generated method stub
		workDAO.setSessionFactory(sessionFactory);

	}

	public SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		return workDAO.getSessionFactory();
	}

	@Transactional
	public List<Assignment> getEmployeeAssignments(int employeeId, int start,
			int count) {
		// TODO Auto-generated method stub
		return workDAO.getEmployeeAssignments(employeeId, start, count);
	}

	@Transactional
	public List<Member> getAllMembers() {
		// TODO Auto-generated method stub
		return workDAO.getAllMembers();
	}

	@Transactional
	public Employee getEmployeeByUserName(String userName) {
		// TODO Auto-generated method stub
		return workDAO.getEmployeeByUserName(userName);
	}

	@Transactional
	public List<Activity> getLastActivity(int count) {
		// TODO Auto-generated method stub
		return workDAO.getLastActivity(count);
	}

	@Transactional
	public int getCountAssignmentsByEmployeeId(int employeeId) {
		// TODO Auto-generated method stub
		return workDAO.getCountAssignmentsByEmployeeId(employeeId);
	}

	@Transactional
	public int getActivityCount() {
		// TODO Auto-generated method stub
		return workDAO.getActivityCount();
	}

	@Transactional
	public Task getTaskById(int id) {
		// TODO Auto-generated method stub
		return workDAO.getTaskById(id);
	}

	@Transactional
	public List<Task> getTasksByProjectId(int id) {
		// TODO Auto-generated method stub
		return workDAO.getTasksByProjectId(id);
	}

	@Transactional
	public List<Project> getAllProjects() {
		// TODO Auto-generated method stub
		return workDAO.getAllProjects();
	}

	@Transactional
	public Project getProjectById(int id) {
		// TODO Auto-generated method stub
		return workDAO.getProjectById(id);
	}

	@Transactional
	public List<Member> getMembersByProjectId(int id) {
		// TODO Auto-generated method stub
		return workDAO.getMembersByProjectId(id);
	}

	@Transactional
	public List<Status> getStatusList() {
		// TODO Auto-generated method stub
		return workDAO.getStatusList();
	}

	@Transactional
	public List<Member> getMembersByEmployeeId(int id) {
		// TODO Auto-generated method stub
		return workDAO.getMembersByEmployeeId(id);
	}

	@Transactional
	public Member getProjectMember(int projectId, int employeeId) {
		// TODO Auto-generated method stub
		return workDAO.getProjectMember(projectId, employeeId);
	}

	@Transactional
	public List<Employee> getEmployeeNoProjectMember(int projectId) {
		// TODO Auto-generated method stub
		return workDAO.getEmployeeNoProjectMember(projectId);
	}

	@Transactional
	public List<Role> getRoleList() {
		// TODO Auto-generated method stub
		return workDAO.getRoleList();
	}

	@Transactional
	public Employee getEmployee(int id) {
		// TODO Auto-generated method stub
		return workDAO.getEmployee(id);
	}

	@Transactional
	public boolean delete(Member member) {
		// TODO Auto-generated method stub
		if (workDAO.hasActivity(member.getId())
				|| workDAO.hasAssignment(member.getId())) {
			return false;
		}
		return workDAO.delete(member);
	}

	@Transactional
	public Role getRoleName(ProjectPosition role) {
		// TODO Auto-generated method stub
		return workDAO.getRoleName(role);
	}

	@Transactional
	public Member getMemberById(int id) {
		// TODO Auto-generated method stub
		return workDAO.getMemberById(id);
	}

	@Transactional
	public Assignment getLastAssigneeByTaskId(int id) {
		// TODO Auto-generated method stub
		return workDAO.getLastAssigneeByTaskId(id);
	}

	@Transactional
	public boolean hasActivity(int member_id) {
		// TODO Auto-generated method stub
		return workDAO.hasActivity(member_id);
	}

	@Transactional
	public boolean hasAssignment(int member_id) {
		// TODO Auto-generated method stub
		return workDAO.hasAssignment(member_id);
	}

	@Transactional
	public List<Task> getTasks() {
		// TODO Auto-generated method stub
		return workDAO.getTasks();
	}

	@Transactional
	public List<Task> getTasks(int start, int count) {
		// TODO Auto-generated method stub
		return workDAO.getTasks(start, count);
	}

	@Transactional
	public List<Assignment> getAssignment(TaskFilterDAO filter,int start, int count){
		// TODO Auto-generated method stub
		return workDAO.getAssignment(filter, start, count);
	}

	@Transactional
	public int getCountAssignment(TaskFilterDAO filter) {
		// TODO Auto-generated method stub
		return workDAO.getCountAssignment(filter);
	}

}
