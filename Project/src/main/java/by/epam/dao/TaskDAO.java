package by.epam.dao;

import java.util.List;

import by.epam.beans.Task;


public interface TaskDAO {
	void save(Task p);
	void update(Task p);
	void delete(int id);
	Task getTaskById(int id);
	List<Task> getTasksByProjectId(int id);
}
