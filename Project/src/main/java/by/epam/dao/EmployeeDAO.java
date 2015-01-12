package by.epam.dao;

import java.util.List;

import by.epam.beans.Employee;
import by.epam.beans.User;


public interface EmployeeDAO {
	void save(Employee p);
	void update(Employee p);
	void delete(int id);
	Employee getEmployee(User user);
	Employee getEmployee(int id);
	Employee getEmployeeByUserName(String userName);
	List<Employee> getEmployeeNoProjectMember(int projectId);
}
