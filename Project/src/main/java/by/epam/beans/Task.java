package by.epam.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne  
    @JoinColumn(name ="projectId") 
	private Project project;

	@Column(name = "description")
	private String description;

	@Column(name = "psd")
	private Date psd;
	@Column(name = "pdd")
	private Date pdd;
	@Column(name = "asd")
	private Date asd;
	@Column(name = "add")
	private Date add;

	@ManyToOne  
    @JoinColumn(name ="statusId") 
	private Status status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPsd() {
		return psd;
	}

	public void setPsd(Date psd) {
		this.psd = psd;
	}

	public Date getPdd() {
		return pdd;
	}

	public void setPdd(Date pdd) {
		this.pdd = pdd;
	}

	public Date getAsd() {
		return asd;
	}

	public void setAsd(Date asd) {
		this.asd = asd;
	}

	public Date getAdd() {
		return add;
	}

	public void setAdd(Date add) {
		this.add = add;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Task(Long id, Project project, String description, Date psd,
			Date pdd, Date asd, Date add, Status status) {
		super();
		this.id = id;
		this.project = project;
		this.description = description;
		this.psd = psd;
		this.pdd = pdd;
		this.asd = asd;
		this.add = add;
		this.status = status;
	}

}
