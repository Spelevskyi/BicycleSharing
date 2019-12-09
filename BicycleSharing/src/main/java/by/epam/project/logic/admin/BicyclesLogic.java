package by.epam.project.logic.admin;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.BicycleDaoImpl;
import by.epam.project.dao.impl.BillingDaoImpl;
import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.billing.PriceList;
import by.epam.project.entity.point.RentalPoint;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;

public class BicyclesLogic implements Logic {

    public static final Logger logger = LogManager.getLogger(BicyclesLogic.class);

    private BillingDaoImpl billDao = new BillingDaoImpl();
    private BicycleDaoImpl bicycleDao = new BicycleDaoImpl();

    private List<PriceList> lists;
    private Map<Bicycle, RentalPoint> bicycles;

    /**
     * Logic method of forwarding to bicycles page
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of bicycles page forwarding performing.");
        if (parameters.size() != Constants.BICYCLES_PARAMETERS_AMOUNT) {
            logger.error("Invalid bicycles page parameters amount!");
            throw new LogicException("Invalid bicycles page parameters amount!");
        }
        try {
            lists = billDao.findAll();
            bicycles = bicycleDao.findBicyclesWithPoints();
            logger.info("Succesfully bicycles page forwarding!");
        } catch (DaoException ex) {
            throw new LogicException("Bicycles page forwarding failed!", ex);
        }
    }

    public Map<Bicycle, RentalPoint> getBicycles() {
        return bicycles;
    }

    public List<PriceList> getLists() {
        return lists;
    }
}
