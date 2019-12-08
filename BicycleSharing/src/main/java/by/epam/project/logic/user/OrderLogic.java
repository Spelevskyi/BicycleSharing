package by.epam.project.logic.user;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.BicycleDaoImpl;
import by.epam.project.dao.impl.BillingDaoImpl;
import by.epam.project.dao.impl.OrderDaoImpl;
import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.billing.PriceList;
import by.epam.project.entity.order.OrderStatus;
import by.epam.project.entity.order.RentalOrder;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;
import by.epam.project.util.CurrentDate;

public class OrderLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(OrderLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();
    public OrderDaoImpl orderDao = new OrderDaoImpl();
    public BicycleDaoImpl bicycleDao = new BicycleDaoImpl();
    public BillingDaoImpl billingDao = new BillingDaoImpl();

    private User user;

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Ordering bicycle action performing.");
        if (parameters.size() != Constants.ORDER_PARAMETERS_AMOUNT) {
            logger.error("Invalid order parameters amount!");
            throw new LogicException("Invalid order parameters amount!");
        }
        int userId = Integer.valueOf(parameters.get(0));
        int bicycleId = Integer.valueOf(parameters.get(1));
        try {
            String dateTime = CurrentDate.getCurrentDate();
            Date date = new Date(System.currentTimeMillis());
            Optional<User> findedUser = userDao.findById(userId);
            if (!findedUser.isPresent()) {
                logger.error("User not exists!");
            }
            if (findedUser.get().isOnRoad()) {
                logger.error("You are in road already!");
                throw new LogicException("You are in road already!");
            }
            Optional<Bicycle> findedBicycle = bicycleDao.findById(bicycleId);
            if (!findedBicycle.isPresent()) {
                logger.error("Bicycle not exists!");
            } else {
                User changedUser = findedUser.get();
                Bicycle changedBicycle = findedBicycle.get();
                PriceList list = billingDao.findById(changedBicycle.getBillingId()).get();
                if (list.getUnlockPrice().doubleValue() > changedUser.getCash().doubleValue()) {
                    logger.error("You cash value not enough for ordering! Should be more than "
                            + list.getUnlockPrice().doubleValue() + " !");
                    throw new DaoException("You cash value not enough for ordering! Should be more than "
                            + list.getUnlockPrice().doubleValue() + " !");
                }
                RentalOrder order = new RentalOrder(bicycleId, userId, dateTime, new String(""), new String(""),
                        new String(""), OrderStatus.ACTIVE, new String(""), 0);
                orderDao.create(order);
                changedUser.setRentalAmount(changedUser.getRentalAmount() + 1);
                changedUser.setLastRentalDate(date);
                changedUser.setOnRoad(true);
                changedUser.setCash(changedUser.getCash().subtract(list.getUnlockPrice()));
                userDao.updateUserAfterOrder(changedUser, changedBicycle);
                user = userDao.findById(userId).get();
                logger.info("Succesfully ordering bicycle!");
            }
        } catch (DaoException ex) {
            System.out.println(ex.getMessage());
            throw new LogicException("Ordering bicycle failed!", ex);
        }
    }

    public User getUser() {
        return user;
    }
}
