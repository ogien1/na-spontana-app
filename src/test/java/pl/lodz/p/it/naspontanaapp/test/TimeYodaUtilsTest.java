package pl.lodz.p.it.naspontanaapp.test;

import static org.junit.Assert.assertEquals;

import org.joda.time.LocalDateTime;
import org.junit.Test;

import pl.lodz.p.it.naspontanaapp.utils.TimeYodaUtils;

/***
 * Testuje logikę obliczająca różnice w czasie
 */
public class TimeYodaUtilsTest {

	private final static String START_DATE = "2016-12-10T20:32:15";

	private final static String END_DATE = "2016-12-10T20:52:00";

	private final static long END_DATE_START_DATE_MINUTES_DIFF = 19;

	private final static long END_DATE_START_DATE_SECONDS_DIFF = 1185;

	@Test
	public void testGetMinutes() {
		LocalDateTime start = new LocalDateTime(START_DATE);
		LocalDateTime end = new LocalDateTime(END_DATE);

		long endStartMinutes = TimeYodaUtils.getMinutes(start, end);
		long startEndMinutes = TimeYodaUtils.getMinutes(end, start);

		assertEquals(endStartMinutes, END_DATE_START_DATE_MINUTES_DIFF);
		assertEquals(startEndMinutes, END_DATE_START_DATE_MINUTES_DIFF);
	}

	@Test
	public void testSeconds() {
		LocalDateTime start = new LocalDateTime(START_DATE);
		LocalDateTime end = new LocalDateTime(END_DATE);

		long endStartSeconds = TimeYodaUtils.getSeconds(start, end);
		long startEndSeconds = TimeYodaUtils.getSeconds(end, start);

		assertEquals(endStartSeconds, END_DATE_START_DATE_SECONDS_DIFF);
		assertEquals(startEndSeconds, END_DATE_START_DATE_SECONDS_DIFF);
	}

}
