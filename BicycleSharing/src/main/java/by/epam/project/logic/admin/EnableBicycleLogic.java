package by.epam.project.logic.admin;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.BicycleDaoImpl;
import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;

public class EnableBicycleLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(EnableBicycleLogic.class);

    public BicycleDaoImpl bicycleDao = new BicycleDaoImpl();

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of enabling bicycle performing.");
        if (parameters.size() == 0) {
            logger.error("Invalid parameters amount for enabling bicycle!");
            throw new LogicException("Invalid parameters amount for enabling bicycle!");
        }
        int bicycleId = Integer.valueOf(parameters.get(0));
        try {
            Optional<Bicycle> findedBicycle = bicycleDao.findById(bicycleId);
            if (!findedBicycle.isPresent()) {
                logger.error("Bicycle not exists!");
            } else {
                Bicycle bicycle = findedBicycle.get();
                bicycle.setStatus(Constants.ENABLE);
                bicycleDao.update(bicycle);
                logger.info("Enable bicycle performing!");
            }
        } catch (DaoException ex) {
            throw new LogicException("Enabling bicycle failed!", ex);
        }
    }
}
