package by.epam.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class User {
	@Column(name = "login")
	private String login;

	@Column(name = "password")
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

}
