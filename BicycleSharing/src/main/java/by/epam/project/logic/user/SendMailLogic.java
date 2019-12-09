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
import by.epam.project.mail.GoogleMail;
import by.epam.project.util.ConfirmationCodeGenerator;
import by.epam.project.util.Constants;

public class SendMailLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(SendMailLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();

    private int confirmCode;

    /**
     * Logic method for sending mail message
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Sending code on user email performing.");
        if (parameters.size() != Constants.SEND_MAIL_PARAMETERS_AMOUNT) {
            logger.error("Invalid sending code on user email parameters amount!");
            throw new LogicException("Invalid sending code on user email parameters amount!");
        }
        int userId = Integer.valueOf(parameters.get(0));
        try {
            Optional<User> findedUser = userDao.findById(userId);
            if (!findedUser.isPresent()) {
                logger.error("User not exists!");
                throw new LogicException("User not exists!");
            } else {
                User user = findedUser.get();
                int generatedCode = ConfirmationCodeGenerator.generateCode();
                confirmCode = generatedCode;
                GoogleMail.INSTANCE.sendMessage(String.valueOf(generatedCode), user.getEmail());
                logger.info("Succesfully sending code on user email!");
            }
        } catch (DaoException ex) {
            throw new LogicException("Sending message failed!", ex);
        }
    }

    public int getConfirmCode() {
        return confirmCode;
    }
}
