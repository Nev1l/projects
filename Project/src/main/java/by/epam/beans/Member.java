package by.epam.beans;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "projectId")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "roleId")
	private Role role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Member(Long id, Project project, Employee employee, Role role) {
		super();
		this.id = id;
		this.project = project;
		this.employee = employee;
		this.role = role;
	}

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

}
