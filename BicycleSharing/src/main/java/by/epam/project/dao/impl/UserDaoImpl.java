package by.epam.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.builder.UserBuilder;
import by.epam.project.dao.UserDao;
import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.card.Card;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.pool.ConnectionPool;
import by.epam.project.pool.ProxyConnection;

public class UserDaoImpl extends UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private static final String SQL_SEARCH_BY_ID = "SELECT * FROM user WHERE Id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM user WHERE Role = 'USER'";
    private static final String SQL_UPDATE_USER = "UPDATE user SET FirstName = ?, LastName = ?, Email = ?, Password = ?, RegistrationDate = ?,"
            + "RentalAmount = ?, LastRentalDate = ?, Role = ?, Status = ?, Cash = ?, Confirmed = ?, ImagePath = ?, PhoneNumber = ?, OnRoad = ?,"
            + "InSystem = ? WHERE user.Id = ?";
    private static final String SQL_USER_REPLENISH_CASH = "UPDATE user SET Cash = ? where Id = ?";
    private static final String SQL_CARD_WITHDRAW_BALANCE = "UPDATE credit_card SET Balance = ? WHERE Id = ?";
    private static final String SQL_CREATE_USER = "INSERT INTO user(FirstName,LastName,Email,Password,Role,"
            + "RegistrationDate,RentalAmount,LastRentalDate,PhoneNumber,Status,Confirmed,ImagePath,Cash,OnRoad,InSystem) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_DELETE_USER = "DELETE FROM user WHERE Id = ?";
    private static final String SQL_MATCH_EMAIL_PASSWORD = "SELECT * FROM user WHERE Email = ? AND Password = ?";
    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM user WHERE Email = ?";
    private static final String SQL_UPDATE_USER_AFTER_ORDER = "UPDATE user SET RentalAmount =?,LastRentalDate = ?,"
            + "Cash = ?, OnRoad = ? WHERE Id = ? ";
    private static final String SQL_UPDATE_ORDERED_BICYCLE = "UPDATE bicycle SET Status = 'DISABLE', OnRoad = ? WHERE Id = ?";

    /**
     * UserDao method for finding user by id
     */
    @Override
    public Optional<User> findById(int id) throws DaoException {
        logger.info("Finding user by id in user dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_SEARCH_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            return UserBuilder.createUser(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    /**
     * UserDao method for creating user
     */
    @Override
    public void create(User entity) throws DaoException {
        logger.info("Creating user in user dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_CREATE_USER);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPassword());
            statement.setString(5, entity.getRole().toString());
            statement.setDate(6, entity.getRegistrationDate());
            statement.setInt(7, entity.getRentalAmount());
            statement.setDate(8, entity.getLastRentalDate());
            statement.setString(9, entity.getPhoneNumber());
            statement.setString(10, entity.getStatus());
            statement.setBoolean(11, entity.isConfirmed());
            statement.setString(12, entity.getImagePath());
            statement.setBigDecimal(13, entity.getCash());
            statement.setBoolean(14, entity.isOnRoad());
            statement.setBoolean(15, entity.isOnline());
            int result = statement.executeUpdate();
            if (result == 0) {
                logger.error("User was not created!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    /**
     * UserDao method for updating current user
     */
    @Override
    public void update(User entity) throws DaoException {
        logger.info("Updating parameters in user dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPassword());
            statement.setDate(5, entity.getRegistrationDate());
            statement.setInt(6, entity.getRentalAmount());
            statement.setDate(7, entity.getLastRentalDate());
            statement.setString(8, entity.getRole().toString());
            statement.setString(9, entity.getStatus());
            statement.setBigDecimal(10, entity.getCash());
            statement.setBoolean(11, entity.isConfirmed());
            statement.setString(12, entity.getImagePath());
            statement.setString(13, entity.getPhoneNumber());
            statement.setBoolean(14, entity.isOnRoad());
            statement.setBoolean(15, entity.isOnline());
            statement.setInt(16, entity.getId());
            int result = statement.executeUpdate();
            if (result == 0) {
                logger.error("User was not updated!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    /**
     * UserDao method of founding all system users
     */
    @Override
    public List<User> findAll() throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL);
            result = statement.executeQuery();
            return UserBuilder.createUsers(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    /**
     * UserDao method for deleting user
     */
    @Override
    public void delete(int id) throws DaoException {
        logger.info("Deleting user in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_DELETE_USER);
            statement.setInt(1, id);
            int row = statement.executeUpdate();
            if (row == 0) {
                logger.error("User was not deleted!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    /**
     * UserDao method for matching existing user in database by email and password
     */
    @Override
    public boolean matchEmailPassword(String email, String password) throws DaoException {
        logger.info("Matching inputing email and password with user in database.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_MATCH_EMAIL_PASSWORD);
            statement.setString(1, email);
            statement.setString(2, password);
            result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    /**
     * UserDao method for finding existing user by email
     */
    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        logger.info("Finding existing user by email.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL);
            statement.setString(1, email);
            result = statement.executeQuery();
            return UserBuilder.createUser(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    /**
     * UserDao transaction method for replenishing user cash
     */
    @Override
    public void replenishCash(User user, Card card) throws DaoException {
        logger.info("Replenish user cash in user dao!");
        ProxyConnection connection = null;
        PreparedStatement userStatement = null;
        PreparedStatement cardStatement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            userStatement = connection.prepareStatement(SQL_USER_REPLENISH_CASH);
            userStatement.setBigDecimal(1, user.getCash());
            userStatement.setInt(2, user.getId());
            int result = userStatement.executeUpdate();
            if (result == 0) {
                logger.error("User cash replenishing failed!");
            }
            cardStatement = connection.prepareStatement(SQL_CARD_WITHDRAW_BALANCE);
            cardStatement.setBigDecimal(1, card.getBalance());
            cardStatement.setInt(2, card.getId());
            result = cardStatement.executeUpdate();
            if (result == 0) {
                logger.error("Card balance withdrawing failed!");
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
            close(userStatement);
            close(cardStatement);
            close(connection);
        }
    }

    /**
     * UserDao transaction method for updating user after ordering bicycle
     */
    @Override
    public void updateUserAfterOrder(User user, Bicycle bicycle) throws DaoException {
        logger.info("Updating user and bicycle after ordering in dao.");
        Connection connection = null;
        PreparedStatement userStatement = null;
        PreparedStatement bicycleStatement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            userStatement = connection.prepareStatement(SQL_UPDATE_USER_AFTER_ORDER);
            connection.setAutoCommit(false);
            userStatement.setInt(1, user.getRentalAmount());
            userStatement.setDate(2, user.getLastRentalDate());
            userStatement.setBigDecimal(3, user.getCash());
            userStatement.setBoolean(4, user.isOnRoad());
            userStatement.setInt(5, user.getId());
            int result = userStatement.executeUpdate();
            if (result == 0) {
                logger.info("User was not updated!");
            }
            bicycleStatement = connection.prepareStatement(SQL_UPDATE_ORDERED_BICYCLE);
            bicycleStatement.setBoolean(1, bicycle.isOnRoad());
            bicycleStatement.setInt(2, bicycle.getId());
            result = bicycleStatement.executeUpdate();
            if (result == 0) {
                logger.info("Bicycle was not updated!");
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
            close(userStatement);
            close(bicycleStatement);
            close(connection);
        }
    }
}
