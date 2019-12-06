package by.epam.project.logic.admin;

import java.sql.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.BicycleDaoImpl;
import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.bicycle.BrandType;
import by.epam.project.entity.bicycle.ColorType;
import by.epam.project.entity.bicycle.StateType;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;
import by.epam.project.validation.BicycleDataValidator;

public class AddBicycleLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(AddBicycleLogic.class);

    public BicycleDaoImpl bicycleDao = new BicycleDaoImpl();

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of adding new bicycle performing.");
        if (parameters.size() != Constants.ADD_BICYCLE_PARAMETERS_AMOUNT) {
            logger.error("Invalid adding bicycle parameters amount!");
            throw new LogicException("Invalid adding bicycle parameters amount!");
        }
        String brand = parameters.get(0);
        String color = parameters.get(1);
        String state = parameters.get(2);
        String status = parameters.get(3);
        int speed = Integer.valueOf(parameters.get(4));
        String path = parameters.get(5);
        if (!(BicycleDataValidator.isBrandTypeValid(brand))) {
            logger.error("Invalid brand type!");
            throw new LogicException("Invalid brand type!");
        }
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
        if (!(BicycleDataValidator.isStatusTypeValid(status))) {
            logger.error("Invalid status value!");
            throw new LogicException("Invalid status value!");
        }
        try {
            Date registrationDate = new Date(System.currentTimeMillis());
            Bicycle bicycle = new Bicycle(BrandType.valueOf(brand), ColorType.valueOf(color), speed, registrationDate,
                    StateType.valueOf(state), path, status);
            bicycleDao.create(bicycle);
            logger.info("Creating bicycle parameter for inserting in database.");
        } catch (DaoException ex) {
            throw new LogicException("Adding new bicycle failed!", ex);
        }
    }
}
