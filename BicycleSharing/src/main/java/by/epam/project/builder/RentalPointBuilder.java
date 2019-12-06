package by.epam.project.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.epam.project.entity.point.RentalPoint;

public class RentalPointBuilder {

    private static final String X_COORDINATE = "x_coordinate";
    private static final String Y_COORDINATE = "y_coordinate";

    public static RentalPoint createPoint(ResultSet result) {
        try {
            result.beforeFirst();
            result.next();
            int x_coordinate = result.getInt(X_COORDINATE);
            int y_coordinate = result.getInt(Y_COORDINATE);
            System.out.println(result.getInt("Id"));
            RentalPoint point = new RentalPoint(x_coordinate, y_coordinate);
            point.setId(result.getInt("Id"));
            return point;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static ArrayList<RentalPoint> createPoints(ResultSet result) throws SQLException {
        ArrayList<RentalPoint> points = new ArrayList<>();
        while (result.next()) {
            int x_cootdinate = result.getInt(X_COORDINATE);
            int y_coordinate = result.getInt(Y_COORDINATE);
            RentalPoint point = new RentalPoint(x_cootdinate, y_coordinate);
            point.setId(result.getInt("Id"));
            points.add(point);
        }
        return points;
    }
}