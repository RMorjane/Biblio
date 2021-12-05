package co.simplon.util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatUtil {

	public static final String SLASH_DATE_FORMAT = "dd/MM/yyyy";
	public static final String POINT_DATE_FORMAT = "dd.MM.yyyy";
	public static final String DASH_DATE_FORMAT = "dd-MM-yyyy";
	
	private String formatDate;
	
	public DateFormatUtil(String formatDate) {
		super();
		this.formatDate = formatDate;
	}

	public Date getDateFromString(String strDate)
	{
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(formatDate);
			return dateFormat.parse(strDate);
		}
		catch(ParseException e) {
			e.printStackTrace();
			return null;
		}				
	}
	
	public String getStringFromDate(Date date)
	{
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(formatDate);
			return dateFormat.format(date);			
		}
		catch(NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String toString() {
		return "DateFormatUtil [formatDate=" + formatDate + "]";
	}
}
