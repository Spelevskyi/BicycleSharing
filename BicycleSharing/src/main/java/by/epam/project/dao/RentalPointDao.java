package by.epam.project.dao;

import java.util.List;

import by.epam.project.entity.point.RentalPoint;
import by.epam.project.exception.DaoException;

public abstract class RentalPointDao extends AbstractDao<RentalPoint> {

    public abstract List<RentalPoint> searchRentalPointsByLocation(String location) throws DaoException;

    public abstract void updateRentalPoint(int x_coordinate, int y_coordinate, int id) throws DaoException;

    public abstract RentalPoint findByCoordinates(int x_coordinates, int y_coordinates) throws DaoException;
}
