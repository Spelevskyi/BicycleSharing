package by.epam.project.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.order.RentalOrder;
import by.epam.project.entity.point.RentalPoint;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;

public abstract class BicycleDao extends AbstractDao<Bicycle> {

    public abstract void addBicycleWithPoint(int id, int x_coordinate, int y_coordinate)
            throws DaoException;
    
    public abstract Map<Bicycle, RentalPoint> findBicyclesWithLocation() throws DaoException;

    public abstract List<Bicycle> findBicyclesNotLocated() throws DaoException;
    
    public abstract int getUnlockPrice(String brand) throws DaoException;

    public abstract Map<Integer, String> sortedBicycleByBrand(int id) throws DaoException;

    public abstract Map<Integer, String> sortedBicycleByColor(int id) throws DaoException;

    public abstract void enableBicycle(int id) throws DaoException;

    public abstract void disableBicycle(int id) throws DaoException;

    public abstract Optional<Bicycle> findOrderBicycle(int id) throws DaoException;

    public abstract void updateAfterMoving(RentalOrder order, Bicycle bicycle, RentalPoint point, User user)
            throws DaoException;
}
