package by.epam.dao;

import java.util.List;

import by.epam.beans.Role;


public interface RoleDAO {
	void save(Role p);
	void update(Role p);
	void delete(int id);
	Role getRoleById(int id);
	List<Role> getRoleList();
}
