package by.epam.filter;

import org.hibernate.classic.Session;

import by.epam.dao.TaskFilterDAO;

public class TaskFilterDecorator implements TaskFilterDAO{
	protected TaskFilterDAO filter;
	
	public TaskFilterDecorator(TaskFilterDAO filter) {
		this.filter = filter;
	}
	
	@Override
	public String doFilter(Session session) {
		// TODO Auto-generated method stub
		return filter.doFilter(session);
	}
}
