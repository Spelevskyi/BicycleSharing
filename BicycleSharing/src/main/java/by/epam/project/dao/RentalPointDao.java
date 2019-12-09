package by.epam.project.dao;

import by.epam.project.entity.point.RentalPoint;
import by.epam.project.exception.DaoException;

public abstract class RentalPointDao extends AbstractDao<RentalPoint> {

    public abstract void deleteByBicycleId(int bicycleId) throws DaoException;
}
