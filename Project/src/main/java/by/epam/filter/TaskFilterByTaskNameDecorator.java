package by.epam.filter;

import org.hibernate.classic.Session;

import by.epam.dao.TaskFilterDAO;

public class TaskFilterByTaskNameDecorator extends TaskFilterDecorator{
	private String param;
	
	public TaskFilterByTaskNameDecorator(TaskFilterDAO filter,String param) {
		super(filter);
		this.param=param;
	}

	@Override
	public String doFilter(Session session) {
		// TODO Auto-generated method stub
		//Criteria crit = super.doFilter(session);
		//crit.add(Restrictions.like("t.description", "%"+param+"%"));
		String sql = super.doFilter(session);
		sql += " and task.description like '%"+param+"%'";
		return sql;
	}

}
