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

public class DeleteBicycleLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(DeleteBicycleLogic.class);

    public BicycleDaoImpl bicycleDao = new BicycleDaoImpl();

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of deleting bicycle performing.");
        if (parameters.size() == 0) {
            logger.error("Invalid parameters amount for deleting bicycles!");
            throw new LogicException("Invalid parameters amount for deleting bicycles!");
        }
        try {
            for (String id : parameters) {
                int bicycleId = Integer.valueOf(id);
                Optional<Bicycle> findedBicycle = bicycleDao.findById(bicycleId);
                if (!findedBicycle.isPresent()) {
                    logger.error("Bicycle not exists!");
                }
                bicycleDao.delete(bicycleId);
            }
            logger.info("Deleting row from bicycle list.");
        } catch (DaoException ex) {
            throw new LogicException("Deleting bicycles failed!", ex);
        }
    }
}

