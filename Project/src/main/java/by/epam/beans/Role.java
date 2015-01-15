package by.epam.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
public class Role implements Serializable{
	private static final long serialVersionUID = -6279591525680987659L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name",length=50)
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
	public void setName(ProjectPosition pos) {
		this.name = pos.getPosition();
	}

	public Role(ProjectPosition pos) {
		super();
		setName(pos);
		// TODO Auto-generated constructor stub
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isAdmin() {
		return name.equals(ProjectPosition.ADMIN.getPosition());
	}
	
	public boolean isManager() {
		return name.equals(ProjectPosition.MANAGER.getPosition());
	}
	
	public boolean isLead() {
		return name.equals(ProjectPosition.LEAD.getPosition());
	}
	public boolean isDeveloper() {
		return name.equals(ProjectPosition.DEVELOPER.getPosition());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[role:"+name+"]";
	}
	
}
