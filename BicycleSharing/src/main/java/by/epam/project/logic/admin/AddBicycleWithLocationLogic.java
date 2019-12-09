package by.epam.project.logic.admin;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.BicycleDaoImpl;
import by.epam.project.dao.impl.RentalPointDaoImpl;
import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.point.RentalPoint;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;
import by.epam.project.validation.BicycleDataValidator;

public class AddBicycleWithLocationLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(AddBicycleWithLocationLogic.class);

    public BicycleDaoImpl bicycleDao = new BicycleDaoImpl();
    public RentalPointDaoImpl pointDao = new RentalPointDaoImpl();

    /**
     * Logic method for adding bicycle with point coordinates on map
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Adding bicycle with location on map performing.");
        if (parameters.size() != Constants.ADD_BICYCLE_LOCATION_PARAMETERS_AMOUNT) {
            logger.error("Invalid adding bicycle with location parameters amount!");
            throw new LogicException("Invalid adding bicycle with location parameters amount!");
        }
        int bicycleId = Integer.valueOf(parameters.get(0));
        int xCoordinate = Integer.valueOf(parameters.get(1));
        int yCoordinate = Integer.valueOf(parameters.get(2));
        if (!(BicycleDataValidator.isCoordinateValid(xCoordinate))) {
            logger.error("Invalid X coordinate value!");
            throw new LogicException("Invalid X coordinate value!");
        }
        if (!(BicycleDataValidator.isCoordinateValid(yCoordinate))) {
            logger.error("Invalid Y coordinate value!");
            throw new LogicException("Invalid y coordinate value!");
        }
        try {
            Optional<Bicycle> findedBicycle = bicycleDao.findById(bicycleId);
            if (!findedBicycle.isPresent()) {
                logger.error("Bicycle not exists!");
            } else {
                RentalPoint point = new RentalPoint(xCoordinate, yCoordinate);
                point.setBicycleId(bicycleId);
                pointDao.create(point);
                logger.info("Creation rental point with bicycle adding on it!");
            }
        } catch (DaoException ex) {
            throw new LogicException("Adding bicycle with location on map failed!", ex);
        }
    }
}
