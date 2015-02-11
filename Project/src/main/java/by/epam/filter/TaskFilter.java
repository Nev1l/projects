package by.epam.filter;

import org.hibernate.classic.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.dao.TaskFilterDAO;

public class TaskFilter implements TaskFilterDAO {
	private static final Logger logger = LoggerFactory
			.getLogger(TaskFilter.class);

	@Override
	public String doFilter(Session session) {
/*		Criteria crit = session.createCriteria(Assignment.class, "a");
		crit.addOrder(Property.forName("a.assign_date").desc());
		crit.createAlias("member", "m");
		crit.createAlias("task", "t");
		crit.createAlias("t.project", "p");
		crit.createAlias("t.status", "s");
		crit.createAlias("m.employee", "e");
		// crit.addOrder(Order.desc("id"));*/
		String sql = "select * from assignment,task,project,status where assignment.task_id=task.id and task.project_id=project.id and task.status_id=status.id and assignment.assign_date  in (SELECT MAX(assign_date) FROM assignment GROUP BY assignment.task_id )";
		return sql;
	}
}
