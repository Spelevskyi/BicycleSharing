package by.epam.project.builder;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.entity.billing.PriceList;
import by.epam.project.exception.DaoException;
import by.epam.project.util.Constants;

public class PriceListBuilder {

    private static final Logger logger = LogManager.getLogger(PriceListBuilder.class);

    public static Optional<PriceList> createPriceList(ResultSet result) throws DaoException {
        try {
            result.beforeFirst();
            result.next();
            String brand = result.getString(Constants.PRICE_LIST_BRAND);
            BigDecimal unlockPrice = result.getBigDecimal(Constants.UNLOCK_PRICE);
            BigDecimal perMinutePrice = result.getBigDecimal(Constants.PRICE_LIST_PER_MINUTE);
            BigDecimal perHourPrice = result.getBigDecimal(Constants.PRICE_LIST_PER_HOUR);
            BigDecimal stayPrice = result.getBigDecimal(Constants.PRICE_LIST_STAY);
            BigDecimal threeHourDiscount = result.getBigDecimal(Constants.PRICE_LIST_THREE_HOUR_DISCOUNT);
            BigDecimal sixHourDiscount = result.getBigDecimal(Constants.PRICE_LIST_SIX_HOUR_DISCOUNT);
            BigDecimal nineHourDiscount = result.getBigDecimal(Constants.PRICE_LIST_NINE_HOUR_DISCOUNT);
            BigDecimal daySale = result.getBigDecimal(Constants.PRICE_LIST_ALL_DAY);
            BigDecimal travelerDiscount = result.getBigDecimal(Constants.PRICE_LIST_PER_MINUTE);
            BigDecimal newCustomerDiscount = result.getBigDecimal(Constants.PRICE_LIST_PER_MINUTE);
            BigDecimal regularCustomerDiscount = result.getBigDecimal(Constants.PRICE_LIST_PER_MINUTE);
            PriceList list = new PriceList(brand, unlockPrice, perMinutePrice, perHourPrice, stayPrice,
                    threeHourDiscount, sixHourDiscount, nineHourDiscount, daySale, regularCustomerDiscount,
                    travelerDiscount, newCustomerDiscount);
            list.setId(Integer.valueOf(result.getInt(Constants.PRICE_LIST_ID)));
            logger.info("Price list was found!");
            return Optional.of(list);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    // PriceListBuilder method for creating price list for current bicycle
    public static Optional<PriceList> createBicyclePriceList(ResultSet result) throws DaoException {
        try {
            String brand = result.getString(Constants.PRICE_LIST_BRAND);
            BigDecimal unlockPrice = result.getBigDecimal(Constants.UNLOCK_PRICE);
            BigDecimal perMinutePrice = result.getBigDecimal(Constants.PRICE_LIST_PER_MINUTE);
            BigDecimal perHourPrice = result.getBigDecimal(Constants.PRICE_LIST_PER_HOUR);
            BigDecimal stayPrice = result.getBigDecimal(Constants.PRICE_LIST_STAY);
            BigDecimal threeHourDiscount = result.getBigDecimal(Constants.PRICE_LIST_THREE_HOUR_DISCOUNT);
            BigDecimal sixHourDiscount = result.getBigDecimal(Constants.PRICE_LIST_SIX_HOUR_DISCOUNT);
            BigDecimal nineHourDiscount = result.getBigDecimal(Constants.PRICE_LIST_NINE_HOUR_DISCOUNT);
            BigDecimal daySale = result.getBigDecimal(Constants.PRICE_LIST_ALL_DAY);
            BigDecimal travelerDiscount = result.getBigDecimal(Constants.PRICE_LIST_PER_MINUTE);
            BigDecimal newCustomerDiscount = result.getBigDecimal(Constants.PRICE_LIST_PER_MINUTE);
            BigDecimal regularCustomerDiscount = result.getBigDecimal(Constants.PRICE_LIST_PER_MINUTE);
            PriceList list = new PriceList(brand, unlockPrice, perMinutePrice, perHourPrice, stayPrice,
                    threeHourDiscount, sixHourDiscount, nineHourDiscount, daySale, regularCustomerDiscount,
                    travelerDiscount, newCustomerDiscount);
            list.setId(Integer.valueOf(result.getInt(Constants.PRICE_LIST_ID)));
            logger.info("Price list was found!");
            return Optional.of(list);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    public static ArrayList<PriceList> createList(ResultSet result) throws DaoException {
        logger.info("Finding all price lists.");
        try {
            ArrayList<PriceList> lists = new ArrayList<>();
            while (result.next()) {
                String brand = result.getString(Constants.PRICE_LIST_BRAND);
                BigDecimal unlockPrice = result.getBigDecimal(Constants.UNLOCK_PRICE);
                BigDecimal perMinutePrice = result.getBigDecimal(Constants.PRICE_LIST_PER_MINUTE);
                BigDecimal perHourPrice = result.getBigDecimal(Constants.PRICE_LIST_PER_HOUR);
                BigDecimal stayPrice = result.getBigDecimal(Constants.PRICE_LIST_STAY);
                BigDecimal threeHourDiscount = result.getBigDecimal(Constants.PRICE_LIST_THREE_HOUR_DISCOUNT);
                BigDecimal sixHourDiscount = result.getBigDecimal(Constants.PRICE_LIST_SIX_HOUR_DISCOUNT);
                BigDecimal nineHourDiscount = result.getBigDecimal(Constants.PRICE_LIST_NINE_HOUR_DISCOUNT);
                BigDecimal daySale = result.getBigDecimal(Constants.PRICE_LIST_ALL_DAY);
                BigDecimal travelerDiscount = result.getBigDecimal(Constants.PRICE_LIST_PER_MINUTE);
                BigDecimal newCustomerDiscount = result.getBigDecimal(Constants.PRICE_LIST_PER_MINUTE);
                BigDecimal regularCustomerDiscount = result.getBigDecimal(Constants.PRICE_LIST_PER_MINUTE);
                PriceList list = new PriceList(brand, unlockPrice, perMinutePrice, perHourPrice, stayPrice,
                        threeHourDiscount, sixHourDiscount, nineHourDiscount, daySale, regularCustomerDiscount,
                        travelerDiscount, newCustomerDiscount);
                list.setId(Integer.valueOf(result.getInt(Constants.PRICE_LIST_ID)));
                lists.add(list);
                logger.info("Price list was found!");
            }
            logger.info("All lists were found!");
            return lists;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

}
