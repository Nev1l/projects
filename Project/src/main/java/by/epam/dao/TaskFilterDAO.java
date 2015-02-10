package by.epam.dao;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;

public interface TaskFilterDAO {
	Criteria doFilter(Session session);
}
