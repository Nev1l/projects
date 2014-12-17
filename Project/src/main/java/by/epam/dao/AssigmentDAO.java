package by.epam.dao;

import by.epam.beans.Assignment;


public interface AssigmentDAO {
	void save(Assignment p);
	void update(Assignment p);
	void delete(int id);
	Assignment getAssignmentById(int id);
}
