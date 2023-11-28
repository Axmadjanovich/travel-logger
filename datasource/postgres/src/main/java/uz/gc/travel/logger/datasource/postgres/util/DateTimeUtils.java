package uz.gc.travel.logger.datasource.postgres.util;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author a.ergashev
 * Date: 11/27/2023
 * Time: 8:02 PM
 */
@UtilityClass
@Log4j2
public class DateTimeUtils {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm[:ss.nnnnnn]");

    public String formatInstantToDate(LocalDateTime instant){
        if (instant == null) return null;
        try {
            return dateFormatter.format(instant);
        }catch (DateTimeException e){
            log.error("Error while formatting instant[{}] to date: {}", instant, e.getMessage());
            return null;
        }
    }

    public static LocalDateTime fromString(String date){
        if (date == null) return null;
        try {
            return LocalDateTime.parse(date, dateTimeFormatter);
        }catch (Exception e){
            log.error("Error while parsing date[{}] to Instant: {}", date, e.getMessage());
            return null;
        }
    }
}
