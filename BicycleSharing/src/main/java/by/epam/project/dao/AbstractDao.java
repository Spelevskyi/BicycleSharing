package by.epam.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.exception.DaoException;

public abstract class AbstractDao<T> {

    private static final Logger logger = LogManager.getLogger(AbstractDao.class);

    public abstract List<T> findAll() throws DaoException;

    public abstract Optional<T> findById(int id) throws DaoException;

    public abstract void delete(int id) throws DaoException;

    public abstract void create(T entity) throws DaoException;

    public abstract void update(T entity) throws DaoException;

    public void close(Connection connection) {
        try {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
    }

    public void close(PreparedStatement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
    }

    public void close(ResultSet result) {
        try {
            if (result != null) {
                result.close();
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
    }

}
