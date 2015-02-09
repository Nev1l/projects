package by.epam.utils;

import java.util.ArrayList;
import java.util.List;

import by.epam.beans.Member;
import by.epam.beans.Project;

public class ProjectByMemberAccess {
	public static List<Project> getProjectsWhereAccessMoreThanDeveloper(
			List<Member> memberProjectAccess) {
		List<Project> list = new ArrayList<Project>();
		for (Member member : memberProjectAccess) {
			if (!member.getRole().isDeveloper()) {
				list.add(member.getProject());
			}
		}
		return list;
	}
	
	public static boolean hasProjectsWhereAccessMoreThanDeveloper(List<Member> memberProjectAccess) {
		return getProjectsWhereAccessMoreThanDeveloper(memberProjectAccess).size()>0;
	}
}
