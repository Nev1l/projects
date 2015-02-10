package by.epam.filter;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;

import by.epam.dao.TaskFilterDAO;

public class TaskFilterDecorator implements TaskFilterDAO{
	protected TaskFilterDAO filter;
	
	public TaskFilterDecorator(TaskFilterDAO filter) {
		this.filter = filter;
	}
	
	@Override
	public Criteria doFilter(Session session) {
		// TODO Auto-generated method stub
		return filter.doFilter(session);
	}
}
