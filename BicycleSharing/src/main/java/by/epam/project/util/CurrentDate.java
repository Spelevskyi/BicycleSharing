package by.epam.project.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentDate {

    public static String getCurrentDate() {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);
        return currentDate.format(formatDate);
    }
}
