package by.epam.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class User implements Serializable{
	private static final long serialVersionUID = 7267530828092831451L;

	@Column(name = "login", nullable = false, length = 50)
	private String login;

	@Column(name = "password", nullable = false, length = 50)
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
		setLogin(login);
		setPassword(password);
	}

}
