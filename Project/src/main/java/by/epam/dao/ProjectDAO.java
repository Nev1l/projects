package by.epam.dao;

import java.util.List;

import by.epam.beans.Project;


public interface ProjectDAO {
	Project save(Project p);
	void update(Project p);
	void delete(int id);
	List<Project> getProjectsByMemberId(int id);
	List<Project> getAllProjects();
	Project getProjectById(int id);
}
