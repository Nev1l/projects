package by.epam.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.beans.Member;
import by.epam.dao.DaoException;
import by.epam.dao.WorkServiceDAO;

public class MemberAddTemplate implements MemberTemplate{
	private static final Logger logger = LoggerFactory
			.getLogger(MemberAddTemplate.class);
	@Override
	public void execute(WorkServiceDAO workService,Member member) {
		logger.info("member="+member);
		workService.save(member);
	}
	@Override
	public int getId(WorkServiceDAO workService,String param)  throws DaoException {
		//ignore all params( id is not important for operation create new)
		return 0;
	}
}
