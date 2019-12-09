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

public class ChangeAvatarLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(ChangeAvatarLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();

    private User user;

    /**
     * Logic method for changing user avatar
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of changing avatar image performing.");
        if (parameters.size() != Constants.CHANGE_AVATAR_PARAMETERS_AMOUNT) {
            logger.error("Invalid changing avatar parameters amount!");
            throw new LogicException("Invalid changing avatar parameters amount!");
        }
        String imagePath = parameters.get(0);
        int userId = Integer.valueOf(parameters.get(1));
        if (!(UserDataValidator.isImagePathValid(imagePath))) {
            logger.error("Invalid image path value!");
            throw new LogicException("Invalid image path value!");
        }
        try {
            Optional<User> findedUser = userDao.findById(userId);
            if (!findedUser.isPresent()) {
                logger.error("User not exists!");
            } else {
                user = findedUser.get();
                user.setImagePath(imagePath);
                userDao.update(user);
                user = userDao.findById(userId).get();
                logger.info("Succesfully image changing.");
            }
        } catch (DaoException ex) {
            throw new LogicException("User avatar image changing failed!", ex);
        }
    }

    public User getUser() {
        return user;
    }

}
