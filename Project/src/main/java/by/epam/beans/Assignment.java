package by.epam.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import by.epam.consts.ConstantsError;
import by.epam.consts.ConstantsJSP;
import by.epam.dao.DaoException;

@Entity
@Table(name = "Assignment")
public class Assignment implements Serializable {
	private static final long serialVersionUID = -8708140777543288677L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;

	//@Column(name = "description", length = 255)
	//private String description;

	// my field
	@Column(name = "assign_date")
	private String assignDate;

	/*
	 * @Column(name = "psd") private Date plannedStartDate;
	 * 
	 * @Column(name = "ped") private Date plannedEndDate;
	 * 
	 * @Column(name = "asd") private Date actualStartDate;
	 * 
	 * @Column(name = "aed") private Date actualEndDate;
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(String assignDate) throws DaoException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(
					ConstantsJSP.DATE_TIME_FORMAT);
			sdf.parse(assignDate);
			this.assignDate = assignDate;
		} catch (ParseException e) {
			throw new DaoException(ConstantsError.errorDateFormat);
		}
	}
	
	public static String getCurrentDateTime() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat(ConstantsJSP.DATE_TIME_FORMAT);
	    java.util.Date now = new java.util.Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) throws DaoException {
		if(member==null){
			throw new DaoException(ConstantsError.memberIncorrect);
		}
		this.member = member;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) throws DaoException {
		if (task==null) {
			throw new DaoException(ConstantsError.taskIncorrect);
		}
		this.task = task;
	}

	/*public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) throws DaoException {
		if (description == null) {
			throw new DaoException(ConstantsError.descriptionNull);
		}
		if (description.equals(ConstantsError.Empty)) {
			throw new DaoException(ConstantsError.descriptionEmpty);
		}
	}*/

	/*
	 * public Date getPlannedStartDate() { return plannedStartDate; }
	 * 
	 * public void setPlannedStartDate(Date plannedStartDate) {
	 * this.plannedStartDate = plannedStartDate; }
	 * 
	 * public Date getPlannedEndDate() { return plannedEndDate; }
	 * 
	 * public void setPlannedEndDate(Date plannedEndDate) { this.plannedEndDate
	 * = plannedEndDate; }
	 * 
	 * public Date getActualStartDate() { return actualStartDate; }
	 * 
	 * public void setActualStartDate(Date actualStartDate) {
	 * this.actualStartDate = actualStartDate; }
	 * 
	 * public Date getActualEndDate() { return actualEndDate; }
	 * 
	 * public void setActualEndDate(Date actualEndDate) { this.actualEndDate =
	 * actualEndDate; }
	 */

	public Assignment() {
		super();
		// TODO Auto-generated constructor stub
	}

}
