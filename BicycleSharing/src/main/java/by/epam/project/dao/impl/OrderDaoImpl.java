package by.epam.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.builder.RentalOrderBuilder;
import by.epam.project.dao.OrderDao;
import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.order.RentalOrder;
import by.epam.project.exception.DaoException;
import by.epam.project.pool.ConnectionPool;
import by.epam.project.pool.ProxyConnection;
import by.epam.project.util.Constants;

public class OrderDaoImpl extends OrderDao {

    private static final Logger logger = LogManager.getLogger(OrderDaoImpl.class);

    private static final String FIND_ALL_ORDERS = "SELECT * FROM rental_order";
    private static final String SQL_FIND_USER_ORDERS = "SELECT * FROM rental_order WHERE RenterId = ?";
    private static final String SQL_FIND_ORDER_WITH_BICYCLE = "SELECT * FROM rental_order INNER JOIN Bicycle ON rental_order.BicycleId = Bicycle.Id";
    private static final String FIND_BY_RENTER_ID = "SELECT * FROM rental_order WHERE rental_order.RenterId = ? AND rental_order.Status = ?";
    private static final String SQL_FIND_ACTIVE_ORDER = "SELECT * FROM rental_order WHERE RenterId = ? AND Status = 'ACTIVE'";
    private static final String ADD_ORDER_IN_DATABASE = "INSERT INTO rental_order(BicycleId,RenterId,BookedStartTime,BookedEndTime,"
            + "ActualStartTime,ActualEndTime,Status,Direction) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_ORDER_FOR_MOVE = "UPDATE rental_order SET rental_order.ActualStartTime = ?"
            + ",rental_order.Direction = (SELECT direction.Direction FROM direction WHERE direction.Id = ?) WHERE RenterId = ? AND Status = 'ACTIVE'";
    private static final String SQL_FULL_ORDER_INFO = "SELECT * FROM rental_order LEFT JOIN bicycle ON bicycle.Id = rental_order.BicycleId LEFT JOIN user ON user.Id = rental_order.RenterId "
            + "LEFT JOIN rental_point ON rental_point.Id = bicycle.PointId LEFT JOIN billing ON billing.Id = bicycle.BillingId WHERE rental_order.RenterId = ? AND rental_order.Status = 'ACTIVE'";
    private static final String UPDATE_ORDER_FOR_STOP = "UPDATE rental_order SET rental_order.ActualEndTime = ?"
            + " WHERE RenterId = ?";
    private static final String UPDATE_COVERED_DISTANCE = "UPDATE rental_order SET Distance = ?,Status = ?,ActualEndTime = ?,BookedEndTime = ?"
            + " WHERE RenterId = ? AND Status = 'ACTIVE'";
    private static final String SEARCH_BY_ID = "SELECT * FROM rental_order WHERE Id = ?";
    private static final String REMOVE_ORDER = "DELETE FROM rental_order WHERE Id = ?";
    private static final String GET_DIRECTION = "SELECT * FROM direction WHERE direction.Id = ?";
    private static final String REMOVE_USER_ORDERS = "DELETE FROM rental_order WHERE RenterId = ?";
    private static final String COUNT_ACTIVE_ORDERS = "SELECT COUNT(*) AS OrderCount FROM rental_order WHERE RenterId = ? AND Status = 'ACTIVE'";

    @Override
    public void create(RentalOrder entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(ADD_ORDER_IN_DATABASE);
            statement.setInt(1, entity.getBicycleId());
            statement.setInt(2, entity.getRenterId());
            statement.setString(3, entity.getBookedStartTime());
            statement.setString(4, entity.getBookedEndTime());
            statement.setString(5, entity.getActualStartTime());
            statement.setString(6, entity.getActualEndTime());
            statement.setString(7, entity.getStatus().toString());
            statement.setString(8, entity.getDirection());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }

    }

    @Override
    public Map<RentalOrder, Bicycle> findOrderWithBicycle() throws DaoException {
        logger.info("Finding orders with bicycle in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_ORDER_WITH_BICYCLE);
            result = statement.executeQuery();
            Map<RentalOrder, Bicycle> orders = RentalOrderBuilder.createOrderWithBicycle(result);
            return orders;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    @Override
    public void update(RentalOrder entity) throws DaoException {
        // TODO Auto-generated method stub

    }

    @Override
    public int countActiveOrders(int id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(COUNT_ACTIVE_ORDERS)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getInt("OrderCount");
        } catch (SQLException ex) {
            return 0;
        }
    }

    @Override
    public void deleteUserOrders(int id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(REMOVE_USER_ORDERS)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }

    }

    @Override
    public void addOrder(int bicycleId, int renterId, String bookedStartTime, String bookedEndTime,
            String actualStartTime, String actualEndTime, String status, String direction, int distance)
            throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_ORDER_IN_DATABASE)) {
            statement.setInt(1, bicycleId);
            statement.setInt(2, renterId);
            statement.setString(3, bookedStartTime);
            statement.setString(4, bookedEndTime);
            statement.setString(5, actualStartTime);
            statement.setString(6, actualEndTime);
            statement.setString(7, status);
            statement.setString(8, direction);
            statement.setInt(9, distance);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public List<RentalOrder> findUserOrders(int id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_USER_ORDERS);
            statement.setInt(1, id);
            result = statement.executeQuery();
            return RentalOrderBuilder.createOrders(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    // OrderDao method of founding active order for user with id as parameter
    @Override
    public Optional<RentalOrder> findActiveOrder(int id) throws DaoException {
        logger.info("Finding active order for current user inf dao!");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_ACTIVE_ORDER);
            statement.setInt(1, id);
            result = statement.executeQuery();
            return RentalOrderBuilder.createOrder(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    @Override
    public Optional<RentalOrder> findByRenterId(int id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FULL_ORDER_INFO);
            statement.setInt(1, id);
            result = statement.executeQuery();
            return RentalOrderBuilder.createOrderWithBicycleAndUser(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    @Override
    public String getMovingDirection(int id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_DIRECTION)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.beforeFirst();
            result.next();
            return result.getString(Constants.DIRECTION);
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    // OrderDao method for updating order after starting moving
    @Override
    public void updateOrderForMove(String time, int directionId, int id) throws DaoException {
        logger.info("Updating order after starting moving in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_ORDER_FOR_MOVE);
            statement.setString(1, time);
            statement.setInt(2, directionId);
            statement.setInt(3, id);
            int row = statement.executeUpdate();
            if (row == 0) {
                logger.error("Order was not updated!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public void updateOrderForStop(String time, int id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER_FOR_STOP)) {
            statement.setString(1, time);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public void updateCoveredDistance(int distance, String status, int id, String date) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_COVERED_DISTANCE)) {
            statement.setInt(1, distance);
            statement.setString(2, status);
            statement.setString(4, date);
            statement.setString(3, date);
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public List<RentalOrder> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_ALL_ORDERS)) {
            ResultSet result = statement.executeQuery();
            return RentalOrderBuilder.createOrders(result);
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public Optional<RentalOrder> findById(int id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(SEARCH_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (!result.next()) {
                return Optional.empty();
            } else {
                RentalOrder order = RentalOrderBuilder.createOrder(result);
                return Optional.ofNullable(order);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(REMOVE_ORDER)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }
}
