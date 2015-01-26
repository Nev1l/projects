package by.epam.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
@Table(name = "Task")
public class Task implements Serializable {
	private static final long serialVersionUID = -5121738018952980031L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@Column(name = "description", length = 255)
	private String description;

	@Column(name = "psd")
	private String plannedStartDate;

	@Column(name = "ped")
	private String plannedEndDate;

	@Column(name = "asd")
	private String actualStartDate;

	@Column(name = "aed")
	private String actualEndDate;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;

	public int getId() {
		return id;
	}

	public void setId(int id)  {
		/*throws DaoException if (id < 0) {
			throw new DaoException(ConstantsError.wrondId);
		}*/
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) throws DaoException {
		if (project == null) {
			throw new DaoException(ConstantsError.projectIncorrect);
		}
		this.project = project;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) throws DaoException {
		if (description == null) {
			throw new DaoException(ConstantsError.descriptionNull);
		}
		if (description.equals(ConstantsError.Empty)) {
			throw new DaoException(ConstantsError.descriptionEmpty);
		}
		this.description = description;
	}

	public String getPlannedStartDate() {
		return plannedStartDate;
	}

	public void setPlannedStartDate(String plannedStartDate)
			throws DaoException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(
					ConstantsJSP.DATE_FORMAT);
			sdf.parse(plannedStartDate);
			this.plannedStartDate = plannedStartDate;
		} catch (ParseException e) {
			throw new DaoException(ConstantsError.errorDateFormat);
		}
	}

	public String getPlannedEndDate() {
		return plannedEndDate;
	}

	public void setPlannedEndDate(String plannedEndDate) throws DaoException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(
					ConstantsJSP.DATE_FORMAT);
			Date aed = sdf.parse(plannedEndDate);
			if (!plannedStartDate.equals(ConstantsError.Empty)) {
				if (sdf.parse(plannedStartDate).before(aed) || sdf.parse(plannedStartDate).equals(aed)) {
					this.plannedEndDate = plannedEndDate;
				} else {
					throw new DaoException(ConstantsError.actualErrorDate);
				}
			}
			this.plannedEndDate = plannedEndDate;
		} catch (ParseException e) {
			throw new DaoException(ConstantsError.errorDateFormat);
		}
	}

	public String getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(String actualStartDate) throws DaoException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(
					ConstantsJSP.DATE_FORMAT);
			sdf.parse(actualStartDate);
			this.actualStartDate = actualStartDate;
		} catch (ParseException e) {
			throw new DaoException(ConstantsError.errorDateFormat);
		}
	}

	public String getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(String actualEndDate) throws DaoException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(
					ConstantsJSP.DATE_FORMAT);// yyyy/MM/dd
			Date aed = sdf.parse(actualEndDate);
			if (!actualStartDate.equals(ConstantsError.Empty)) {
				if (sdf.parse(actualStartDate).before(aed) || sdf.parse(actualStartDate).equals(aed)) {
					this.actualEndDate = actualEndDate;
				} else {
					throw new DaoException(ConstantsError.actualErrorDate);
				}
			}
			this.actualEndDate = actualEndDate;
		} catch (ParseException e) {
			throw new DaoException(ConstantsError.errorDateFormat);
		}
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) throws DaoException {
		if (status == null) {
			throw new DaoException(ConstantsError.statusIncorrect);
		}
		this.status = status;
	}

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + description + " " + status.getName() + "]";
	}

}
