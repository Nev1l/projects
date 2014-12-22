package by.epam.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name",length=50)
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(RoleAccess role) {
		this.name = role.getAccess();
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(RoleAccess role) {
		super();
		this.name = role.getAccess();
	}

	public boolean isAdmin() {
		return name.equals(RoleAccess.ADMIN.getAccess());
	}
	
	public boolean isManager() {
		return name.equals(RoleAccess.MANAGER.getAccess());
	}
	
	public boolean isLead() {
		return name.equals(RoleAccess.LEAD.getAccess());
	}
	public boolean isDeveloper() {
		return name.equals(RoleAccess.DEVELOPER.getAccess());
	}
}
