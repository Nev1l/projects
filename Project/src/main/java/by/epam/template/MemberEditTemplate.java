package by.epam.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.beans.Member;
import by.epam.consts.ConstantsError;
import by.epam.dao.DaoException;
import by.epam.dao.WorkServiceDAO;

public class MemberEditTemplate implements MemberTemplate {
	private static final Logger logger = LoggerFactory
			.getLogger(MemberEditTemplate.class);

	@Override
	public void execute(WorkServiceDAO workService, Member member) {
		logger.info("member=" + member);
		workService.update(member);
	}

	@Override
	public int getId(WorkServiceDAO workService, String param) throws DaoException {
		try {
			int memberId = Integer.parseInt(param);
			Member member = workService.getMemberById(memberId);
			if(member!=null){
				return member.getId();
			}
		} catch (NumberFormatException e) {
			logger.info("error:" + e);
		}
		throw new DaoException(ConstantsError.errorIncorrectId);
	}

}
