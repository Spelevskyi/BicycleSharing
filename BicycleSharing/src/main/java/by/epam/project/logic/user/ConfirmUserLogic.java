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
import by.epam.project.util.Constants;
import by.epam.project.validation.UserDataValidator;

public class ConfirmUserLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(ConfirmUserLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();

    private User user;

    /**
     * Logic method for confirming user
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of forwarding to confirm page executing.");
        if (parameters.size() != Constants.CONFIRM_PARAMETERS_AMOUNT) {
            logger.error("Invalid confirm page parameters amount!");
            throw new LogicException("Invalid confrim page parameters amount!");
        }
        int userId = Integer.valueOf(parameters.get(0));
        String enteredCode = parameters.get(1);
        String confirmationCode = parameters.get(2);
        if (!(UserDataValidator.isConfirmCodeValid((enteredCode)))) {
            logger.error("Invalid entered code value!");
            throw new LogicException("Invalid entered code value!");
        }
        if (!(UserDataValidator.isConfirmCodeValid(confirmationCode))) {
            logger.error("Invalid confirm code value!");
            throw new LogicException("Invalid confirm code value!");
        }
        try {
            Optional<User> findedUser = userDao.findById(userId);
            if (!findedUser.isPresent()) {
                logger.error("User not exists!");
            } else {
                if (!(enteredCode.equals(confirmationCode))) {
                    logger.error("Entered code not equal to confirmation code!");
                    throw new LogicException("Entered code not equal to confirmation code!");
                }
                User changedUser = findedUser.get();
                changedUser.setConfirmed(true);
                userDao.update(changedUser);
                user = userDao.findById(userId).get();
                logger.info("Succesfully confirm user email!");
            }
        } catch (DaoException ex) {
            throw new LogicException("Confirming user failed!", ex);
        }
    }

    public User getUser() {
        return user;
    }
}

