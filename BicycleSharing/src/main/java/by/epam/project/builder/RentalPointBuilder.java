package by.epam.project.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.entity.point.RentalPoint;
import by.epam.project.exception.DaoException;
import by.epam.project.util.Constants;

public class RentalPointBuilder {

    private static final Logger logger = LogManager.getLogger(RentalPointBuilder.class);

    /**
     * Method in RentalPointBuilder for creating point
     * 
     * @param result
     * @return
     * @throws DaoException
     */
    public static Optional<RentalPoint> createPoint(ResultSet result) throws DaoException {
        logger.info("Creating rentalpoint in builder.");
        try {
            result.beforeFirst();
            result.next();
            int x_coordinate = result.getInt(Constants.POINT_X_COORDINATE);
            int y_coordinate = result.getInt(Constants.POINT_Y_COORDINATE);
            RentalPoint point = new RentalPoint(x_coordinate, y_coordinate);
            point.setId(result.getInt(Constants.POINT_ID));
            logger.info("Succesfully creating point!");
            return Optional.of(point);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    /**
     * Method in RentalPointBuilder for creating points
     * 
     * @param result
     * @return
     * @throws DaoException
     */
    public static ArrayList<RentalPoint> createPoints(ResultSet result) throws DaoException {
        logger.info("Creating rentalpoints in builder.");
        ArrayList<RentalPoint> points = new ArrayList<>();
        try {
            logger.info("Creating rentalpoint in builder.");
            while (result.next()) {
                int x_cootdinate = result.getInt(Constants.POINT_X_COORDINATE);
                int y_coordinate = result.getInt(Constants.POINT_Y_COORDINATE);
                RentalPoint point = new RentalPoint(x_cootdinate, y_coordinate);
                point.setId(result.getInt(Constants.POINT_ID));
                points.add(point);
                logger.info("Succesfully creating point!");
            }
            return points;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }
}