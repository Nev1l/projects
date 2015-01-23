package by.epam.dao;

import java.util.List;

import by.epam.beans.Member;


public interface MemberDAO {
	boolean hasActivity(int member_id);
	boolean hasAssignment(int member_id);
	Member save(Member p);
	void update(Member p);
	boolean delete(Member member);
	Member getMemberById(int id);
	Member getProjectMember(int projectId,int employeeId);
	List<Member> getMembersByEmployeeId(int id);//for get roles of all projects of the employee
	List<Member> getAllMembers();//all members of all projects
	List<Member> getMembersByProjectId(int id);
}
