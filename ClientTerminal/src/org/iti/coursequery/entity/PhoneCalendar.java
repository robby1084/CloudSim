package org.iti.coursequery.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.iti.common.util.StringConvert;

public class PhoneCalendar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2719870987876363772L;
	
	private String year;
	private String term;
	private String weeks;
	private String startTime;

	public String getYear() {
		return year;
	}

	public String getTerm() {
		return term;
	}

	public String getWeeks() {
		return weeks;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		return "PhoneCalendar [year=" + year + ", term=" + term + ", weeks="
				+ weeks + ", startTime=" + startTime + "]";
	}

	public String[][] getCalendar() {
		String calendarStartTime = this.startTime.split("\\.")[0];
		int weeks = 20;
		try {
			weeks = Integer.parseInt(this.weeks.trim());
		} catch (Throwable e) {
			weeks = 20;
		}
		int daySum = weeks * 7;
		Calendar calendar = Calendar.getInstance();
		Date startDate = StringConvert.timeConvert1(calendarStartTime);
		String[][] array = new String[20][7];
		int weekCount = 1;
		for (int i = 0; i < daySum; i++) {

			calendar.setTime(startDate);
			int startMonth = calendar.get(Calendar.MONTH) + 1;
			int startDay = calendar.get(Calendar.DATE);
			long startMillions = startDate.getTime() + 24L * 60L * 60L * 1000L;
			startDate = new Date(startMillions);
			array[weekCount / 7][i % 7] = new StringBuilder()
					.append(startMonth).append("_").append(startDay).toString();
			weekCount++;
		}
		return array;
	}

	public static void main(String[] args) {
		String calendarStartTime = "2014-09-01 00:00:01";
		Date startDate = StringConvert.timeConvert1(calendarStartTime);
		System.out.println(startDate.getTime() + "");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		int day = calendar.get(Calendar.DATE);
		System.out.println(day);
		int month = calendar.get(Calendar.MONTH);
		System.out.println(month);
	}
}
