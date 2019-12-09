package by.epam.project.logic.admin;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.action.command.admin.UsersCommand;
import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;

public class UsersLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(UsersCommand.class);

    private UserDaoImpl userDao = new UserDaoImpl();

    private List<User> users;

    /**
     * Logic method of forwarding to users page
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of forwarding to users page perfoming.");
        if (parameters.size() != Constants.USERS_PARAMETERS_AMOUNT) {
            logger.error("Invalid users page parameters amount!");
            throw new LogicException("Invalid users page parameters amount!");
        }
        try {
            users = userDao.findAll();
            logger.info("All users where found!");
        } catch (DaoException ex) {
            throw new LogicException("Users page forwarding failed!", ex);
        }
    }

    public List<User> getUsers() {
        return users;
    }
}
