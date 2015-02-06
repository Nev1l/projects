package by.epam.utils;

import java.text.SimpleDateFormat;

import by.epam.consts.ConstantsJSP;

public class Convert {
	
	public static String getCurrentDateTime() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat(ConstantsJSP.DATE_TIME_FORMAT);
	    java.util.Date now = new java.util.Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}
}
