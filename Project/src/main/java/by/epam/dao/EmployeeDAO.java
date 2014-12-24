package by.epam.dao;

import by.epam.beans.Employee;
import by.epam.beans.User;


public interface EmployeeDAO {
	void save(Employee p);
	void update(Employee p);
	void delete(int id);
	Employee getEmployee(User user);
	Employee getEmployeeByUserName(String userName);
}
