package by.epam.project.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.order.RentalOrder;
import by.epam.project.exception.DaoException;

public abstract class OrderDao extends AbstractDao<RentalOrder> {

    public abstract void addOrder(int bicycleId, int renterId, String bookedStartTime, String bookedEndTime,
            String actualStartTime, String actualEndTime, String status, String direction, int distance)
            throws DaoException;

    public abstract Optional<RentalOrder> findActiveOrder(int id) throws DaoException;

    public abstract Optional<RentalOrder> findByRenterId(int id) throws DaoException;

    public abstract List<RentalOrder> findUserOrders(int id) throws DaoException;

    public abstract void deleteUserOrders(int id) throws DaoException;

    public abstract String getMovingDirection(int id) throws DaoException;

    public abstract void updateOrderForMove(String time, int directionId, int id) throws DaoException;

    public abstract void updateOrderForStop(String time, int id) throws DaoException;

    public abstract void updateCoveredDistance(int distance, String status, int id, String date) throws DaoException;

    public abstract int countActiveOrders(int id) throws DaoException;

    public abstract Map<RentalOrder, Bicycle> findOrderWithBicycle() throws DaoException;

}
