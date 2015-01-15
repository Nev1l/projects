import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import by.epam.beans.Project;
import by.epam.beans.Status;
import by.epam.consts.ConstantsError;
import by.epam.consts.ConstantsJSP;


public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String project_id = "123";
		try {
			int projectId = Integer.parseInt(project_id);
		} catch (NumberFormatException e) {
			System.out.println(e);
		}
	}

}
