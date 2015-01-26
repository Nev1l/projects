package by.epam.consts;

public class ConstantsJSP {
/*	public final static String plannedStartDate = "psd";
	public final static String plannedEndDate = "ped";
	public final static String actualStartDate = "asd";
	public final static String actualEndDate = "aed";
	public final static String description = "description";
	public final static String status = "st";
*/
	public final static String id = "id";
	
	public final static int RESULTS_ON_LOAD = 10;
	public final static String FILE_ERROR = "Error(File)";

	public final static String DATE_FORMAT = "yyyy-MM-dd";
	public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public final static String homeController = "home.do";
	public final static String projectController = "project.do";
	public final static String memberController = "member.do";
	public final static String taskController = "task.do";
	
	public final static String projectUpdateController = "projectUpdate.do";
	public final static String projectNewController = "projectNew.do";
	public final static String taskUpdateController = "taskUpdate.do";
	public final static String taskNewController = "taskNew.do";
	
	public final static String homePage = "home";
	public final static String userProfilePage = "userprofile";
	public final static String loginPage = "login";
	public final static String logoutPage = "logout";

	public final static String memberPage = "member";
	public final static String memberNewPage = "memberNew";
	public final static String memberEditPage = "memberEdit";

	public final static String rolePage = "role";
	public final static String assignmentPage = "assignment";

	public final static String projectPage = "project";
	public final static String projectNewPage = "projectNew";
	public final static String projectEditPage = "projectEdit";

	public final static String taskPage = "task";
	public final static String taskNewPage = "taskNew";
	public final static String taskEditPage = "taskEdit";

	public final static String activityPage = "activity";
	public final static String assigneePage = "assignee";

	// public final static String SECURUTY_CONTEXT = "SPRING_SECURITY_CONTEXT";
	public final static String ASSIGN_AUTO = "(Automatic)";
	public final static String GUEST = "guest";
	public final static String ROLE = "ROLE";
	public final static String NO_AUTH = "Authentication failed";
	public final static String EMPTY = "";

	public final static String EMPLOYEE_PROFILE = "EMPLOYEE_PROFILE";
	public final static String EMPLOYEE = "EMPLOYEE";
	public final static String MEMBER = "MEMBER";
	public final static String MEMBERS = "MEMBERS";
	public final static String TASK = "TASK";
	public final static String PROJECT = "PROJECT";
	public final static String PROJECT_LIST = "PROJECT_LIST";
	public final static String MEMBER_LIST = "MEMBER_LIST";
	public final static String PROJECT_TASKS = "PROJECT_TASKS";
	public final static String ASSIGNEE = "ASSIGNEE";
	public final static String ERROR = "ERROR";

	public final static String MEMBER_ID = "MEMBER_ID";
	public final static String EMPLOYEE_LIST = "EMPLOYEE_LIST";
	public final static String ROLE_LIST = "ROLE_LIST";
	public final static String STATUS_LIST = "STATUS_LIST";
	public final static String EMPLOYEE_ASSIGNMENT = "EMPLOYEE_ASSIGNMENT";
	public final static String MEMBER_PROJECTS = "MEMBER_PROJECTS";
	public final static String PROJECT_MEMBERS = "PROJECT_MEMBERS";
	public final static String ALL_MEMBERS = "ALL_MEMBERS";
	public final static String LAST_ACTIVITY = "LAST_ACTIVITY";
	public final static String RECORD_ON_PAGE = "RECORD_ON_PAGE";
	public final static String RECORD_COUNT = "RECORD_COUNT";
	public final static String CUR_PAGE = "CUR_PAGE";
	public final static String CHECKED = "true";

	public static String getGuest() {
		return GUEST;
	}

	public static String getNoAuth() {
		return NO_AUTH;
	}

	public static String getEmpty() {
		return EMPTY;
	}

	public static String getEmployee() {
		return EMPLOYEE;
	}

	public static String getMember() {
		return MEMBER;
	}

	public static String getTask() {
		return TASK;
	}

	public static String getProject() {
		return PROJECT;
	}

	public static String getProjectList() {
		return PROJECT_LIST;
	}

	public static String getProjectTasks() {
		return PROJECT_TASKS;
	}

	public static String getAssignee() {
		return ASSIGNEE;
	}

	public static String getError() {
		return ERROR;
	}

	public static String getEmployeeAssignment() {
		return EMPLOYEE_ASSIGNMENT;
	}

	public static String getMemberProjects() {
		return MEMBER_PROJECTS;
	}

	public static String getProjectMembers() {
		return PROJECT_MEMBERS;
	}

	public static String getAllMembers() {
		return ALL_MEMBERS;
	}

	public static String getLastActivity() {
		return LAST_ACTIVITY;
	}

	public static String getRecordOnPage() {
		return RECORD_ON_PAGE;
	}

	public static String getRecordCount() {
		return RECORD_COUNT;
	}

	public static String getCurPage() {
		return CUR_PAGE;
	}

}
