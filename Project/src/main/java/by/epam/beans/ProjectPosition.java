package by.epam.beans;

public enum ProjectPosition {
	ADMIN("Administrator"), MANAGER("Manager"), LEAD("Lead"), JUNIOR(
			"Junior");
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
