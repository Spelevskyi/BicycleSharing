package by.epam.project.logic.user;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.OrderDaoImpl;
import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.road.DirectionIndex;
import by.epam.project.util.Constants;
import by.epam.project.util.CurrentDate;

public class StartMoveLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(StartMoveLogic.class);

    private OrderDaoImpl orderDao = new OrderDaoImpl();
    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of starting bicycle moving performing.");
        if (parameters.size() != Constants.START_MOVE_PARAMETERS_AMOUNT) {
            logger.error("Invalid start moving parameters amount");
            throw new LogicException("Invalid start moving parameters amount");
        }
        int userId = Integer.valueOf(parameters.get(0));
        try {
            String dateTime = CurrentDate.getCurrentDate();
            Optional<User> findedUser = userDao.findById(userId);
            if (!findedUser.isPresent()) {
                logger.error("User not exists!");
            } else {
                int index = DirectionIndex.generateDirectionIndex();
                orderDao.updateOrderForMove(dateTime, index, userId);
                logger.info("Succesfully starting road.");
            }
        } catch (DaoException ex) {
            throw new LogicException("Starting road failed!", ex);
        }
    }
}
