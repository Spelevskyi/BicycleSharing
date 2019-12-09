package by.epam.project.logic.admin;

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

public class LockUserLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(LockUserLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of locking user perfoming.");
        if (parameters.size() != Constants.LOCK_USER_PARAMETERS_AMOUNT) {
            logger.error("Invalid parameters amount for lock user!");
            throw new LogicException("Invalid parameters amount for lock user!");
        }
        int userId = Integer.valueOf(parameters.get(0));
        try {
            Optional<User> findedUser = userDao.findById(userId);
            if (!findedUser.isPresent()) {
                logger.error("User not exists!");
            } else {
                User user = userDao.findById(userId).get();
                user.setStatus(Constants.LOCKED);
                userDao.update(user);
                logger.info("Locking user succesfully!");
            }
        } catch (DaoException ex) {
            throw new LogicException("User locking failed!", ex);
        }
    }
}
