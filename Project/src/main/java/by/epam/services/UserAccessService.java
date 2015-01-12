package by.epam.services;

import java.util.ArrayList;
import java.util.List;

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
import by.epam.beans.Position;
import by.epam.dao.WorkDAO;

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
		Employee employee = workDAO.getEmployeeByUserName(username);
		//Position position = workDAO.getPositionByEmployeeId(employee.getId());
		//Member member = null;
		//member = workDAO.getMemberByEmployeeId(employee.getId());
		//logger.info("employee="+employee.toString());
		logger.info("role="+employee.getPosition().getName());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(employee.getPosition().getName()));
		return new User(employee.getLogin(), employee.getPassword(), true,
				true, true, true, authorities);
	}

}
