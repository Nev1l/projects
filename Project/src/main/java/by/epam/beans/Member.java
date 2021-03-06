package by.epam.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import by.epam.consts.ConstantsError;
import by.epam.dao.DaoException;

@Entity
@Table(name = "Member")
public class Member implements Serializable{
	private static final long serialVersionUID = 5981232014682996340L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	//@Column(name = "is_enabled",columnDefinition = "smallint DEFAULT 1", nullable = false)
	//private boolean isEnabled=true; 
	
	public int getId() {
		return id;
	}

	public void setId(int id)  {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) throws DaoException {
		if (project==null) {
			throw new DaoException(ConstantsError.projectIncorrect);
		}
		this.project = project;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) throws DaoException {
		if(employee==null){
			throw new DaoException(ConstantsError.employeeIncorrect);
		}
		this.employee = employee;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) throws DaoException {
		if(role==null){
			throw new DaoException(ConstantsError.roleIncorrect);
		}
		this.role = role;
	}

	public Member(Project project, Employee employee, Role role) {
		super();
		this.project = project;
		this.employee = employee;
		this.role = role;
	}

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", project=" + project + ", employee="
				+ employee + ", role=" + role + "]";
	}

}
