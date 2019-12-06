package by.epam.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.epam.project.builder.RentalPointBuilder;
import by.epam.project.dao.RentalPointDao;
import by.epam.project.entity.point.RentalPoint;
import by.epam.project.exception.DaoException;
import by.epam.project.pool.ConnectionPool;

public class RentalPointDaoImpl extends RentalPointDao {

    private static final String FIND_ALL_RENTAL_POINTS = "SELECT * FROM rental_point";
    private static final String ADD_POINT_IN_DATABASE = "INSERT INTO rental_point(x_coordinate,y_coordinate) VALUES(?,?)";
    private static final String SEARCH_BY_COORDINATES = "SELECT * FROM rental_point WHERE x_coordinate = ? AND y_coordinate = ?";
    private static final String SEARCH_BY_ID = "SELECT * FROM rental_point WHERE Id = ?";
    private static final String FIND_ALL_RENTAL_POINTS_BY_LOCATION = "SELECT * FROM rental_point WHERE Location = ?";
    private static final String REMOVE_POINT = "DELETE FROM rental_point WHERE Id = ?";
    private static final String UPDATE_RENTAL_POINT = "UPDATE rental_point SET rental_point.x_coordinate = ?,"
            + "rental_point.y_coordinate = ? WHERE Id = ?";

    @Override
    public void createRentalPoint(int x_coordinate, int y_coordinate) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_POINT_IN_DATABASE)) {
            statement.setInt(1, x_coordinate);
            statement.setInt(2, y_coordinate);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public void updateRentalPoint(int x_coordinate, int y_coordinate, int id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_RENTAL_POINT)) {
            statement.setInt(1, x_coordinate);
            statement.setInt(2, y_coordinate);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public List<RentalPoint> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_ALL_RENTAL_POINTS)) {
            ResultSet result = statement.executeQuery();
            return RentalPointBuilder.createPoints(result);
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public Optional<RentalPoint> findById(int id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(SEARCH_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (!result.next()) {
                return Optional.empty();
            } else {
                RentalPoint point = RentalPointBuilder.createPoint(result);
                return Optional.ofNullable(point);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public RentalPoint findByCoordinates(int x, int y) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(SEARCH_BY_COORDINATES)) {
            statement.setInt(1, x);
            statement.setInt(2, y);
            ResultSet result = statement.executeQuery();
            return RentalPointBuilder.createPoint(result);
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(REMOVE_POINT)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }

    }

    @Override
    public List<RentalPoint> searchRentalPointsByLocation(String location) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_RENTAL_POINTS_BY_LOCATION);
            statement.setString(1, location);
            ResultSet result = statement.executeQuery();
            ArrayList<RentalPoint> points = RentalPointBuilder.createPoints(result);
            return points;
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }

    }

}
