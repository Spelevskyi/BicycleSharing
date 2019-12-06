package by.epam.project.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.builder.CardBuilder;
import by.epam.project.dao.CardDao;
import by.epam.project.entity.card.Card;
import by.epam.project.exception.DaoException;
import by.epam.project.pool.ConnectionPool;
import by.epam.project.pool.ProxyConnection;

public class CardDaoImpl extends CardDao {

    private static final Logger logger = LogManager.getLogger(CardDaoImpl.class);

    private static final String FIND_ALL_CARDS = "SELECT * FROM credit_card";
    private static final String ADD_CARD = "INSERT INTO credit_card(OwnerId,CardMaster,Balance,Code,Number,ValidationDate,Status) VALUES(?,?,?,?,?,?,?)";
    private static final String REMOVE_CARD = "DELETE FROM credit_card WHERE Id = ?";
    private static final String REMOVE_USER_CARDS = "DELETE FROM credit_card WHERE OwnerId = ?";
    private static final String SQL_FIND_USER_CARDS = "SELECT * FROM credit_card WHERE OwnerId = ?";
    private static final String SQL_UPDATE_CARD = "UPDATE credit_card SET OwnerId = ?,CardMaster = ? ,Balance = ? ,Code = ? ,Number = ? ,ValidationDate = ? ,Status = ? WHERE Id = ?";
    private static final String SQL_DELETE_CARD = "DELETE FROM credit_card WHERE Id = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM credit_card WHERE Id = ?";
    private static final String WITHDRAW_BALANCE = "UPDATE credit_card SET Balance = Balance - ? WHERE Id = ?";
    private static final String SQL_MATCH_CODE_NUMBER_MASTER = "SELECT * FROM credit_card WHERE Code = ? AND Number = ? AND CardMaster = ?";
    private static final String SQL_MATCH_CODE_NUMBER = "SELECT * FROM credit_card WHERE OwnerId = ? AND Code = ? AND Number = ?";

    @Override
    public Optional<Card> findUserCard(int id, String code, int number, String master) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_MATCH_CODE_NUMBER);
            statement.setInt(1, id);
            statement.setString(2, code);
            statement.setInt(3, number);
            result = statement.executeQuery();
            return CardBuilder.createCard(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    @Override
    public void deleteUserCards(int id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(REMOVE_USER_CARDS)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public List<Card> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_ALL_CARDS)) {
            ResultSet result = statement.executeQuery();
            return CardBuilder.createCards(result);
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public void addCard(int ownerId, String cardType, int balance, String code, int number, Date date, String status)
            throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(ADD_CARD);
            statement.setInt(1, ownerId);
            statement.setString(2, cardType);
            statement.setInt(3, balance);
            statement.setString(4, code);
            statement.setInt(5, number);
            statement.setDate(6, date);
            statement.setString(7, "ENABLE");
            statement.execute();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public void withdrawBalance(int id, int amount) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(WITHDRAW_BALANCE)) {
            statement.setInt(1, amount);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public Optional<Card> findById(int id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            return CardBuilder.createCard(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    // CardDao method for deleting credit cards
    @Override
    public void delete(int id) throws DaoException {
        logger.info("Deleting user card in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_DELETE_CARD);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public List<Card> findUserCards(int id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_USER_CARDS);
            statement.setInt(1, id);
            result = statement.executeQuery();
            return CardBuilder.createCards(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    @Override
    public boolean matchCodeNumberMaster(String code, int number, String master) throws DaoException {
        logger.info("Matching inputing card code, identification number and card master with cards in database.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_MATCH_CODE_NUMBER_MASTER);
            statement.setString(1, code);
            statement.setInt(2, number);
            statement.setString(3, master);
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

    @Override
    public void create(Card entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(ADD_CARD);
            statement.setInt(1, entity.getOwnerId());
            statement.setString(2, entity.getType().toString());
            statement.setBigDecimal(3, entity.getBalance());
            statement.setString(4, entity.getCode());
            statement.setInt(5, entity.getNumber());
            statement.setDate(6, entity.getDate());
            statement.setString(7, entity.getStatus());
            statement.execute();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public void update(Card entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_CARD);
            statement.setInt(1, entity.getOwnerId());
            statement.setString(2, entity.getType().toString());
            statement.setBigDecimal(3, entity.getBalance());
            statement.setString(4, entity.getCode());
            statement.setInt(5, entity.getNumber());
            statement.setDate(6, entity.getDate());
            statement.setString(7, entity.getStatus());
            statement.setInt(8, entity.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }

    }
}
