package by.epam.dao;

import java.util.List;

import by.epam.beans.Assignment;


public interface AssigmentDAO {
	void save(Assignment p);
	void update(Assignment p);
	void delete(int id);
	List<Assignment> getEmployeeAssignments(int employeeId,int start,int count);
	
	int getCountAssignmentsByEmployeeId(int employeeId);//Работает не правильно переписать
	Assignment getLastAssigneeByTaskId(int id);
	//List<Assignment> getLastActivity(); Наверно эт не нужно делать здесь
	
	
	
	
	
	
	//List<Assignment> getAssignments(int employee_id,int task_id, String date);
}
