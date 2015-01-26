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
@Table(name = "Activity")
public class Activity implements Serializable {
	private static final long serialVersionUID = -6438307689729658459L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "activity_date")
	private String date;

	@Column(name = "comment", length = 255)
	private String comment;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) throws DaoException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(
					ConstantsJSP.DATE_TIME_FORMAT);
			sdf.parse(date);
			this.date = date;
		} catch (ParseException e) {
			throw new DaoException(ConstantsError.errorDateFormat);
		}
		
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static String getCurrentDateTime() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat(ConstantsJSP.DATE_TIME_FORMAT);
	    java.util.Date now = new java.util.Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}

	@Override
	public String toString() {
		return "Activity [member=" + member + "]";
	}

}
