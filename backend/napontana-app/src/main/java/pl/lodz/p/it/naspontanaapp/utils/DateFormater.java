package pl.lodz.p.it.naspontanaapp.utils;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by piotr on 12/10/16.
 */
public class DateFormater {

    private static final DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static String convert(LocalDateTime localDateTime) {
        return dateTimeFormat.print(localDateTime);
    }

    public static LocalDateTime convert(String localDateTime) {
        return dateTimeFormat.parseLocalDateTime(localDateTime);
    }
}
