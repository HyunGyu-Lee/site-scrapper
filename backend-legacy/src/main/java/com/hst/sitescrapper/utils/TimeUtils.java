package com.hst.sitescrapper.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author dlgusrb0808@gmail.com
 */
public class TimeUtils {
	public static final String DATETIME_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATETIME_S_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.S";

	/***
	 * LocalDateTime to java.util.Date 변환
	 * @param localDateTime 변환할 LocalDateTime 객체
	 * @return java.util.Date
	 */
	public static Date toDate(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}
		return instantToDate(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/***
	 * LocalDate to java.util.Date 변환
	 * @param localDate 변환할 LocalDate 객체
	 * @return java.util.Date
	 */
	public static Date toDate(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		return instantToDate(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	private static Date instantToDate(Instant instant) {
		return Date.from(instant);
	}

	public static long diffHours(LocalDateTime first, LocalDateTime second) {
		return Math.abs(Duration.between(second, first).toHours());
	}

	public static LocalDateTime parseEpochSecond(long second) {
		return Instant.ofEpochSecond(second).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static String format(LocalDateTime time, String pattern) {
		return time.format(pattern(pattern));
	}

	public static String nowFormat(String pattern) {
		return format(LocalDateTime.now(), pattern);
	}

	public static LocalDateTime parse(String source, String pattern) {
		return LocalDateTime.parse(source, pattern(pattern));
	}

	private static DateTimeFormatter pattern(String pattern) {
		return DateTimeFormatter.ofPattern(pattern);
	}


	public static LocalDateTime getNextTime(LocalDateTime target, int hour, int minute, int second) {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime pivot = LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), hour, minute, second);

		if (target.isBefore(pivot)) {
			return pivot;
		} else {
			return target.plusDays(1L).withHour(hour).withMinute(minute).withSecond(second);
		}
	}

	public static boolean isBetween(LocalDateTime target, LocalDateTime begin, LocalDateTime end) {
		return target.isAfter(begin) && target.isBefore(end);
	}
}
