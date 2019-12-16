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
import by.epam.project.validation.BicycleDataValidator;

public class ChangeBicycleLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(ChangeBicycleLogic.class);

    public BicycleDaoImpl bicycleDao = new BicycleDaoImpl();

    /**
     * Adding new bicycle logic method
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of changing bicycle performing.");
        if (parameters.size() != Constants.CHANGE_BICYCLE_PARAMETERS_AMOUNT) {
            logger.error("Invalid changing bicycle parameters amount!");
            throw new LogicException("Invalid changing bicycle parameters amount!");
        }
        String color = parameters.get(0);
        String state = parameters.get(1);
        int speed = Integer.valueOf(parameters.get(2));
        String path = parameters.get(3);
        int bicycleId = Integer.valueOf(parameters.get(4));
        if (!(BicycleDataValidator.isColorTypeValid(color))) {
            logger.error("Invalid color type!");
            throw new LogicException("Invalid color type!");
        }
        if (!(BicycleDataValidator.isSpeedValueValid(speed))) {
            logger.error("Invalid max speed value!");
            throw new LogicException("Invalid max speed value!");
        }
        if (!(BicycleDataValidator.isStateTypeValid(state))) {
            logger.error("Invalid bicycle state value!");
            throw new LogicException("Invalid bicycle state value!");
        }
        if (!(BicycleDataValidator.isImagePathValid(path))) {
            logger.error("Invalid bicycle image path!");
            throw new LogicException("Invalid bicycle image path!");
        }
        try {
            Optional<Bicycle> findedBicycle = bicycleDao.findById(bicycleId);
            if (!findedBicycle.isPresent()) {
                logger.error("Bicycle not exists!");
            } else {
                Bicycle bicycle = findedBicycle.get();
                if (bicycle.isOnRoad()) {
                    logger.error("Bicycle is already on road! You cannot modify it!");
                    throw new LogicException("Bicycle is already on road! You cannot modify it!");
                }
                bicycle.setColor(color);
                bicycle.setState(state);
                bicycle.setSpeed(speed);
                bicycle.setImagePath(path);
                bicycleDao.update(bicycle);
                logger.info("Changing existing bicycle performing!");
            }
        } catch (DaoException ex) {
            throw new LogicException("Changing bicycle failed!", ex);
        }
    }
}
