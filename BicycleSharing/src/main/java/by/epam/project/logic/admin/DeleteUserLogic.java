package by.epam.project.logic.admin;

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

public class DeleteUserLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(DeleteUserLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();

    /**
     * Logic method for deleting users
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of deleting users performing.");
        if (parameters.size() == Constants.ILLEGAL_DELETE_PARAMETERS_AMOUNT) {
            logger.error("Invalid parameters amount for deleting users!");
            throw new LogicException("Invalid parameters amount for deleting users!");
        }
        try {
            for (String id : parameters) {
                int userId = Integer.valueOf(id);
                Optional<User> findedUser = userDao.findById(userId);
                if (!findedUser.isPresent()) {
                    logger.error("User not exists!");
                } else {
                    userDao.delete(userId);
                    logger.info("Deleting users performing!");
                }
            }
        } catch (DaoException ex) {
            throw new LogicException("Deleting users failed!", ex);
        }
    }
}
