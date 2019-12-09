package by.epam.project.entity.point;

public class RentalPoint {

    private int id;
    private int x_coordinate;
    private int y_coordinate;
    private int bicycleId;

    public RentalPoint(int x_coordinate, int y_coordinate) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX_coordinate() {
        return x_coordinate;
    }

    public void setX_coordinate(int x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    public int getY_coordinate() {
        return y_coordinate;
    }

    public void setY_coordinate(int y_coordinate) {
        this.y_coordinate = y_coordinate;
    }

    public int getBicycleId() {
        return bicycleId;
    }

    public void setBicycleId(int bicycleId) {
        this.bicycleId = bicycleId;
    }

}
