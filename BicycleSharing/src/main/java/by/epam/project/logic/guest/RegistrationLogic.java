package by.epam.project.logic.guest;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.entity.user.RoleType;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.mail.GoogleMail;
import by.epam.project.util.ConfirmationCodeGenerator;
import by.epam.project.util.Constants;
import by.epam.project.validation.UserDataValidator;

public class RegistrationLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(RegistrationLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();

    private int confirmCode;

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Registration performing.");
        if (parameters.size() != Constants.REGISTRATION_PARAMETERS_AMOUNT) {
            logger.error("Invalid registration parameters amount!");
            throw new LogicException("Invalid registration parameters amount!");
        }
        String firstName = parameters.get(0);
        String lastName = parameters.get(1);
        String email = parameters.get(2);
        String firstPassword = parameters.get(3);
        String secondPassword = parameters.get(4);
        String phoneNumber = parameters.get(5);
        if (!(UserDataValidator.isEmailValid(email))) {
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
        if (!(UserDataValidator.isFirstNameValid(firstName))) {
            logger.error("Invalid first name input value!");
            throw new LogicException("Invalid first name input value!");
        }
        if (!(UserDataValidator.isLastNameValid(lastName))) {
            logger.error("Invalid last name input value!");
            throw new LogicException("Invalid last name input value!");
        }
        if (!(UserDataValidator.isPhoneNumberValid(phoneNumber))) {
            logger.error("Invalid phone number input value!");
            throw new LogicException("Invalid phone number input value!");
        }
        if (!(UserDataValidator.isPasswordEqual(firstPassword, secondPassword))) {
            logger.error("Password are not equal!");
            throw new LogicException("Passwords are not equal!");
        }
        try {
            if (userDao.matchEmailPassword(email, firstPassword)) {
                logger.error("User exests already!");
                throw new LogicException("User exists already!");
            } else {
                int generatedCode = ConfirmationCodeGenerator.generateCode();
                Date registrationDate = new Date(System.currentTimeMillis());
                User user = new User(firstName, lastName, email, firstPassword, RoleType.USER, registrationDate,
                        Constants.INITIAL_RENTAL_AMOUNT, registrationDate, phoneNumber, Constants.UNLOCKED, false,
                        Constants.INITIAL_IMAGE_PATH, new BigDecimal(0));
                userDao.create(user);
                confirmCode = generatedCode;
                GoogleMail.INSTANCE.sendMessage(String.valueOf(generatedCode), email);
                logger.info("Succesfully registration done.");
            }
        } catch (DaoException ex) {
            throw new LogicException("Registration failed!", ex);
        }
    }

    public int getConfirmCode() {
        return confirmCode;
    }
}

