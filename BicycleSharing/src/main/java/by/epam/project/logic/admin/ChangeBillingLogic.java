package by.epam.project.logic.admin;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.BillingDaoImpl;
import by.epam.project.entity.billing.PriceList;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;
import by.epam.project.validation.PriceListValidator;

public class ChangeBillingLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(ChangeBillingLogic.class);

    public BillingDaoImpl billDao = new BillingDaoImpl();

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of changing price list performing.");
        if (parameters.size() != Constants.CHANGE_BILLING_PARAMETERS_AMOUNT) {
            logger.error("Invalid parameters amount for billing changing!");
            throw new LogicException("Invalid parameters amount for billing changing!");
        }
        BigDecimal unlockPrice = new BigDecimal(parameters.get(0));
        BigDecimal perMinutePrice = new BigDecimal(parameters.get(1));
        BigDecimal perHourPrice = new BigDecimal(parameters.get(2));
        BigDecimal stayPrice = new BigDecimal(parameters.get(3));
        BigDecimal threeHourDiscount = new BigDecimal(parameters.get(4));
        BigDecimal sixHourDiscount = new BigDecimal(parameters.get(5));
        BigDecimal nineHourDiscount = new BigDecimal(parameters.get(6));
        BigDecimal daySale = new BigDecimal(parameters.get(7));
        BigDecimal regularCustomerDiscount = new BigDecimal(parameters.get(8));
        BigDecimal travelerDiscount = new BigDecimal(parameters.get(9));
        BigDecimal newCustomerDiscount = new BigDecimal(parameters.get(10));
        System.out.println(newCustomerDiscount);
        int id = Integer.valueOf(parameters.get(11));
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
        if (!(PriceListValidator.isPriceValueValid(newCustomerDiscount))) {
            logger.error("Invalid new customer discount input value!");
            throw new LogicException("Invalid new customer discount input value!");
        }
        try {
            Optional<PriceList> findedList = billDao.findById(id);
            if (!findedList.isPresent()) {
                logger.error("Price list not exists!");
            } else {
                PriceList changedList = findedList.get();
                changedList.setUnlockPrice(unlockPrice);
                changedList.setStayPrice(stayPrice);
                changedList.setDaySale(daySale);
                changedList.setNewCustomerDiscount(newCustomerDiscount);
                changedList.setNineHoursDiscount(nineHourDiscount);
                changedList.setThreeHoursDiscount(threeHourDiscount);
                changedList.setPerMinutePrice(perMinutePrice);
                changedList.setPerHourPrice(perHourPrice);
                changedList.setRegularCustomerDiscount(regularCustomerDiscount);
                changedList.setSixHoursDiscount(sixHourDiscount);
                changedList.setTravelerDiscount(travelerDiscount);
                billDao.update(changedList);
                logger.info("Price list for " + changedList.getBrand() + " was updated!");
            }
        } catch (DaoException ex) {
            throw new LogicException("Changing price list failed!", ex);
        }
    }
}
