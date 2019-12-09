package by.epam.project.logic.common;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;


public class LogoutLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(LogoutLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();

    /**
     * Logic method for log out
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Logout performing!");
        if (parameters.size() != Constants.LOGOUT_PARAMETERS_AMOUNT) {
            logger.error("Invalid logout parameters amount!");
            throw new LogicException("Invalid logout parameters amount!");
        }
        int userId = Integer.valueOf(parameters.get(0));
        try {
            Optional<User> findedUser = userDao.findById(userId);
            if (!findedUser.isPresent()) {
                logger.error("User not exists!");
            } else {
                User user = findedUser.get();
                user.setOnline(false);
                userDao.update(user);
                logger.info("Succesfully logout performing!");
            }
        } catch (DaoException ex) {
            throw new LogicException("Logout failed!", ex);
        }
    }
}
