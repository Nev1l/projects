package by.epam.dao;

import by.epam.beans.Task;


public interface TaskDAO {
	void save(Task p);
	void update(Task p);
	void delete(int id);
}
