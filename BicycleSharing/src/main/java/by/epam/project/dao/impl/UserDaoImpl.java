package by.epam.project.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.builder.UserBuilder;
import by.epam.project.dao.UserDao;
import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.card.Card;
import by.epam.project.entity.user.RoleType;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.pool.ConnectionPool;
import by.epam.project.pool.ProxyConnection;

public class UserDaoImpl extends UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private static final String SQL_SEARCH_BY_ID = "SELECT * FROM user WHERE Id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM user WHERE Role = 'USER'";
    private static final String SQL_UPDATE_USER = "UPDATE user SET FirstName = ?, LastName = ?, Email = ?, Password = ?, RegistrationDate = ?,"
            + "RentalAmount = ?, LastRentalDate = ?, Role = ?, Status = ?, Cash = ?, Confirmed = ?, ImagePath = ?, PhoneNumber = ? WHERE user.Id = ?";
    private static final String SQL_USER_REPLENISH_CASH = "UPDATE user SET Cash = ? where Id = ?";
    private static final String SQL_CARD_WITHDRAW_BALANCE = "UPDATE credit_card SET Balance = ? WHERE Id = ?";

    private static final String FIND_ALL_USERS = "SELECT * FROM user WHERE Status = ?";
    private static final String FIND_ALL_UNLOCKED_USERS = "SELECT * FROM user WHERE Status = ?";
    private static final String ADD_USER_IN_DATABASE = "INSERT INTO user(FirstName,LastName,Email,Password,Role,"
            + "RegistrationDate,RentalAmount,LastRentalDate,PhoneNumber,Status,Confirmed,ImagePath) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_USER_IN_DATABASE = "UPDATE user SET FirstName = ?,LastName = ?,"
            + "PhoneNumber = ? WHERE user.Id = ?";
    private static final String LOCK_USER = "UPDATE user SET user.Status = 'LOCK' where user.Id = ?";
    private static final String UNLOCK_USER = "UPDATE user SET user.Status = 'UNLOCK' where user.Id = ?";
    private static final String SQL_CONFIRM_USER = "UPDATE user SET user.Confirmed = true where user.Email = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM user WHERE Id = ?";
    private static final String SQL_MATCH_EMAIL_PASSWORD = "SELECT * FROM user WHERE Email = ? AND Password = ?";
    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM user WHERE Email = ?";
    private static final String CHANGE_IMAGE_PATH = "UPDATE user SET ImagePath = ? where Id = ?";
    private static final String SQL_UPDATE_USER_AFTER_ORDER = "UPDATE user SET RentalAmount =?,LastRentalDate = ?,"
            + "Cash = ? WHERE Id = ? ";
    private static final String SQL_FIND_USER_WITH_CARDS = "SELECT * FROM user LEFT JOIN credit_card ON credit_card.OwnerId = user.Id WHERE user.Id = ?";
    private static final String FIND_USER_WITH_ACTIVE_ORDER = "SELECT * FROM user LEFT JOIN rental_order ON rental_order.RenterId = user.Id WHERE user.Id = ? "
            + "AND rental_order.Status = 'ACTIVE'";
    private static final String SQL_UPDATE_ORDERED_BICYCLE = "UPDATE bicycle SET Status = 'DISABLE' WHERE Id = ?";
    private static final String SQL_FIND_USER_WITH_ORDERS = "SELECT * FROM user LEFT JOIN rental_order ON rental_order.RenterId = user.Id WHERE user.Id = ?";
    private static final String FIND_USER_WITH_CARDS_AND_ORDERS = "SELECT * FROM user JOIN credit_card ON credit_card.OwnerId = user.Id JOIN rental_order"
            + " ON rental_order.RenterId = user.Id WHERE user.Id = ?";

    // UserDao method for finding user by id
    @Override
    public Optional<User> findById(int id) throws DaoException {
        logger.info("Finding user by id in dao.");
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

    @Override
    public void create(User entity) throws DaoException {
        // TODO Auto-generated method stub

    }

    // UserDao method for updating current user
    @Override
    public void update(User entity) throws DaoException {
        logger.info("Updating user info in dao.");
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
            statement.setInt(14, entity.getId());
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

    @Override
    public Optional<User> findUserWithCards(int id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_USER_WITH_CARDS);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return UserBuilder.createUserWithCards(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public Optional<User> findUserWithOrders(int id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_USER_WITH_ORDERS);
            statement.setInt(1, id);
            result = statement.executeQuery();
            return UserBuilder.createUserWithOrders(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    @Override
    public Optional<User> findUserWithActiveOrder(int id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(FIND_USER_WITH_ACTIVE_ORDER);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return UserBuilder.createUserWithActiveOrder(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public Optional<User> findUserWithCardsAndOrders(int id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(FIND_USER_WITH_CARDS_AND_ORDERS);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (!result.next()) {
                System.out.println("dsd");
                return Optional.empty();
            } else {
                return UserBuilder.createUserWithCardsAndOrders(result);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    // UserDao method of founding all system users
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

    @Override
    public void addUser(String firstName, String lastName, String email, String password, String role,
            Date registrationDate, int rentalAmount, Date lastRentaDate, String phoneNumber, String status,
            boolean confirmed, String imagePath) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_USER_IN_DATABASE)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, role);
            statement.setDate(6, registrationDate);
            statement.setInt(7, rentalAmount);
            statement.setDate(8, lastRentaDate);
            statement.setString(9, phoneNumber);
            statement.setString(10, status);
            statement.setBoolean(11, confirmed);
            statement.setString(12, imagePath);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    // UserDao method for deleting user
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

    // UserDao method for matching existing user in database by email and password
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

    // UserDao method for finding existing user by email
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

    @Override
    public List<User> findAllUsers() throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(FIND_ALL_USERS);
            statement.setString(1, RoleType.USER.toString());
            ResultSet result = statement.executeQuery();
            ArrayList<User> users = UserBuilder.createUsers(result);
            return users;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public void updateUser(String firstName, String lastName, String phoneNumber, int id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(UPDATE_USER_IN_DATABASE);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, phoneNumber);
            statement.setInt(4, id);
            int row = statement.executeUpdate();
            if (row == 0) {
                throw new DaoException("User was not updated!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public void changeImage(String imagePath, int id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(CHANGE_IMAGE_PATH);
            statement.setString(1, imagePath);
            statement.setInt(2, id);
            int row = statement.executeUpdate();
            if (row == 0) {
                throw new DaoException("Image was not updated!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public void lockUser(int id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(LOCK_USER);
            statement.setInt(1, id);
            int row = statement.executeUpdate();
            if (row == 0) {
                throw new DaoException("User was not locked!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public void unlockUser(int id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(UNLOCK_USER);
            statement.setInt(1, id);
            int row = statement.executeUpdate();
            if (row == 0) {
                throw new DaoException("User was not unlocked!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public List<User> findAllUnlocked() throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(FIND_ALL_UNLOCKED_USERS);
            statement.setString(1, "LOCK");
            ResultSet result = statement.executeQuery();
            ArrayList<User> users = UserBuilder.createUsers(result);
            return users;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public void confirmUser(String email) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_CONFIRM_USER);
            statement.setString(1, email);
            int row = statement.executeUpdate();
            if (row == 0) {
                logger.error("User was not confirmed!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    // UserDao transaction method for replenishing user cash
    @Override
    public void replenishCash(User user, Card card) throws DaoException {
        logger.info("Replenish user cash in dao!");
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

    // UserDao transaction method for updating user after ordering bicycle
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
            userStatement.setInt(4, user.getId());
            userStatement.executeUpdate();
            bicycleStatement = connection.prepareStatement(SQL_UPDATE_ORDERED_BICYCLE);
            bicycleStatement.setInt(1, bicycle.getId());
            bicycleStatement.executeUpdate();
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
