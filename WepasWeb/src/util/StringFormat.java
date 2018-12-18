package util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class StringFormat
{
	private static NumberFormat moneyFormat;
	private static NumberFormat numberFormat;
	private static DateFormat dateFormat;
	private static DateFormat timeFormat;
	
	public static String formatMoney(double value)
	{
		if (moneyFormat == null)
			moneyFormat = new DecimalFormat("###,##0.00");
		
		return moneyFormat.format(value);
	}
	
	public static String formatNumber(int value)
	{
		if (numberFormat == null)
			numberFormat = new DecimalFormat("#####0");
		
		return numberFormat.format(value);
	}
	
	public static String formatDate(GregorianCalendar gc)
	{
		dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return dateFormat.format(gc.getTime());
	}
	
	public static String formatDateShort(GregorianCalendar gc)
	{
		dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
		return dateFormat.format(gc.getTime());
	}
	
	public static String formatDateTime(GregorianCalendar gc)
	{
		dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
		timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
		return dateFormat.format(gc.getTime()) + " um " + timeFormat.format(gc.getTime()) + " Uhr";
	}
	
	public static String formatDateTimeShort(GregorianCalendar gc)
	{
		dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
		timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
		return dateFormat.format(gc.getTime()) + " um " + timeFormat.format(gc.getTime()) + " ";
	}
	
	public static String formatDateTimeSmall(GregorianCalendar gc)
	{
		String s = "";
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == 1)
		{
			s = "So, ";
		}
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == 2)
		{
			s = "Mo, ";
		}
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == 3)
		{
			s = "Di, ";
		}
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == 4)
		{
			s = "Mi, ";
		}
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == 5)
		{
			s = "Do, ";
		}
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == 6)
		{
			s = "Fr, ";
		}
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == 7)
		{
			s = "Sa, ";
		}
		dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
		timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
		return s + timeFormat.format(gc.getTime());
	}
	
	public static Date formatDate(String s) throws ParseException
	{
		Date date = null;
		DateFormat parser = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMANY);
		try
		{
			date = parser.parse(s);
		}
		catch (ParseException e)
		{
			throw e;
		}
		return date;
	}
	
	public static String format(Date date)
	{
		SimpleDateFormat df = new SimpleDateFormat("EEE, dd. MMM HH:mm");
		return df.format(date);
	}	
	
	public static String formatDateShort(Date date)
	{
		SimpleDateFormat df = new SimpleDateFormat("EEE, dd. MMM");
		return df.format(date);
	}
	
}
