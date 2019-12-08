package by.epam.project.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.builder.PriceListBuilder;
import by.epam.project.dao.BillingDao;
import by.epam.project.entity.billing.PriceList;
import by.epam.project.exception.DaoException;
import by.epam.project.pool.ConnectionPool;
import by.epam.project.pool.ProxyConnection;

public class BillingDaoImpl extends BillingDao {

    private static final Logger logger = LogManager.getLogger(BillingDaoImpl.class);

    private static final String SQL_CREATE_PRICE_LIST = "INSERT INTO billing(Brand,UnlockPrice,PerMinutePrice,PerHourPrice,StayPrice,"
            + "ThreeHourDiscount,SixHourDiscount,NineHourDiscount,DaySale,RegularCustomerDiscount,TravelerDiscount,NewCustomerDiscount)"
            + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_PRICE_LIST = "UPDATE billing SET Brand = ?, UnlockPrice = ?, PerMinutePrice = ?,PerHourPrice = ?,"
            + "StayPrice = ?, ThreeHourDiscount = ?, SixHourDiscount = ?, NineHourDiscount = ?, DaySale = ?,"
            + "RegularCustomerDiscount = ?, TravelerDiscount = ?, NewCustomerDiscount = ? WHERE Id = ?";
    private static final String SQL_DELETE_BILLING = "DELETE FROM billing WHERE Id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM billing";
    private static final String SQL_SEARCH_BY_ID = "SELECT * FROM billing WHERE Id = ?";

    // BillingDao method for creating priceList
    @Override
    public void create(PriceList entity) throws DaoException {
        logger.info("Creating price list in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_CREATE_PRICE_LIST);
            statement.setString(1, entity.getBrand());
            statement.setBigDecimal(2, entity.getUnlockPrice());
            statement.setBigDecimal(3, entity.getPerMinutePrice());
            statement.setBigDecimal(4, entity.getPerHourPrice());
            statement.setBigDecimal(5, entity.getStayPrice());
            statement.setBigDecimal(6, entity.getThreeHoursDiscount());
            statement.setBigDecimal(7, entity.getSixHoursDiscount());
            statement.setBigDecimal(8, entity.getNineHoursDiscount());
            statement.setBigDecimal(9, entity.getDaySale());
            statement.setBigDecimal(10, entity.getRegularCustomerDiscount());
            statement.setBigDecimal(11, entity.getTravelerDiscount());
            statement.setBigDecimal(12, entity.getNewCustomerDiscount());
            int result = statement.executeUpdate();
            if (result == 0) {
                throw new DaoException("Price list was not added!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    // BillingDao method for updating existing billing price list
    @Override
    public void update(PriceList entity) throws DaoException {
        logger.info("Updating price list in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_PRICE_LIST);
            statement.setString(1, entity.getBrand());
            statement.setBigDecimal(2, entity.getUnlockPrice());
            statement.setBigDecimal(3, entity.getPerMinutePrice());
            statement.setBigDecimal(4, entity.getPerHourPrice());
            statement.setBigDecimal(5, entity.getStayPrice());
            statement.setBigDecimal(6, entity.getThreeHoursDiscount());
            statement.setBigDecimal(7, entity.getSixHoursDiscount());
            statement.setBigDecimal(8, entity.getNineHoursDiscount());
            statement.setBigDecimal(9, entity.getDaySale());
            statement.setBigDecimal(10, entity.getRegularCustomerDiscount());
            statement.setBigDecimal(11, entity.getTravelerDiscount());
            statement.setBigDecimal(12, entity.getNewCustomerDiscount());
            statement.setInt(13, entity.getId());
            int result = statement.executeUpdate();
            if (result == 0) {
                throw new DaoException("Price list was not updated!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }

    }

    // BillingDao method for deleting existing price list
    @Override
    public void delete(int id) throws DaoException {
        logger.info("Deleting price list in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_DELETE_BILLING);
            statement.setInt(1, id);
            int result = statement.executeUpdate();
            if (result == 0) {
                throw new DaoException("Price list was not deleted!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }

    }

    // PriceListDao method for finding all price lists
    @Override
    public List<PriceList> findAll() throws DaoException {
        logger.info("Finding all price lists.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL);
            result = statement.executeQuery();
            return PriceListBuilder.createList(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    // PriceListDao method for finding list by id
    @Override
    public Optional<PriceList> findById(int id) throws DaoException {
        logger.info("Finding price list by id.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_SEARCH_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            return PriceListBuilder.createPriceList(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        finally {
            close(result);
            close(statement);
            close(connection);
        }
    }
}
