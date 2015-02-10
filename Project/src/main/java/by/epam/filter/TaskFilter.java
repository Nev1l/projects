package by.epam.filter;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import by.epam.beans.Assignment;
import by.epam.dao.TaskFilterDAO;


public class TaskFilter implements TaskFilterDAO {

	@Override
	public Criteria doFilter(Session session) {
		Criteria crit = session.createCriteria(
				Assignment.class, "a");
		crit.addOrder(Order.desc("assign_date"));
		crit.createAlias("member", "m");
		crit.createAlias("task", "t");
		crit.createAlias("t.project", "p");
		crit.createAlias("t.status", "s");
		crit.createAlias("m.employee", "e");
		crit.setProjection(Projections.projectionList().add(
				Projections.distinct(Projections.property("t.id"))));
		return crit;
	}

}
