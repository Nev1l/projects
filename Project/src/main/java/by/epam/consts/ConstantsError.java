package by.epam.consts;

public interface ConstantsError {
	String Empty = "";
	String memberDeleteError = "Can't delete(Member has assignments or some history of activity).";
	String taskAttachmentError = "File doesn't attach to task";
	String projectAttachmentError = "File doesn't attach to project";
	String projectNameIsEmpty = "Project name is empty.";
	String projectIsNull = "Param is null";
	String projectNameIncorrect = "Param name of project is incorrect.";
	String employeeIncorrect = "Param employee is incorrect.";
	String projectIncorrect = "Param project is incorrect.";
	String statusIncorrect = "Param status is incorrect.";
	String roleIncorrect = "Param role is incorrect.";
	String taskIncorrect = "Param task is incorrect.";
	String memberIncorrect = "Param member is incorrect.";
	String actualErrorDate = "Actual end date is incorrect (before actual start date)";
	String plannedErrorDate = "Planned end date is incorrect (before actual start date)";
	String descriptionNull = "Description is null";
	String descriptionEmpty = "Description is empty";
	String wrondId = "Incorrect id.";
	
	String errorLoginPage = "Wrong input data(login/password)";
	String errorNull = "Field has null value.";
	String errorUserNotExist = "User doesn't exist in database.";
	String errorInputData = "Wrong input data.";
	String errorServer = "Sorry!Server has a problems.";
	String errorNotFound = "Not found.";
	String errorIncorrectId = "Wrong id.";
	String errorParamIsNull = "Param is null.";
	String errorDateFormat = "Incorrect date format.";
}
