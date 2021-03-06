package by.epam.project.logic.admin;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.BillingDaoImpl;
import by.epam.project.entity.billing.PriceList;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;
import by.epam.project.validation.PriceListValidator;

public class AddBillingLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(ChangeBillingLogic.class);

    private BillingDaoImpl billDao = new BillingDaoImpl();

    /**
     * Logic method for adding new price list
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Adding new billing list executing.");
        if (parameters.size() != Constants.ADD_BILLING_PARAMETERS_AMOUNT) {
            logger.error("Invalid parameters amount for adding new billing!");
            throw new LogicException("Invalid parameters amount for adding new billing!");
        }
        String brand = parameters.get(0);
        BigDecimal unlockPrice = new BigDecimal(parameters.get(1));
        BigDecimal perMinutePrice = new BigDecimal(parameters.get(2));
        BigDecimal perHourPrice = new BigDecimal(parameters.get(3));
        BigDecimal stayPrice = new BigDecimal(parameters.get(4));
        BigDecimal threeHourDiscount = new BigDecimal(parameters.get(5));
        BigDecimal sixHourDiscount = new BigDecimal(parameters.get(6));
        BigDecimal nineHourDiscount = new BigDecimal(parameters.get(7));
        BigDecimal daySale = new BigDecimal(parameters.get(8));
        BigDecimal regularCustomerDiscount = new BigDecimal(parameters.get(9));
        BigDecimal travelerDiscount = new BigDecimal(parameters.get(10));
        BigDecimal newCutomerDiscount = new BigDecimal(parameters.get(11));
        if (!(PriceListValidator.isBrandTypeValid(brand))) {
            logger.error("Invalid brand type input value!");
            throw new LogicException("Invalid brand type input value!");
        }
        if (!(PriceListValidator.isPriceValueValid(unlockPrice))) {
            logger.error("Invalid unlock price input value!");
            throw new LogicException("Invalid unlock price input value!");
        }
        if (!(PriceListValidator.isPriceValueValid(perMinutePrice))) {
            logger.error("Invalid per minute price input value!");
            throw new LogicException("Invalid per minute price input value!");
        }
        if (!(PriceListValidator.isPriceValueValid(perHourPrice))) {
            logger.error("Invalid per hour input value!");
            throw new LogicException("Invalid per hour input value!");
        }
        if (!(PriceListValidator.isPriceValueValid(stayPrice))) {
            logger.error("Invalid stay price input value!");
            throw new LogicException("Invalid stay price input value!");
        }
        if (!(PriceListValidator.isPriceValueValid(threeHourDiscount))) {
            logger.error("Invalid three hour discount input value!");
            throw new LogicException("Invalid three hour discount input value!");
        }
        if (!(PriceListValidator.isPriceValueValid(sixHourDiscount))) {
            logger.error("Invalid six hour discount input value!");
            throw new LogicException("Invalid six hour discount input value!");
        }
        if (!(PriceListValidator.isPriceValueValid(nineHourDiscount))) {
            logger.error("Invalid nine hour discount input value!");
            throw new LogicException("Invalid nine hour discount input value!");
        }
        if (!(PriceListValidator.isPriceValueValid(daySale))) {
            logger.error("Invalid day sale input value!");
            throw new LogicException("Invalid day sale input value!");
        }
        if (!(PriceListValidator.isPriceValueValid(regularCustomerDiscount))) {
            logger.error("Invalid regular customer discount input value!");
            throw new LogicException("Invalid regular customer discount input value!");
        }
        if (!(PriceListValidator.isPriceValueValid(travelerDiscount))) {
            logger.error("Invalid traveler discount input value!");
            throw new LogicException("Invalid traveler discount input value!");
        }
        if (!(PriceListValidator.isPriceValueValid(newCutomerDiscount))) {
            logger.error("Invalid new customer discount input value!");
            throw new LogicException("Invalid new customer discount input value!");
        }
        try {
            PriceList list = new PriceList(brand, unlockPrice, perMinutePrice, perHourPrice, stayPrice,
                    threeHourDiscount, sixHourDiscount, nineHourDiscount, daySale, regularCustomerDiscount,
                    travelerDiscount, newCutomerDiscount);
            billDao.create(list);
            logger.info("Adding new row in bicycle price list.");
        } catch (DaoException ex) {
            System.out.println(ex.getMessage());
            throw new LogicException("Adding new billing list failed!", ex);
        }
    }
}
