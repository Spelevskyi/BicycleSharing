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

public class ChangeProfileLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(ChangeProfileLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();

    private User user;

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of changing user profile info performing.");
        if (parameters.size() != Constants.CHANGE_PROFILE_PARAMETERS_AMOUNT) {
            logger.error("Invalid changing profile parameters amount!");
            throw new LogicException("Invalid changing profile parameters amount!");
        }
        String firstName = parameters.get(0);
        String lastName = parameters.get(1);
        String phoneNumber = parameters.get(2);
        int userId = Integer.valueOf(parameters.get(3));
        if (!(UserDataValidator.isFirstNameValid(firstName))) {
            logger.error("Invalid firstname input value!");
            throw new LogicException("Invalid firstname input value!");
        }
        if (!(UserDataValidator.isLastNameValid(lastName))) {
            logger.error("Invalid lastname input value!");
            throw new LogicException("Invalid lastname input value!");
        }
        if (!(UserDataValidator.isPhoneNumberValid(phoneNumber))) {
            logger.error("Invalid phone number input value!");
            throw new LogicException("Invalid phone number input value!");
        }
        try {
            Optional<User> findedUser = userDao.findById(userId);
            if (!findedUser.isPresent()) {
                logger.error("User not exists!");
            } else {
                User changedUser = findedUser.get();
                changedUser.setFirstName(firstName);
                changedUser.setLastName(lastName);
                changedUser.setPhoneNumber(phoneNumber);
                userDao.update(changedUser);
                user = userDao.findById(userId).get();
                logger.info("Succesfully updating user profile info!");
            }
        } catch (DaoException ex) {
            throw new LogicException("Changing user profile info failed!", ex);
        }
    }

    public User getUser() {
        return user;
    }
}
