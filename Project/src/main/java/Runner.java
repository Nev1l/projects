import java.sql.Date;
import java.sql.Timestamp;


public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date d1 = new Date(1992,1,18);
		java.sql.Date d2 = new java.sql.Date(1992,1,18);
		Timestamp t = new Timestamp(1992,1,18,10,46,12,0);
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(t);
	}

}
