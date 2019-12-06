package by.epam.project.logic.common;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;
import by.epam.project.validation.UserDataValidator;

public class ForgotPasswordLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(ForgotPasswordLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Forgot password logic executing.");
        if (parameters.size() != Constants.FORGOT_PASSWORD_PARAMETERS_AMOUNT) {
            logger.error("Invalid forgot password parameters amount!");
            throw new LogicException("Invalid forgot password parameters amount!");
        }
        String email = parameters.get(0);
        String firstPassword = parameters.get(1);
        String secondPassword = parameters.get(2);
        if (!(UserDataValidator.isEmailValid(email))){
            logger.error("Invalid email input value!");
            throw new LogicException("Invalid email input value!");
        }
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
            if (!userDao.matchEmailPassword(email, firstPassword)) {
                logger.error("User not exists!");
                throw new DaoException("User not exists!");
            }
            User user = userDao.findUserByEmail(email).get();
            user.setPassword(firstPassword);
            userDao.update(user);
            logger.info("Succesfully changing password!");
        } catch (DaoException ex) {
            throw new LogicException(ex);
        }
    }
}

