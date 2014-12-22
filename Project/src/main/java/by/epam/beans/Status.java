package by.epam.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Status")
public class Status {
	public enum State {
		NOT_STARTED("No started"), STARTED("Started"), SUSPENDED("Suspended"), FINISHED(
				"Finished");
		
		String state;

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		private State(String state) {
			this.state = state;
		}

	}

	@Id
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