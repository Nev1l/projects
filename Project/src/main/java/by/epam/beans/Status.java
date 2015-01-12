package by.epam.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Status")
public class Status implements Serializable{
	private static final long serialVersionUID = 2493430731222117716L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name", length = 50)
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(Status status) {
		this.name = status.toString();
	}

	public Status(State state) {
		super();
		this.name = state.toString();
	}

	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}

}