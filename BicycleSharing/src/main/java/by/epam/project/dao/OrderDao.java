package by.epam.project.dao;

import java.util.Map;
import java.util.Optional;

import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.order.RentalOrder;
import by.epam.project.exception.DaoException;

public abstract class OrderDao extends AbstractDao<RentalOrder> {

    public abstract Optional<RentalOrder> findActiveOrder(int id) throws DaoException;
    public abstract Optional<RentalOrder> findByRenterId(int id) throws DaoException;
    public abstract void updateOrderForMove(String time, int directionId, int id) throws DaoException;
    public abstract Map<RentalOrder, Bicycle> findOrderWithBicycle() throws DaoException;

}
