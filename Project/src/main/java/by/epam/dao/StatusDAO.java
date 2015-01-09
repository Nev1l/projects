package by.epam.dao;

import java.util.List;

import by.epam.beans.Status;


public interface StatusDAO {
	void save(Status p);
	void update(Status p);
	void delete(int id);
	Status getStatusById(int id);
	List<Status> getStatusList();
}
