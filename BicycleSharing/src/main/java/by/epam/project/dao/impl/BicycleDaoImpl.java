package by.epam.project.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.builder.BicycleBuilder;
import by.epam.project.dao.BicycleDao;
import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.order.RentalOrder;
import by.epam.project.entity.point.RentalPoint;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.pool.ConnectionPool;
import by.epam.project.pool.ProxyConnection;

public class BicycleDaoImpl extends BicycleDao {

    private static final Logger logger = LogManager.getLogger(BicycleDaoImpl.class);

    private static final String SQL_CREATE_BICYCLE = "INSERT INTO bicycle (Brand,Color,MaxSpeed,CreationDate,State,ImagePath,Status,BillingId) VALUES"
            + "(?,?,?,?,?,?,?,(SELECT billing.Id FROM billing WHERE billing.Brand = ?))";
    private static final String SQL_UPDATE_BICYCLE = "UPDATE bicycle SET Brand = ?,Color = ?,MaxSpeed = ? ,CreationDate = ? ,State = ? ,ImagePath = ? ,Status = ?, BillingId = ?"
            + " WHERE Id = ?";
    private static final String SQL_SORT_BY_BRAND = "SELECT bicycle.Brand,COUNT(bicycle.Brand) AS BicycleCount FROM bicycle JOIN rental_order ON rental_order.bicycleId = bicycle.Id WHERE rental_order.RenterId = ?"
            + " GROUP BY bicycle.Brand ORDER BY BicycleCount DESC";
    private static final String SQL_SORT_BY_COLOR = "SELECT bicycle.Color,COUNT(bicycle.Color) AS BicycleCount FROM bicycle JOIN rental_order ON rental_order.bicycleId = bicycle.Id WHERE rental_order.RenterId = ?"
            + " GROUP BY bicycle.Color ORDER BY BicycleCount DESC";
    private static final String SQL_UPDATE_ORDER_AFTER_MOVE = "UPDATE rental_order SET Distance = ?, Status = ?, ActualEndTime = ?, BookedEndTime = ? WHERE Id = ?";
    private static final String SQL_UPDATE_POINT_AFTER_MOVE = "UPDATE rental_point SET x_coordinate  = ?, y_coordinate = ? WHERE Id = ?";
    private static final String SQL_UPDATE_USER_AFTER_MOVE = "UPDATE user SET Cash = ?,Status = ? WHERE Id = ?";
    private static final String SQL_UPDATE_BICYCLE_AFTER_MOVE = "UPDATE bicycle SET Status = ? WHERE Id = ?";
    private static final String SQL_UPDATE_POINT_ID = "UPDATE bicycle SET PointId = (SELECT Id FROM rental_point WHERE x_coordinate = ? AND y_coordinate = ?) WHERE Id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM bicycle";
    private static final String SQL_FIND_BICYCLES_WITH_LOCATION = "SELECT * FROM bicycle INNER JOIN rental_point"
            + " ON bicycle.PointId = rental_point.Id  INNER JOIN billing ON bicycle.BillingId = billing.Id WHERE bicycle.Status = 'ENABLE'";
    private static final String SQL_FIND_BICYCLES_WITH_POINTS = "SELECT * FROM bicycle LEFT JOIN rental_point"
            + " ON bicycle.PointId = rental_point.Id INNER JOIN billing ON bicycle.BillingId = billing.Id ";
    private static final String FIND_ALL_BICYCLES_NOT_LOCATED = "SELECT * FROM bicycle WHERE bicycle.PointId = 'null'";
    private static final String SQL_ADD_POINT = "INSERT INTO rental_point(x_coordinate,y_coordinate) VALUES(?,?)";
    private static final String SQL_SEARCH_BY_ID = "SELECT * FROM bicycle WHERE Id = ?";
    private static final String SQL_DELETE_BICYCLE = "DELETE FROM bicycle WHERE Id = ?";
    private static final String FIND_BICYCLE_BY_ORDER_ID = "SELECT * FROM bicycle JOIN rental_order ON bicycle.Id = rental_order.BicycleId WHERE rental_order.RenterId = (SELECT user.Id FROM user WHERE user.Id = ?)";
    private static final String SQL_DELETE_POINT = "DELETE FROM rental_point WHERE rental_point.Id = (SELECT bicycle.PointId FROM bicycle WHERE bicycle.Id = ?)";

