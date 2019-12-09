package by.epam.project.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.builder.DebtBuilder;
import by.epam.project.dao.DebtDao;
import by.epam.project.entity.debt.Debt;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.pool.ConnectionPool;
import by.epam.project.pool.ProxyConnection;

public class DebtDaoImpl extends DebtDao {

    private static final Logger logger = LogManager.getLogger(DebtDaoImpl.class);

    private static final String SQL_CREATE_DEBT = "INSERT INTO debt(DebtorId,Amount,CreationDate) VALUES(?,?,?)";
    private static final String SQL_DELETE_DEBT = "DELETE FROM debt WHERE Id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM debt";
    private static final String SQL_UPDATE_DEBT = "UPDATE debt SET DebtorId = ?, Amount = ?, CreationDate = ? WHERE Id = ?";
    private static final String SQL_UPDATE_USER_AFTER_PAY_DEBT = "UPDATE user SET Cash = ?, Status = ? WHERE Id = ?";
    private static final String SQL_FIND_ALL_BY_ID = "SELECT * FROM debt WHERE DebtorId = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM debt WHERE Id = ?";
    private static final String SQL_FIND_USERS_DEBTS = "SELECT * FROM debt INNER JOIN user ON user.Id = debt.DebtorId";
    private static final String SQL_FIND_USER_DEBT = "SELECT * FROM debt INNER JOIN user ON user.Id = debt.DebtorId WHERE user.Id = ?";

    /**
     * DebtDao transaction method for updating user after paying debt and deleting
     * debt entity
     */
    @Override
    public void updateAfterPayDebt(User user, Debt debt) throws DaoException {
        logger.info("Updating user after paying debt and deleting debt in dao.");
        ProxyConnection connection = null;
        PreparedStatement debtStatement = null;
        PreparedStatement userStatement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            debtStatement = connection.prepareStatement(SQL_DELETE_DEBT);
            debtStatement.setDouble(1, debt.getId());
            int result = debtStatement.executeUpdate();
            if (result == 0) {
                logger.error("Debt was not deleted!");
            }
            userStatement = connection.prepareStatement(SQL_UPDATE_USER_AFTER_PAY_DEBT);
            userStatement.setBigDecimal(1, user.getCash());
            userStatement.setString(2, user.getStatus());
            userStatement.setInt(3, user.getId());
            result = userStatement.executeUpdate();
            if (result == 0) {
                logger.error("User was not updated!");
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
            close(debtStatement);
            close(userStatement);
            close(connection);
        }

    }

    /**
     * DebtDao method for finding all debt with users
     */
    @Override
    public Map<Debt, User> findUserDebt(int id) throws DaoException {
        logger.info("Finding users with debts in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_USER_DEBT);
            statement.setInt(1, id);
            result = statement.executeQuery();
            Map<Debt, User> debets = DebtBuilder.createUserDebt(result);
            return debets;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    /**
     * DebtDao method of finding all debts of current user
     */
    @Override
    public List<Debt> findAllById(int id) throws DaoException {
        logger.info("Finding all debt of current user by id in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL_BY_ID);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return DebtBuilder.createDebets(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    /**
     * DebtDao method for finding users with debts
     */
    @Override
    public Map<Debt, User> findUsersDebts() throws DaoException {
        logger.info("Finding users with debts in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_USERS_DEBTS);
            result = statement.executeQuery();
            Map<Debt, User> debets = DebtBuilder.createUsersDebts(result);
            return debets;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    /**
     * DabtDao method of finding all debts
     */
    @Override
    public List<Debt> findAll() throws DaoException {
        logger.info("Finding all debts in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL);
            result = statement.executeQuery();
            return DebtBuilder.createDebets(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    /**
     * DebtDao method of finding debt by id
     */
    @Override
    public Optional<Debt> findById(int id) throws DaoException {
        logger.info("Finding debt by id in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            return DebtBuilder.createDebt(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    /**
     * DebtDao method for deleting user debt
     */
    @Override
    public void delete(int id) throws DaoException {
        logger.info("Deleting user debt in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_DELETE_DEBT);
            statement.setInt(1, id);
            int result = statement.executeUpdate();
            if (result == 0) {
                logger.error("User debt was not deleted!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    /**
     * DebtDao method for creating debt
     */
    @Override
    public void create(Debt entity) throws DaoException {
        logger.info("Creating debt in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_CREATE_DEBT);
            statement.setInt(1, entity.getDebtorId());
            statement.setBigDecimal(2, entity.getAmount());
            statement.setDate(3, entity.getCreationDate());
            int result = statement.executeUpdate();
            if (result == 0) {
                logger.error("User debt was not created!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    /**
     * DebtDao method for updating user debt
     */
    @Override
    public void update(Debt entity) throws DaoException {
        logger.info("Creating debt in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_DEBT);
            statement.setInt(1, entity.getDebtorId());
            statement.setBigDecimal(2, entity.getAmount());
            statement.setDate(3, entity.getCreationDate());
            statement.setInt(4, entity.getId());
            int result = statement.executeUpdate();
            if (result == 0) {
                logger.error("User debt was not updated!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }
}
