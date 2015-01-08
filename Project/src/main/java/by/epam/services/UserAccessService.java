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
import by.epam.beans.Role;
import by.epam.beans.RoleAccess;
import by.epam.controllers.LoginController;
import by.epam.dao.WorkDAO;
import by.epam.dao.WorkServiceDAO;

@Service("userAccessService")
@Transactional
public class UserAccessService implements UserDetailsService {
	private static final Logger logger = LoggerFactory
			.getLogger(UserAccessService.class);

	@Autowired
	private WorkDAO workDAO;

	@Transactional
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		Employee employee = null;
		Member member = null;
		employee = workDAO.getEmployeeByUserName(username);
		member = workDAO.getMemberByEmployeeId(employee.getId());
		//logger.info("employee="+employee.toString());
		//logger.info("role="+member.getRole().getName());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(member.getRole().getName()));
		return new User(employee.getLogin(), employee.getPassword(), true,
				true, true, true, authorities);
	}

}
