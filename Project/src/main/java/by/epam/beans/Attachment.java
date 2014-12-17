package by.epam.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "Attachment")
public class Attachment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name")
	private String name;

	@Column(name = "size")
	private String size;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "projectId")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "taskId")
	private Task task;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Attachment(int id, String name, String size, String description,
			Project project, Task task) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.description = description;
		this.project = project;
		this.task = task;
	}

	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}

}
