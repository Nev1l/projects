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
@Table(name = "Activity")
public class Activity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "activity_date")
	private Date date;

	@Column(name = "duration")
	private int duration;

	@Column(name = "comment",length=255)
	private String comment;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "assignment_id")
	private Assignment assignment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public Activity(int id, Date date, int duration, String comment,
			Member member, Assignment assignment) {
		super();
		this.id = id;
		this.date = date;
		this.duration = duration;
		this.comment = comment;
		this.member = member;
		this.assignment = assignment;
	}

	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
