package by.epam.beans;

public enum ProjectPosition {
	ADMIN("Administrator"), MANAGER("Resource manager"), LEAD("Team lead"), JUNIOR(
			"Junior"), TESTER("Tester");
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
