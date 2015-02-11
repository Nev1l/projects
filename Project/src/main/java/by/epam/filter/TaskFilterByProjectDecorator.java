package by.epam.filter;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import by.epam.dao.TaskFilterDAO;

public class TaskFilterByProjectDecorator extends TaskFilterDecorator{
	private String param;
	public TaskFilterByProjectDecorator(TaskFilterDAO filter,String param) {
		super(filter);
		this.param=param;
	}

	@Override
	public String doFilter(Session session) {
		// TODO Auto-generated method stub
		//Criteria crit = super.doFilter(session);
		//rit.add(Restrictions.eq("p.name", param));
		String sql = super.doFilter(session);
		sql += " and project.name='"+param+"'";
		return sql;
	}

}
