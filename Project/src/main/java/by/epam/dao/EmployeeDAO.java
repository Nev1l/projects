package by.epam.dao;

import by.epam.beans.Employee;


public interface EmployeeDAO {
	void save(Employee p);
	void update(Employee p);
	void delete(int id);
	Employee getEmployeeById(int id);
}
