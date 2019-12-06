package by.epam.project.road.paying;

import java.math.BigDecimal;

import by.epam.project.entity.billing.PriceList;
import by.epam.project.entity.order.RentalOrder;
import by.epam.project.entity.user.User;
import by.epam.project.road.TimeDifference;
import by.epam.project.util.Constants;
import by.epam.project.util.CurrentDate;

public class BillingOperation {

    public static BigDecimal ChangeBalance(RentalOrder order, User user, PriceList list) {
        String dateTime = CurrentDate.getCurrentDate();
        double stayingTotalPrice = TimeDifference.minuteRoadTime(order.getBookedStartTime(), order.getActualStartTime())
                * list.getStayPrice().doubleValue();
        double roadTime = (double) TimeDifference.minuteRoadTime(order.getActualStartTime(), dateTime);
        double roadTotalPrice = 0;
        if (roadTime < Constants.MINUTE_CONSTANT) {
            roadTotalPrice = roadTime * list.getPerMinutePrice().doubleValue();
        } else if (roadTime >= Constants.MINUTE_CONSTANT) {
            double minuteTime = roadTime % Constants.MINUTE_CONSTANT;
            double hourTime = roadTime - minuteTime;
            if (Constants.THREE_HOUR <= hourTime || hourTime < Constants.SIX_HOUR) {
                hourTime -= hourTime * (list.getThreeHoursDiscount().doubleValue() / Constants.PERCENTAGE_DIVISOR);
            } else if (Constants.SIX_HOUR <= hourTime || hourTime < Constants.NINE_HOUR) {
                hourTime -= hourTime * (list.getSixHoursDiscount().doubleValue() / Constants.PERCENTAGE_DIVISOR);
            } else if (Constants.NINE_HOUR <= hourTime || hourTime < Constants.DAY_HOURS) {
                hourTime -= hourTime * (list.getNineHoursDiscount().doubleValue() / Constants.PERCENTAGE_DIVISOR);
            } else {
                hourTime -= hourTime * (list.getDaySale().doubleValue() / Constants.PERCENTAGE_DIVISOR);
            }
            roadTotalPrice = hourTime * list.getPerHourPrice().doubleValue()
                    + minuteTime * list.getPerMinutePrice().doubleValue();
        }
        if (RegularCustomerChecker.isRegularCustomer(user.getLastRentalDate())) {
            roadTotalPrice -= roadTotalPrice
                    * (list.getRegularCustomerDiscount().doubleValue() / Constants.PERCENTAGE_DIVISOR);
        }
        if (user.getRentalAmount() >= Constants.TRAVELER_RENTAL_AMOUNT) {
            roadTotalPrice -= roadTotalPrice
                    * (list.getTravelerDiscount().doubleValue() / Constants.PERCENTAGE_DIVISOR);
        }
        if (user.getRentalAmount() <= Constants.NEW_RENTAL_AMOUNT) {
            roadTotalPrice -= roadTotalPrice
                    * (list.getNewCustomerDiscount().doubleValue() / Constants.PERCENTAGE_DIVISOR);
        }
        return new BigDecimal(stayingTotalPrice + roadTotalPrice);
    }


}
