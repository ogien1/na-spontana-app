package pl.lodz.p.it.naspontanaapp.utils;

import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;

public class TimeYodaUtils {

	public static long getMinutes(LocalDateTime start, LocalDateTime end) {
		return new Duration(start.toDateTime(DateTimeZone.UTC),
				end.toDateTime(DateTimeZone.UTC)).toStandardMinutes().getMinutes();
	}

	public static long getSeconds(LocalDateTime start, LocalDateTime end) {
		return new Duration(start.toDateTime(DateTimeZone.UTC),
				end.toDateTime(DateTimeZone.UTC)).toStandardSeconds().getSeconds();
	}

}
