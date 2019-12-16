package by.epam.project.logic.admin;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.BicycleDaoImpl;
import by.epam.project.dao.impl.RentalPointDaoImpl;
import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;

public class DisableBicycleLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(DisableBicycleLogic.class);

    public BicycleDaoImpl bicycleDao = new BicycleDaoImpl();
    public RentalPointDaoImpl pointDao = new RentalPointDaoImpl();

    /**
     * Logic method for disabling bicycle
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of disabling bicycle performing.");
        if (parameters.size() == 0) {
            logger.error("Invalid parameters amount for disabling cards!");
            throw new LogicException("Invalid parameters amount for disabling cards!");
        }
        int bicycleId = Integer.valueOf(parameters.get(0));
        try {
            Optional<Bicycle> findedBicycle = bicycleDao.findById(bicycleId);
            if (!findedBicycle.isPresent()) {
                logger.error("Bicycle not exists!");
            } else {
                Bicycle bicycle = findedBicycle.get();
                if (bicycle.isOnRoad()) {
                    logger.error("Bicycle is already on road! You cannot disable it!");
                    throw new LogicException("Bicycle is already on road! You cannot disable it!");
                }
                bicycle.setStatus(Constants.DISABLE);
                bicycleDao.update(bicycle);
                pointDao.deleteByBicycleId(bicycleId);
                logger.info("Disable bicycle!");
            }
        } catch (DaoException ex) {
            throw new LogicException("Disabling bicycle failed!", ex);
        }
    }
}


