package by.epam.project.logic.user;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.OrderDaoImpl;
import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.order.RentalOrder;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;

public class UserHomeLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(UserHomeLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();
    public OrderDaoImpl orderDao = new OrderDaoImpl();

    private Map<RentalOrder, Bicycle> orders;
    private RentalOrder activeOrder;

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of forwarding to user home page performing.");
        if (parameters.size() != Constants.HOME_PARAMETERS_AMOUNT) {
            logger.error("Invalid user home page parameters amount!");
            throw new LogicException("Invalid user home page parameters amount!");
        }
        int userId = Integer.valueOf(parameters.get(0));
        try {
            Optional<RentalOrder> findedOrder = orderDao.findActiveOrder(userId);
            if (!findedOrder.isPresent()) {
                activeOrder = null;
            } else {
                activeOrder = findedOrder.get();
            }
            orders = orderDao.findOrderWithBicycle();
            logger.info("Succesfully user home page forwarding!");
        } catch (DaoException ex) {
            throw new LogicException(ex);
        }
    }

    public Map<RentalOrder, Bicycle> getOrders() {
        return orders;
    }

    public RentalOrder getActiveOrder() {
        return activeOrder;
    }
}
