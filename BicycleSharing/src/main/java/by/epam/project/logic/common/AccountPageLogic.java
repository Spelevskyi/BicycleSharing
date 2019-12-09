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

public class AccountPageLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(AccountPageLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();

    private User user;

    /**
     * Logic method of forwarding to account page
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of forwarding to account page executing.");
        if (parameters.size() != Constants.ACCOUNT_PARAMETERS_AMOUNT) {
            logger.error("Invalid user account page parameters amount!");
            throw new LogicException("Invalid user account page parameters amount!");
        }
        int userId = Integer.valueOf(parameters.get(0));
        try {
            Optional<User> findedUser = userDao.findById(userId);
            if (!findedUser.isPresent()) {
                logger.error("User not exists!");
            } else {
                user = findedUser.get();
            }
            logger.info("Succesfully forwarding to user account page.");
        } catch (DaoException ex) {
            throw new LogicException("Forwarding to account page failed!", ex);
        }
    }

    public User getUser() {
        return user;
    }
}
