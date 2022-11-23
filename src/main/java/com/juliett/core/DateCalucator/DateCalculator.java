package com.juliett.core.DateCalucator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.TimeZone;

public class DateCalculator {

	public String addMonthToCurrentMonth(int n) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, n);
		String result = dateFormat.format(cal.getTime());
		return result;

	}

	public String addYearToCurrentYear(int n) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, n);
		String result = dateFormat.format(cal.getTime());
		return result;

	}

	public String addMonthsToMonth(String date, int n) {
		LocalDate date2 = LocalDate.parse(date);
		// Displaying date

		// Add 2 months to the date
		LocalDate newDate = date2.plusMonths(n);
		String result = newDate.toString();
		return result;

	}
	
}
