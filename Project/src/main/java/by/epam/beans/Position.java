package by.epam.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Position")
public class Position implements Serializable{
	private static final long serialVersionUID = -8532007959104362187L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name", length = 50)
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

	public Position() {
		super();
		// TODO Auto-generated constructor stub
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
