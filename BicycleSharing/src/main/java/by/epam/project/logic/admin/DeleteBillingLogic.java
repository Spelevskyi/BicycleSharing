package by.epam.project.logic.admin;

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

public class DeleteBillingLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(ChangeBillingLogic.class);

    public BillingDaoImpl billDao = new BillingDaoImpl();

    /**
     * Logic method for deleting price lists
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of deleting price lists performing.");
        if (parameters.size() == Constants.ILLEGAL_DELETE_PARAMETERS_AMOUNT) {
            logger.error("Invalid parameters amount for deleting billing list!");
            throw new LogicException("Invalid parameters amount for deleting billing list!");
        }
        try {
            for (String id : parameters) {
                int billId = Integer.valueOf(id);
                Optional<PriceList> findedList = billDao.findById(billId);
                if (!findedList.isPresent()) {
                    logger.error("Price list not exists!");
                } else {
                    billDao.delete(billId);
                    logger.info("Deleting row from bicycle price list!");
                }
            }
        } catch (DaoException ex) {
            throw new LogicException("Deleting price lists failed!", ex);
        }
    }
}
