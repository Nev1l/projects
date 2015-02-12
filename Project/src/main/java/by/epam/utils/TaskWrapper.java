package by.epam.utils;

import by.epam.beans.Task;

public class TaskWrapper {
	private Task task;
	private boolean hasError = false;
	private String message;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TaskWrapper(Task task, boolean hasError, String message) {
		super();
		this.task = task;
		this.hasError = hasError;
		this.message = message;
	}

}
