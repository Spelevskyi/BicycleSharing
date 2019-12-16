package by.epam.project.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.builder.RentalPointBuilder;
import by.epam.project.dao.RentalPointDao;
import by.epam.project.entity.point.RentalPoint;
import by.epam.project.exception.DaoException;
import by.epam.project.pool.ConnectionPool;
import by.epam.project.pool.ProxyConnection;

public class RentalPointDaoImpl extends RentalPointDao {

    private static final Logger logger = LogManager.getLogger(RentalPointDaoImpl.class);

    private static final String SQL_FIND_ALL = "SELECT * FROM rental_point";
    private static final String SQL_CREATE_POINT = "INSERT INTO rental_point(x_coordinate,y_coordinate,BicycleId) VALUES(?,?,?)";
    private static final String SQL_SEARCH_BY_ID = "SELECT * FROM rental_point WHERE Id = ?";
    private static final String SQL_DELETE_BY_BICYCLE_ID = "DELETE FROM rental_point WHERE BicycleId = ?";
    private static final String SQL_DELETE_POINT = "DELETE FROM rental_point WHERE Id = ?";

    /**
     * RentalPointDao method for deleting point by bicycle id
     */
    @Override
    public void deleteByBicycleId(int bicycleId) throws DaoException {
        logger.info("Deleting rental point by bicycle id in dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_DELETE_BY_BICYCLE_ID);
            statement.setInt(1, bicycleId);
            int result = statement.executeUpdate();
            if (result == 0) {
                logger.error("Rental point was not deleted!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    /**
     * RentalPointDao method for creating point
     */
    @Override
    public void create(RentalPoint entity) throws DaoException {
        logger.info("Creation rental point in point dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_CREATE_POINT);
            statement.setInt(1, entity.getX_coordinate());
            statement.setInt(2, entity.getY_coordinate());
            statement.setInt(3, entity.getBicycleId());
            int result = statement.executeUpdate();
            if (result == 0) {
                logger.error("Rental point was not created!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    /**
     * RentalPointDao method for finding all rental points
     */
    @Override
    public List<RentalPoint> findAll() throws DaoException {
        logger.info("Finding all rental points in point dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL);
            result = statement.executeQuery();
            return RentalPointBuilder.createPoints(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    /**
     * RentalPointDao method for finding point by id
     */
    @Override
    public Optional<RentalPoint> findById(int id) throws DaoException {
        logger.info("Deleting rental point in point dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_SEARCH_BY_ID);
            result = statement.executeQuery();
            return RentalPointBuilder.createPoint(result);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
    }

    /**
     * RentalPointDao method for deleting point
     */
    @Override
    public void delete(int id) throws DaoException {
        logger.info("Deleting rental point in point dao.");
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_DELETE_POINT);
            statement.setInt(1, id);
            int result = statement.executeUpdate();
            if (result == 0) {
                logger.error("Rental point was not deleted!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public void update(RentalPoint entity) throws DaoException {
        // TODO Auto-generated method stub

    }

}
