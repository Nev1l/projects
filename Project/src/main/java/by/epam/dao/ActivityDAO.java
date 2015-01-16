package by.epam.dao;

import java.util.List;

import by.epam.beans.Activity;


public interface ActivityDAO {
	void save(Activity p);
	void update(Activity p);
	void delete(int id);
	Activity getActivityById(int id);
	List<Activity> getLastActivity(int count);
	int getActivityCount();
	//јктивити будет и ∆урнал и јвтојктивити(автоматически генерируемое активити)
	
	//Activity getLastActivityByProjectId(int projectId);
}