    // BicycleDao method for creation bicycle
    @Override
    public void create(Bicycle entity) throws DaoException {
        logger.info("Creation bicycle in bicycle dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_CREATE_BICYCLE);
            statement.setString(1, entity.getBrand().toString());
            statement.setString(2, entity.getColor().toString());
            statement.setInt(3, entity.getSpeed());
            statement.setDate(4, entity.getDate());
            statement.setString(5, entity.getState().toString());
            statement.setString(6, entity.getImagePath());
            statement.setString(7, entity.getStatus());
            statement.setString(8, entity.getBrand().toString());
            int result = statement.executeUpdate();
            if (result == 0) {
                logger.error("Bicycle was not added!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    // BicycleDao method for sorting favorites bicycles by brand
    @Override
    public Map<Integer, String> sortedBicycleByBrand(int id) throws DaoException {
        logger.info("Sorting by brand in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_SORT_BY_BRAND);
            statement.setInt(1, id);
            result = statement.executeQuery();
            Map<Integer, String> favorites = BicycleBuilder.createFavoritesByBrand(result);
            return favorites;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    // BicycleDao method for sorting favorites bicycles by color
    @Override
    public Map<Integer, String> sortedBicycleByColor(int id) throws DaoException {
        logger.info("Sorting by color in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_SORT_BY_COLOR);
            statement.setInt(1, id);
            result = statement.executeQuery();
            Map<Integer, String> favorites = BicycleBuilder.createFavoritesByColor(result);
            return favorites;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    @Override
    public void updateAfterMoving(RentalOrder order, Bicycle bicycle, RentalPoint point, User user)
            throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement orderStatement = null;
        PreparedStatement pointStatement = null;
        PreparedStatement bicycleStatement = null;
        PreparedStatement userStatement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            orderStatement = connection.prepareStatement(SQL_UPDATE_ORDER_AFTER_MOVE);
            pointStatement = connection.prepareStatement(SQL_UPDATE_POINT_AFTER_MOVE);
            bicycleStatement = connection.prepareStatement(SQL_UPDATE_BICYCLE_AFTER_MOVE);
            userStatement = connection.prepareStatement(SQL_UPDATE_USER_AFTER_MOVE);
            orderStatement.setDouble(1, order.getDistance());
            orderStatement.setString(2, order.getStatus().toString());
            orderStatement.setString(3, order.getActualEndTime());
            orderStatement.setString(4, order.getBookedEndTime());
            orderStatement.setInt(5, order.getId());
            orderStatement.executeUpdate();
            pointStatement.setInt(1, point.getX_coordinate());
            pointStatement.setInt(2, point.getY_coordinate());
            pointStatement.setInt(3, point.getId());
            pointStatement.executeUpdate();
            bicycleStatement.setString(1, bicycle.getStatus());
            bicycleStatement.setInt(2, bicycle.getId());
            bicycleStatement.executeUpdate();
            userStatement.setBigDecimal(1, user.getCash());
            userStatement.setString(2, user.getStatus());
            userStatement.setInt(3, user.getId());
            userStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.error("Transaction rollback failed!");
            }
            throw new DaoException(ex);
        } finally {
            close(orderStatement);
            close(pointStatement);
            close(bicycleStatement);
            close(userStatement);
            close(connection);
        }

    }

    @Override
    public Optional<Bicycle> findOrderBicycle(int id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(FIND_BICYCLE_BY_ORDER_ID);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            Optional<Bicycle> bicycle = BicycleBuilder.createBicycle(result);
            return bicycle;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }


    // BicycleDao method for finding all bicycles
    @Override
    public List<Bicycle> findAll() throws DaoException {
        logger.info("Finding all bicycles from dao");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL);
            result = statement.executeQuery();
            return BicycleBuilder.createBicycles(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    // BicycleDao method of finding located bicycles
    @Override
    public Map<Bicycle, RentalPoint> findBicyclesWithPoints() throws DaoException {
        logger.info("Founding bicycles with location in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_BICYCLES_WITH_POINTS);
            result = statement.executeQuery();
            return BicycleBuilder.createBicyclesWithLocation(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    // BicycleDao method of finding located bicycles
    @Override
    public Map<Bicycle, RentalPoint> findBicyclesWithLocation() throws DaoException {
        logger.info("Founding bicycles with location in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_BICYCLES_WITH_LOCATION);
            result = statement.executeQuery();
            return BicycleBuilder.createBicyclesWithLocation(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    @Override
    public List<Bicycle> findBicyclesNotLocated() throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(FIND_ALL_BICYCLES_NOT_LOCATED);
            ResultSet result = statement.executeQuery();
            ArrayList<Bicycle> bicycles = BicycleBuilder.createBicycles(result);
            return bicycles;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    // BicycleDao method for updating bicycle
    @Override
    public void update(Bicycle entity) throws DaoException {
        logger.info("Update bicycle in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_BICYCLE);
            statement.setString(1, entity.getBrand().toString());
            statement.setString(2, entity.getColor().toString());
            statement.setInt(3, entity.getSpeed());
            statement.setDate(4, entity.getDate());
            statement.setString(5, entity.getState().toString());
            statement.setString(6, entity.getImagePath());
            statement.setString(7, entity.getStatus());
            statement.setInt(8, entity.getBillingId());
            statement.setInt(9, entity.getId());
            int result = statement.executeUpdate();
            if (result == 0) {
                logger.error("Bicycle was not updated!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }

    }

    // BicycleDao transaction method for adding bicycle with point on map
    @Override
    public void addBicycleWithPoint(int id, int x_coordinate, int y_coordinate) throws DaoException {
        logger.info("Adding bicycle with rental point on map in bicycle dao.");
        ProxyConnection connection = null;
        PreparedStatement pointStatement = null;
        PreparedStatement bicycleStatement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            pointStatement = connection.prepareStatement(SQL_ADD_POINT);
            pointStatement.setInt(1, x_coordinate);
            pointStatement.setInt(2, y_coordinate);
            int row = pointStatement.executeUpdate();
            if (row == 0) {
                logger.error("Point was not added!");
            }
            bicycleStatement = connection.prepareStatement(SQL_UPDATE_POINT_ID);
            bicycleStatement.setInt(1, x_coordinate);
            bicycleStatement.setInt(2, y_coordinate);
            bicycleStatement.setInt(3, id);
            row = bicycleStatement.executeUpdate();
            if (row == 0) {
                logger.error("Bicycle was not added!");
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.error("Transaction rollback failed!");
            }
            throw new DaoException(ex);
        } finally {
            close(pointStatement);
            close(bicycleStatement);
            close(connection);
        }
    }

    // BicycleDao method for finding bicycle by id
    @Override
    public Optional<Bicycle> findById(int id) throws DaoException {
        logger.info("Finding bicycle by id in bicycle dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_SEARCH_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            Optional<Bicycle> bicycle = BicycleBuilder.createBicycle(result);
            return bicycle;
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    // BicycleDao method for deleting bicycles
    @Override
    public void delete(int id) throws DaoException {
        logger.info("Deleting bicycles in dao.");
        ProxyConnection connection = null;
        PreparedStatement bicycleStatement = null;
        PreparedStatement pointStatement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            pointStatement = connection.prepareStatement(SQL_DELETE_POINT);
            pointStatement.setInt(1, id);
            int result = pointStatement.executeUpdate();
            if (result == 0) {
                logger.error("Point was not deleted!");
            }
            bicycleStatement = connection.prepareStatement(SQL_DELETE_BICYCLE);
            bicycleStatement.setInt(1, id);
            result = bicycleStatement.executeUpdate();
            if (result == 0) {
                logger.error("Bicycle was not deleted!");
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.error("Transaction rollback failed!");
            }
            throw new DaoException(ex);
        } finally {
            close(pointStatement);
            close(bicycleStatement);
            close(connection);
        }
    }
}
