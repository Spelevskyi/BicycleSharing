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

public class ConfirmLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(ConfirmLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();

    private User user;

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of forwarding to confirm page executing.");
        if (parameters.size() != Constants.CONFIRM_PARAMETERS_AMOUNT) {
            logger.error("Invalid confirm page parameters amount!");
            throw new LogicException("Invalid confrim page parameters amount!");
        }
        String email = parameters.get(0);
        int enteredCode = Integer.valueOf(parameters.get(1));
        int confirmationCode = Integer.valueOf(parameters.get(2));
        try {
            Optional<User> findedUser = userDao.findUserByEmail(email);
            if (!findedUser.isPresent()) {
                logger.error("User not exists!");
            } else {
                if (!(enteredCode == confirmationCode)) {
                    logger.error("Entered code not equal to confirmation code!");
                    throw new LogicException("Entered code not equal to confirmation code!");
                }
                User changedUser = findedUser.get();
                changedUser.setConfirmed(true);
                userDao.update(changedUser);
                logger.info("Succesfully confirm user email!");
            }
        } catch (DaoException ex) {
            throw new LogicException(ex);
        }
    }

    public User getUser() {
        return user;
    }
}

