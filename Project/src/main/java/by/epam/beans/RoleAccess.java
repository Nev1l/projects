package by.epam.beans;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public enum RoleAccess implements Serializable{
	ADMIN("Administrator"),MANAGER("Manager"), LEAD("Lead"), DEVELOPER("Developer");

	String access;

	public String getAccess() {
		return access;
	}

	public void setRole(String access) {
		this.access = access;
	}

	private RoleAccess(String access) {
		this.access = access;
	}
}
