package by.epam.project.dao.impl;

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

    private static final String SQL_FIND_ALL = "SELECT * FROM credit_card";
    private static final String SQL_CREATE_CARD = "INSERT INTO credit_card(OwnerId,CardMaster,Balance,Code,Number,ValidationDate,Status) VALUES(?,?,?,?,?,?,?)";
    private static final String SQL_FIND_USER_CARDS = "SELECT * FROM credit_card WHERE OwnerId = ?";
    private static final String SQL_UPDATE_CARD = "UPDATE credit_card SET OwnerId = ?,CardMaster = ? ,Balance = ? ,Code = ? ,Number = ? ,ValidationDate = ? ,Status = ? WHERE Id = ?";
    private static final String SQL_DELETE_CARD = "DELETE FROM credit_card WHERE Id = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM credit_card WHERE Id = ?";
    private static final String SQL_MATCH_CODE_NUMBER_MASTER = "SELECT * FROM credit_card WHERE Code = ? AND Number = ? AND CardMaster = ?";
    private static final String SQL_FIND_USER_CARD = "SELECT * FROM credit_card WHERE OwnerId = ? AND Code = ? AND Number = ?";

    // CardDao method for finding user credit card
    @Override
    public Optional<Card> findUserCard(int id, String code, int number, String master) throws DaoException {
        logger.info("Finding user credit card.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_USER_CARD);
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


    // CardDao method of founding all cards
    @Override
    public List<Card> findAll() throws DaoException {
        logger.info("Findinf all cards in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL);
            result = statement.executeQuery();
            return CardBuilder.createCards(result);
        } catch (SQLException ex) {
            throw new DaoException();
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    // CardDo method of founding card by id
    @Override
    public Optional<Card> findById(int id) throws DaoException {
        logger.info("Finding credit card by id in dao.");
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
            int result = statement.executeUpdate();
            if (result == 0) {
                throw new DaoException("User credit card was not deleted!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    // CardDao method for finding all credit cards of current user
    @Override
    public List<Card> findUserCards(int id) throws DaoException {
        logger.info("Finding users credit cards in dao.");
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

    // CardDao method for matching existing card with new one
    @Override
    public boolean matchCodeNumberMaster(String code, int number, String master) throws DaoException {
        logger.info(
                "Matching inputing card code, identification number and card master with cards in database in dao.");
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

    // CardDao method of creating credit card
    @Override
    public void create(Card entity) throws DaoException {
        logger.info("Creating card in card dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_CREATE_CARD);
            statement.setInt(1, entity.getOwnerId());
            statement.setString(2, entity.getType());
            statement.setBigDecimal(3, entity.getBalance());
            statement.setString(4, entity.getCode());
            statement.setInt(5, entity.getNumber());
            statement.setDate(6, entity.getDate());
            statement.setString(7, entity.getStatus());
            int result = statement.executeUpdate();
            if (result == 0) {
                logger.error("Credit card was not added!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    // CarDao method of updating existing credit card
    @Override
    public void update(Card entity) throws DaoException {
        logger.info("Updating current credit card in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
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
            int result = statement.executeUpdate();
            if (result == 0) {
                logger.error("Card was not updated!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }

    }
}
