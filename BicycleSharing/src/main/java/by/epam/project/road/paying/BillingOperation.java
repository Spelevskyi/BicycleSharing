package by.epam.project.road.paying;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.entity.billing.PriceList;
import by.epam.project.entity.order.RentalOrder;
import by.epam.project.entity.user.User;
import by.epam.project.road.TimeDifference;
import by.epam.project.util.Constants;
import by.epam.project.util.CurrentDate;

public class BillingOperation {

    private static final Logger logger = LogManager.getLogger(BillingOperation.class);

    /**
     * Static method for counting price of user road
     * 
     * @param order - users order
     * @param user  - current user
     * @param list  - price list for bicycle in order
     * @return BigDecimal value of price
     */
    public static BigDecimal ChangeBalance(RentalOrder order, User user, PriceList list) {
        logger.info("Counting road price after ending road.");
        String dateTime = CurrentDate.getCurrentDate();
        double stayingTotalPrice = TimeDifference.minuteRoadTime(order.getBookedStartTime(), order.getActualStartTime())
                * list.getStayPrice().doubleValue();
        double roadTime = (double) TimeDifference.minuteRoadTime(order.getActualStartTime(), dateTime);
        double roadTotalPrice = 0;
        if (roadTime < Constants.MINUTE_CONSTANT) {
            logger.info("Road time less than one hour!");
            roadTotalPrice = roadTime * list.getPerMinutePrice().doubleValue();
        } else if (roadTime >= Constants.MINUTE_CONSTANT) {
            logger.info("Road time bigger than one hour!");
            double minuteTime = roadTime % Constants.MINUTE_CONSTANT;
            double hourTime = roadTime - minuteTime;
            if (Constants.THREE_HOUR <= hourTime || hourTime < Constants.SIX_HOUR) {
                logger.info("Counting price with three hour discount!");
                hourTime -= hourTime * (list.getThreeHoursDiscount().doubleValue() / Constants.PERCENTAGE_DIVISOR);
            } else if (Constants.SIX_HOUR <= hourTime || hourTime < Constants.NINE_HOUR) {
                logger.info("Counting price with six hour discount!!");
                hourTime -= hourTime * (list.getSixHoursDiscount().doubleValue() / Constants.PERCENTAGE_DIVISOR);
            } else if (Constants.NINE_HOUR <= hourTime || hourTime < Constants.DAY_HOURS) {
                logger.info("Counting price with nine hour discount!");
                hourTime -= hourTime * (list.getNineHoursDiscount().doubleValue() / Constants.PERCENTAGE_DIVISOR);
            } else {
                logger.info("Counting price with whole day discount!");
                hourTime -= hourTime * (list.getDaySale().doubleValue() / Constants.PERCENTAGE_DIVISOR);
            }
            roadTotalPrice = hourTime * list.getPerHourPrice().doubleValue()
                    + minuteTime * list.getPerMinutePrice().doubleValue();
        }
        if (RegularCustomerChecker.isRegularCustomer(user.getLastRentalDate())) {
            logger.info("Counting price with regular customer discount!");
            roadTotalPrice -= roadTotalPrice
                    * (list.getRegularCustomerDiscount().doubleValue() / Constants.PERCENTAGE_DIVISOR);
        }
        if (user.getRentalAmount() >= Constants.TRAVELER_RENTAL_AMOUNT) {
            logger.info("Counting price with tarveler customer discount!");
            roadTotalPrice -= roadTotalPrice
                    * (list.getTravelerDiscount().doubleValue() / Constants.PERCENTAGE_DIVISOR);
        }
        if (user.getRentalAmount() <= Constants.NEW_RENTAL_AMOUNT) {
            logger.info("Counting price with new customer discount!");
            roadTotalPrice -= roadTotalPrice
                    * (list.getNewCustomerDiscount().doubleValue() / Constants.PERCENTAGE_DIVISOR);
        }
        return new BigDecimal(stayingTotalPrice + roadTotalPrice);
    }


}
