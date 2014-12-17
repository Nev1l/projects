package by.epam.dao;

import by.epam.beans.Project;


public interface ProjectDAO {
	void save(Project p);
	void update(Project p);
	void delete(int id);
	Project getProjectById(int id);
}
