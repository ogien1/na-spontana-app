package pl.lodz.p.it.naspontanaapp.utils;

import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;

/**
 * Oblicza rożnicę w datach
 */
public class TimeYodaUtils {

	/**
	 * Oblicza liczę minut pomiędzy datami
	 * @param start
	 * @param end
	 * @return
	 */
	public static long getMinutes(LocalDateTime start, LocalDateTime end) {
		return Math.abs(new Duration(start.toDateTime(DateTimeZone.UTC),
				end.toDateTime(DateTimeZone.UTC)).toStandardMinutes().getMinutes());
	}

	/**
	 * Oblicza liczbę sekund pomiędzy datami
	 * @param start
	 * @param end
	 * @return
	 */
	public static long getSeconds(LocalDateTime start, LocalDateTime end) {
		return Math.abs(new Duration(start.toDateTime(DateTimeZone.UTC),
				end.toDateTime(DateTimeZone.UTC)).toStandardSeconds().getSeconds());
	}

}
