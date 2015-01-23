package by.epam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.epam.beans.Activity;
import by.epam.beans.Assignment;
import by.epam.beans.Member;
import by.epam.beans.Project;
import by.epam.beans.Task;
import by.epam.dao.DaoException;
import by.epam.dao.WorkServiceDAO;

@Service
public class ActivityService {
	@Autowired
	private WorkServiceDAO workService;

	public void activityChangeProject(Project projectOld, Project projectNew,
			Member activityMember) throws DaoException {
		String projectName = projectOld.getName();
		String comment = null;
		if (!projectOld.getName().equals(projectNew.getName())) {
			projectName = projectNew.getName();
			comment = "changed project name from " + projectOld.getName()
					+ " to " + projectNew.getName();
			Activity activity = new Activity();
			activity.setComment(comment);
			activity.setDate(Activity.getCurrentDateTime());
			activity.setMember(activityMember);
			workService.save(activity);
		}
		if (!projectOld.getDescription().equals(projectNew.getDescription())) {
			comment = "changed project(" + projectName + ") description to "
					+ projectNew.getDescription();
			Activity activity = new Activity();
			activity.setComment(comment);
			activity.setDate(Activity.getCurrentDateTime());
			activity.setMember(activityMember);
			workService.save(activity);
		}
		if (!projectOld.getStatus().getName()
				.equals(projectNew.getStatus().getName())) {
			comment = "changed project(" + projectName + ") status from "
					+ projectOld.getStatus().getName() + " to "
					+ projectNew.getStatus().getName();
			Activity activity = new Activity();
			activity.setComment(comment);
			activity.setDate(Activity.getCurrentDateTime());
			activity.setMember(activityMember);
			workService.save(activity);
		}
	}

	public void activityCreateProject(Project project, Member activityMember)
			throws DaoException {
		String comment = "created project " + project.getName();
		Activity activity = new Activity();
		activity.setComment(comment);
		activity.setDate(Activity.getCurrentDateTime());
		activity.setMember(activityMember);
		comment = "was added to member list of " + project.getName();
		activity = new Activity();
		activity.setComment(comment);
		activity.setDate(Activity.getCurrentDateTime());
		activity.setMember(activityMember);
		workService.save(activity);
	}

	public void activityChangeTask(Task t1, Task t2, Member activityMember)
			throws DaoException {
		String taskDescription = t1.getDescription();
		String comment = null;
		if (!t1.getDescription().equals(t2.getDescription())) {
			comment = "changed task description from " + t1.getDescription()
					+ " to " + t2.getDescription();
			Activity activity = new Activity();
			activity.setComment(comment);
			activity.setDate(Activity.getCurrentDateTime());
			activity.setMember(activityMember);
			workService.save(activity);
		}
		if (!t1.getStatus().getName().equals(t2.getStatus().getName())) {
			comment = "changed task(" + taskDescription + ") status from "
					+ t1.getStatus().getName() + " to "
					+ t2.getStatus().getName();
			Activity activity = new Activity();
			activity.setComment(comment);
			activity.setDate(Activity.getCurrentDateTime());
			activity.setMember(activityMember);
			workService.save(activity);
		}
	}

	public void activityChangeAssignee(Assignment assignment, Member activityMember)
			throws DaoException {
		String assignee = assignment.getMember().getEmployee().getFirstName()+" "+ assignment.getMember().getEmployee().getLastName();
		String comment = "changed the Assignee to " + assignee +" of task("+assignment.getTask().getDescription()+")";
		Activity activity = new Activity();
		activity.setComment(comment);
		activity.setDate(Activity.getCurrentDateTime());
		activity.setMember(activityMember);
		workService.save(activity);
	}
	public void activityCreateTask(Task task, Member activityMember)
			throws DaoException {
		String comment = "created task " + task.getDescription();
		Activity activity = new Activity();
		activity.setComment(comment);
		activity.setDate(Activity.getCurrentDateTime());
		activity.setMember(activityMember);
		workService.save(activity);
	}
	
	public void activityCreateAssignee(Assignment assignment, Member activityMember)
			throws DaoException {
		activityCreateTask(assignment.getTask(),activityMember);
		String assignee = assignment.getMember().getEmployee().getFirstName()+" "+ assignment.getMember().getEmployee().getLastName();
		String comment = "assigned task(" + assignment.getTask().getDescription()+") to "+assignee;
		Activity activity = new Activity();
		activity.setComment(comment);
		activity.setDate(Activity.getCurrentDateTime());
		activity.setMember(activityMember);
		workService.save(activity);
	}
}
