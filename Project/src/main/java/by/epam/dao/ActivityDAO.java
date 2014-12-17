package by.epam.dao;

import by.epam.beans.Activity;


public interface ActivityDAO {
	void save(Activity p);
	void update(Activity p);
	void delete(int id);
	Activity getActivityById(int id);
}
