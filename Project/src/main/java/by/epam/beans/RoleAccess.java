package by.epam.beans;

import javax.persistence.Embeddable;

@Embeddable
public enum RoleAccess {
	ADMIN("Administrator"), MANAGER("Manager"), LEAD("Lead"), DEVELOPER("Developer");

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
