package by.epam.project.road;

import by.epam.project.util.Constants;

public class TimeDifference {
    public static double roadTime(String startTime, String endTime) {
        String startDateTimeArray[] = startTime.split(" ");
        String endDateTimeArray[] = endTime.split(" ");
        String startTimeArray[] = startDateTimeArray[1].split(":");
        String endTimeArray[] = endDateTimeArray[1].split(":");
        double startTimeValue = Integer.valueOf(startTimeArray[0]) * Constants.SECONDS_CONSTANT
                + Integer.valueOf(startTimeArray[1]) * Constants.MINUTE_CONSTANT + Integer.valueOf(startTimeArray[2]);
        double endTimeValue = Integer.valueOf(endTimeArray[0]) * Constants.SECONDS_CONSTANT
                + Integer.valueOf(endTimeArray[1]) * Constants.MINUTE_CONSTANT + Integer.valueOf(endTimeArray[2]);
        return endTimeValue / Constants.SECONDS_CONSTANT - startTimeValue / Constants.SECONDS_CONSTANT;
    }

    public static double minuteRoadTime(String startTime, String endTime) {
        String startDateTimeArray[] = startTime.split(" ");
        String endDateTimeArray[] = endTime.split(" ");
        String startTimeArray[] = startDateTimeArray[1].split(":");
        String endTimeArray[] = endDateTimeArray[1].split(":");
        double startTimeValue = Integer.valueOf(startTimeArray[0]) * Constants.SECONDS_CONSTANT
                + Integer.valueOf(startTimeArray[1]) * Constants.MINUTE_CONSTANT + Integer.valueOf(startTimeArray[2]);
        double endTimeValue = Integer.valueOf(endTimeArray[0]) * Constants.SECONDS_CONSTANT
                + Integer.valueOf(endTimeArray[1]) * Constants.MINUTE_CONSTANT + Integer.valueOf(endTimeArray[2]);
        return endTimeValue / Constants.MINUTE_CONSTANT - startTimeValue / Constants.MINUTE_CONSTANT;
    }
}
