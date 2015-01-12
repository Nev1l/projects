package by.epam.beans;

import java.io.Serializable;

public enum State implements Serializable{
	NOT_STARTED("No started"), STARTED("Started"), SUSPENDED("Suspended"), FINISHED(
			"Finished");

	String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	State(String state) {
		this.state = state;
	}

	public static State getEnum(String s) {
		if (NOT_STARTED.state.equals(s)) {
			return NOT_STARTED;
		} else if (STARTED.state.equals(s)) {
			return STARTED;
		} else if (SUSPENDED.state.equals(s)) {
			return SUSPENDED;
		} else if (FINISHED.state.equals(s)) {
			return FINISHED;
		}
		throw new IllegalArgumentException("No Enum specified for this string");
	}
}
