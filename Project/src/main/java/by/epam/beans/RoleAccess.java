package by.epam.beans;

import javax.persistence.Embeddable;

@Embeddable
public enum RoleAccess {
	ADMIN("Administrator"), MANAGER("Resource manager"), LEAD("Team lead"), DEVELOPER("Developer"), TESTER("Tester");

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
