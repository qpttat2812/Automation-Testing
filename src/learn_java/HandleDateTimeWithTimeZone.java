package learn_java;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class HandleDateTimeWithTimeZone {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));

		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		df.setTimeZone(cal.getTimeZone());
		System.out.println(df.format(cal.getTime()));
	}
}
