package pl.lodz.p.it.naspontanaapp.utils;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Konwertuje daty
 */
public class DateFormater {

    private static final DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * Konwersje datę do String
     * @param localDateTime
     * @return
     */
    public static String convert(LocalDateTime localDateTime) {
        return dateTimeFormat.print(localDateTime);
    }

    /**
     * Konwertuję String do daty
     * @param localDateTime
     * @return
     */
    public static LocalDateTime convert(String localDateTime) {
        return dateTimeFormat.parseLocalDateTime(localDateTime);
    }
}
