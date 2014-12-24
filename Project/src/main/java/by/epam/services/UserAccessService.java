package by.epam.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.beans.Employee;
import by.epam.beans.Member;
import by.epam.controllers.LoginController;
import by.epam.dao.WorkDAO;

@Service("userAccessService")
public class UserAccessService implements UserDetailsService {
	private static final Logger logger = LoggerFactory
			.getLogger(UserAccessService.class);

	@Autowired
	private WorkDAO workDAO;
	
	@Transactional
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		logger.info("UserAccessService->loadUserByUsername");
		Employee employee = null;
		Member member = null;
		employee = workDAO.getEmployeeByUserName(username);
		member = workDAO.getMemberByEmployeeId(employee.getId());
		logger.info(employee.toString());
		logger.info(member.toString());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(member.getRole().getName()));
		return new User(employee.getLogin(), employee.getPassword(), true,
				true, true, true, authorities);
		/*
		 * Session sess = workDAO.getSessionFactory().getCurrentSession();
		 * Transaction tx = null;
		 * 
		 * try { tx = sess.beginTransaction();
		 * tx.commit();
		 * } catch (Exception e) { if (tx != null) tx.rollback(); throw new
		 * UsernameNotFoundException("DataBase error"); }
		 */
	}

}
