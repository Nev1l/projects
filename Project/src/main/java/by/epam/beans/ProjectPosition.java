package by.epam.beans;

import java.io.Serializable;

public enum ProjectPosition implements Serializable{
	ADMIN("Administrator"),MANAGER("Manager"), LEAD("Lead"), DEVELOPER("Developer");
	String position;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	private ProjectPosition(String position) {
		this.position = position;
	}

}
