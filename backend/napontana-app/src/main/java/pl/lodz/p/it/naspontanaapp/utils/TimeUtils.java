package pl.lodz.p.it.naspontanaapp.utils;

import org.joda.time.LocalDateTime;
import org.joda.time.Period;

public class TimeUtils {

	public static long getMinutes(LocalDateTime start, LocalDateTime end) {
		return Period.fieldDifference(start, end).getMinutes();
	}

}
