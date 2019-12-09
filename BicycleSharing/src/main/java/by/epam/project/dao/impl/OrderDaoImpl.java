package by.epam.project.dao.impl;

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

public class OrderDaoImpl extends OrderDao {

    private static final Logger logger = LogManager.getLogger(OrderDaoImpl.class);

    private static final String SQL_FIND_ALL = "SELECT * FROM rental_order";
    private static final String SQL_FIND_ORDER_WITH_BICYCLE = "SELECT * FROM rental_order INNER JOIN Bicycle ON rental_order.BicycleId = Bicycle.Id";
    private static final String SQL_FIND_ACTIVE_ORDER = "SELECT * FROM rental_order WHERE RenterId = ? AND Status = 'ACTIVE'";
    private static final String SQL_CREATE_ORDER = "INSERT INTO rental_order(BicycleId,RenterId,BookedStartTime,BookedEndTime,"
            + "ActualStartTime,ActualEndTime,Status,Direction) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_ORDER_FOR_MOVE = "UPDATE rental_order SET rental_order.ActualStartTime = ?"
            + ",rental_order.Direction = (SELECT direction.Direction FROM direction WHERE direction.Id = ?) WHERE RenterId = ? AND Status = 'ACTIVE'";
    private static final String SQL_FULL_ORDER_INFO = "SELECT * FROM rental_order LEFT JOIN bicycle ON bicycle.Id = rental_order.BicycleId LEFT JOIN user ON user.Id = rental_order.RenterId "
            + "LEFT JOIN rental_point ON rental_point.BicycleId = bicycle.Id LEFT JOIN billing ON billing.Id = bicycle.BillingId WHERE rental_order.RenterId = ? AND rental_order.Status = 'ACTIVE'";
    private static final String SQL_SEARCH_BY_ID = "SELECT * FROM rental_order WHERE Id = ?";
    private static final String SQL_DELETE_ORDER = "DELETE FROM rental_order WHERE Id = ?";

    /**
     * OrderDao method for creating bicycle order
     */
    @Override
    public void create(RentalOrder entity) throws DaoException {
        logger.info("Creating bicycle order in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_CREATE_ORDER);
            statement.setInt(1, entity.getBicycleId());
            statement.setInt(2, entity.getRenterId());
            statement.setString(3, entity.getBookedStartTime());
            statement.setString(4, entity.getBookedEndTime());
            statement.setString(5, entity.getActualStartTime());
            statement.setString(6, entity.getActualEndTime());
            statement.setString(7, entity.getStatus().toString());
            statement.setString(8, entity.getDirection());
            int result = statement.executeUpdate();
            if (result == 0) {
                logger.error("Order was not created!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    /**
     * OrderDao method for finding order with bicycle
     */
    @Override
    public Map<RentalOrder, Bicycle> findOrderWithBicycle() throws DaoException {
        logger.info("Finding orders with bicycle in order dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_ORDER_WITH_BICYCLE);
            result = statement.executeQuery();
            return RentalOrderBuilder.createOrderWithBicycle(result);
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

    /**
     * OrderDao method of founding active order for user with id as parameter
     */
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

    /**
     * OrderDao method for finding order by renter id
     */
    @Override
    public Optional<RentalOrder> findByRenterId(int id) throws DaoException {
        logger.info("Finding order by renter id in dao.");
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

    /**
     * OrderDao method for updating order after starting moving
     */
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

    /**
     * OrderDao method for finding all orders
     */
    @Override
    public List<RentalOrder> findAll() throws DaoException {
        logger.info("Finding all orders in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet result = statement.executeQuery();
            return RentalOrderBuilder.createOrders(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    /**
     * OrderDao method for finding order by id
     */
    @Override
    public Optional<RentalOrder> findById(int id) throws DaoException {
        logger.info("Finding order by id in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_SEARCH_BY_ID);
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

    /**
     * OrderDao method for deleting order
     */
    @Override
    public void delete(int id) throws DaoException {
        logger.info("Deleting order by id in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_DELETE_ORDER);
            statement.setInt(1, id);
            int result = statement.executeUpdate();
            if (result == 0) {
                logger.error("Order was not deleted!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }
}
