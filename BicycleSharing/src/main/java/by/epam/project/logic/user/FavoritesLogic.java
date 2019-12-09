package by.epam.project.logic.user;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.BicycleDaoImpl;
import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;

public class FavoritesLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(FavoritesLogic.class);

    private BicycleDaoImpl bicycleDao = new BicycleDaoImpl();
    private UserDaoImpl userDao = new UserDaoImpl();

    private User user;
    private Map<Integer, String> sortedByBrand;
    private Map<Integer, String> sortedByColor;

    /**
     * Logic method of forwarding to favorites bicycles page
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Favorites page forwarding perforning!");
        if (parameters.size() != Constants.FAVORITES_PARAMETERS_AMOUNT) {
            logger.error("Invalid favorites parameters amount!");
            throw new LogicException("Invalid favorites parameters amount!");
        }
        int userId = Integer.valueOf(parameters.get(0));
        try {
            Optional<User> findedUser = userDao.findById(userId);
            if (!findedUser.isPresent()) {
                logger.error("User not exists!");
            } else {
                sortedByBrand = bicycleDao.sortedBicycleByBrand(userId);
                sortedByColor = bicycleDao.sortedBicycleByColor(userId);
                user = userDao.findById(userId).get();
            }
            logger.info("Sucesfully favorites page redirect!");
        } catch (DaoException ex) {
            throw new LogicException("Forwarding to favorites page failed!", ex);
        }
    }

    public Map<Integer, String> getSortedByBrand() {
        return sortedByBrand;
    }

    public Map<Integer, String> getSortedByColor() {
        return sortedByColor;
    }

    public User getUser() {
        return user;
    }

}
