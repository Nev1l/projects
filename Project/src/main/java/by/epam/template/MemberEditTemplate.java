package by.epam.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.beans.Member;
import by.epam.dao.WorkServiceDAO;

public class MemberEditTemplate implements MemberTemplate {
	private static final Logger logger = LoggerFactory
			.getLogger(MemberEditTemplate.class);

	@Override
	public void execute(WorkServiceDAO workService, Member member) {
		logger.info("member=" + member);
		workService.update(member);
	}

}
