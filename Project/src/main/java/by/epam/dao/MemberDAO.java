package by.epam.dao;

import java.util.List;

import by.epam.beans.Member;


public interface MemberDAO {
	void save(Member p);
	void update(Member p);
	void delete(int id);
	Member getMemberByEmployeeId(int id);
	List<Member> getAllMembers();
	List<Member> getMembersByProjectId(int id);
}
