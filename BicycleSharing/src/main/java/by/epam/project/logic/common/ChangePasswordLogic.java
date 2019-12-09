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
import by.epam.project.validation.UserDataValidator;

public class ChangePasswordLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(ChangePasswordLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();

    /**
     * Logic method for changing password
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of changing password performing.");
        if (parameters.size() != Constants.CHANGE_PASSWORD_PARAMETERS_AMOUNT) {
            logger.error("Invalid changing password parameters amount!");
            throw new LogicException("Invalid changing password parameters amount!");
        }
        int userId = Integer.valueOf(parameters.get(0));
        String firstPassword = parameters.get(1);
        String secondPassword = parameters.get(2);
        if (!(UserDataValidator.isPasswordValid(firstPassword))) {
            logger.error("Invalid first password input value!");
            throw new LogicException("Invalid first password input value!");
        }
        if (!(UserDataValidator.isPasswordValid(secondPassword))) {
            logger.error("Invalid second password input value!");
            throw new LogicException("Invalid second password input value!");
        }
        if (!(UserDataValidator.isPasswordEqual(firstPassword, secondPassword))) {
            logger.error("Passwords are not equal!");
            throw new LogicException("Passwords are not equal!");
        }
        try {
            Optional<User> findedUser = userDao.findById(userId);
            if (!findedUser.isPresent()) {
                logger.error("User with current userId not exists!");
            }
            else {
                User user = findedUser.get();
                user.setPassword(firstPassword);
                userDao.update(user);
                logger.info("Succesfully performing action of changing password!");
            }
        } catch (DaoException ex) {
            throw new LogicException("Changing user password failed!", ex);
        }
    }
}

