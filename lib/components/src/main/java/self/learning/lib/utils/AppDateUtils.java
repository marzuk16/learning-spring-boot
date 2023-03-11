package self.learning.lib.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public final class AppDateUtils {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_WITHOUT_TIMESTAMP = "yyyy-MM-dd";
    public static final String DATE_FORMAT_WITHOUT_TIMESTAMP_SLASH_SEPARATED = "dd/MM/yyyy";

    private AppDateUtils() {
    }

    private static final Logger LOG = LoggerFactory.getLogger(AppDateUtils.class);

    public static String now() {
        return DateFormatUtils.format(new Date(), DEFAULT_DATE_FORMAT);
    }

    public static String now(String timeZone) {
        return DateFormatUtils.format(new Date(), DEFAULT_DATE_FORMAT, TimeZone.getTimeZone(timeZone));
    }

    public static String convert(String date, String srcFormat, String destFormat) throws ParseException {
        Date d = DateUtils.parseDate(date, new String[] { srcFormat });
        return d != null ? DateFormatUtils.format(d, destFormat) : "";
    }

    public static Date parseDate(String date, String format) {
        try {
            return DateUtils.parseDate(date, new String[] { format });
        } catch (ParseException e) {
            LOG.error("Error in parsing date.", e);
            throw new DateTimeException(e.getMessage());
        }
    }

	public static boolean isWritableDateToDataBase(Date date){
		if(date == null)
			return true;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if(calendar.get(Calendar.YEAR) > 9999)
			return false;
		return true;
	}

    public static Integer calculateAge(Date dob) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dob);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DATE);
        LocalDate localDateDOB = LocalDate.of(year, month, date);
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(localDateDOB, currentDate);
        return age.getYears();
    }

    public static Date setHoursMinutesAndSecondsToDate(Date date, int hours, int minute, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hours);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    public static Date getDateWithYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    public static Long getNextYear() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        return Long.valueOf(calendar.get(Calendar.YEAR) + 1);
    }

	public static Long getCurrentYear() {
		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		return Long.valueOf(calendar.get(Calendar.YEAR));
	}

	public static Long getYearFromDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return Long.valueOf(calendar.get(Calendar.YEAR));
	}

	public static boolean isBetweenStartAndEndDate(String startDateString, String endDateString) {

		if(startDateString == null || endDateString == null) {
			return false;
		}

		Date startDate = parseDate(startDateString, DEFAULT_DATE_FORMAT);
		Date endDate = parseDate(endDateString, DEFAULT_DATE_FORMAT);

		return isBetweenStartAndEndDate(startDate, endDate);
	}

	public static boolean isBetweenStartAndEndDate(Date startDate, Date endDate) {

		if (startDate == null || endDate == null) {
			return false;
		}

		Date currentDate = new Date();

		return (currentDate.after(startDate) || currentDate.equals(startDate))
				&& (endDate.after(currentDate) || endDate.equals(currentDate));
	}
}
