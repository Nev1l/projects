package by.epam.dao;

import java.util.List;

import by.epam.beans.Task;


public interface TaskDAO {
	Task save(Task p);
	void update(Task p);
	void delete(int id);
	Task getTaskById(int id);
	List<Task> getTasksByProjectId(int id);
	List<Task> getTasks();
	List<Task> getTasks(int start,int count);
	
}
