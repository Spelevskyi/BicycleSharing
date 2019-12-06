package by.epam.project.logic.common;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.CardDaoImpl;
import by.epam.project.dao.impl.OrderDaoImpl;
import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;


public class LanguageLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(LanguageLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();
    public CardDaoImpl cardDao = new CardDaoImpl();
    public OrderDaoImpl orderDao = new OrderDaoImpl();

    private String lang;
    private User user;

    @Override
    public void action(List<String> parameters) throws LogicException {
        if (parameters.size() != Constants.LANGUAGE_PARAMETERS_AMOUNT) {
            logger.error("Invalid language change parameters amount!");
            throw new LogicException("Invalid language change parameters amount!");
        }
        String language = parameters.get(0);
        int userId = Integer.valueOf(parameters.get(1));
        if (!(userId != 0 || (language != Constants.ENGLISH_LANGUAGE && language != Constants.RUSSIAN_LANGUAGE))) {
            logger.error("Invalid input values!");
            throw new LogicException("Invalid input values!");
        }
        try {
            Optional<User> findedUser = userDao.findById(userId);
            if (!findedUser.isPresent()) {
                logger.error("User not exists!");
                throw new LogicException("User not exists!");
            } else {
                lang = language;
                ;
                user = findedUser.get();
            }
            logger.info("Succesfully language changing.");
        } catch (DaoException ex) {
            throw new LogicException(ex.getMessage());
        }
    }

    public String getLanguage() {
        return lang;
    }

    public User getUser() {
        return user;
    }

}
