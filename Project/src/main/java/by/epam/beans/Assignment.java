package by.epam.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "Assignment")
public class Assignment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne  
    @JoinColumn(name ="memberId")  
	private Member member;

	@ManyToOne  
    @JoinColumn(name ="taskId") 
	private Task task;

	@Column(name = "description")//unique = true, nullable = false, length = 10
	private String description;

	@Column(name = "psd")
	private Date psd;
	@Column(name = "pdd")
	private Date pdd;
	@Column(name = "asd")
	private Date asd;
	@Column(name = "add")
	private Date add;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
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

	public Assignment(int id, Member member, Task task, String description,
			Date psd, Date pdd, Date asd, Date add) {
		super();
		this.id = id;
		this.member = member;
		this.task = task;
		this.description = description;
		this.psd = psd;
		this.pdd = pdd;
		this.asd = asd;
		this.add = add;
	}

	public Assignment() {
		super();
		// TODO Auto-generated constructor stub
	}

}
