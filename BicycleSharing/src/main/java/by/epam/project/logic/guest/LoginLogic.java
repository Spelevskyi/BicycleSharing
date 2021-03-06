package by.epam.project.logic.guest;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.encoding.PasswordEncoding;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;
import by.epam.project.validation.UserDataValidator;

public class LoginLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(LoginLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();

    private User user;

    /**
     * Logic method for log in guest
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Login logic executing.");
        if (parameters.size() != Constants.LOGIN_PARAMETERS_AMOUNT) {
            logger.error("Invalid login parameters amount!");
            throw new LogicException("Invalid login parameters amount!");
        }
        String email = parameters.get(0);
        String password = parameters.get(1);
        if (!(UserDataValidator.isEmailValid(email))) {
            logger.error("Invalid email input value!");
            throw new LogicException("Invalid email input value!");
        }
        if (!(UserDataValidator.isPasswordValid(password))) {
            logger.error("Invalid password input value!");
            throw new LogicException("Invalid password input value!");
        }
        try {
            String encodePassword = new String(password.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            if (!userDao.matchEmailPassword(email, PasswordEncoding.encode(encodePassword))) {
                logger.error("User not exists!");
                throw new LogicException("User not exists!");
            } else {
                user = userDao.findUserByEmail(email).get();
                user.setOnline(true);
                userDao.update(user);
                logger.info("Succesfully sign in system.");
            }
        } catch (DaoException ex) {
            throw new LogicException("Log in failed!", ex);
        }
    }

    public User getUser() {
        return user;
    }
}
