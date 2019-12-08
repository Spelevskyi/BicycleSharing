package by.epam.project.logic.user;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.road.BicycleLocation;
import by.epam.project.util.Constants;

public class EndMoveLogic implements Logic{
    
    private static final Logger logger = LogManager.getLogger(EndMoveLogic.class);

    private UserDaoImpl userDao = new UserDaoImpl();

    private User user;

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of ending road performing.");
        if (parameters.size() != Constants.START_MOVE_PARAMETERS_AMOUNT) {
            logger.error("Invalid end moving parameters amount");
            throw new LogicException("Invalid end moving parameters amount");
        }
        int userId = Integer.valueOf(parameters.get(0));
        try {
            Optional<User> findedUser = userDao.findById(userId);
            if (!findedUser.isPresent()) {
                logger.error("User not exists!");
            } else {
                BicycleLocation.changeBicycleLocation(userId);
                user = userDao.findById(userId).get();
                logger.info("Succesfully ending road performing!");
            }
        } catch (DaoException ex) {
            throw new LogicException("Ending road failed!", ex);
        }
    }

    public User getUser() {
        return user;
    }
}



