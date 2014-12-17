package by.epam.dao;

import by.epam.beans.Member;


public interface MemberDAO {
	void save(Member p);
	void update(Member p);
	void delete(int id);
	Member getMemberById(int id);
}
