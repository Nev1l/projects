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
		this.name = role.name();
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(RoleAccess role) {
		super();
		this.name = role.name();
	}

	public boolean isAdmin() {
		return name.equals(RoleAccess.ADMIN.name());
	}
	
	public boolean isManager() {
		return name.equals(RoleAccess.MANAGER.name());
	}
	
	public boolean isLead() {
		return name.equals(RoleAccess.LEAD.name());
	}
	public boolean isDeveloper() {
		return name.equals(RoleAccess.DEVELOPER.name());
	}
}
