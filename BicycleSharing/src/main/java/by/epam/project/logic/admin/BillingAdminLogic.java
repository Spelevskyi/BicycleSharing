package by.epam.project.logic.admin;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.BillingDaoImpl;
import by.epam.project.entity.billing.PriceList;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;

public class BillingAdminLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(BillingAdminLogic.class);

    private BillingDaoImpl billDao = new BillingDaoImpl();

    private List<PriceList> lists;

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of forwarding to admin billing page performing.");
        if (parameters.size() != Constants.BILLING_ADMIN_PARAMETERS_AMOUNT) {
            logger.error("Invalid billing page parameters amount!");
            throw new LogicException("Invalid billing page parameters amount!");
        }
        try {
            lists = billDao.findAll();
            logger.info("Forwarding to admin billing page was succesfull!");
        } catch (DaoException ex) {
            throw new LogicException("Admin page forwarding failed!", ex);
        }
    }

    public List<PriceList> getList() {
        return lists;
    }
}