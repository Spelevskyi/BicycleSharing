package by.epam.project.entity.order;

import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.user.User;

public class RentalOrder {

    private int id;
    private int bicycleId;
    private int renterId;
    private String bookedStartTime;
    private String bookedEndTime;
    private String actualStartTime;
    private String actualEndTime;
    private OrderStatus status;
    private String direction;
    private double distance;
    private Bicycle bicycle;
    private User user;

    public RentalOrder(int bicycleId, int renterId, String bookedStartTime, String bookedEndTime,
            String actualStartTime, String actualEndTime, OrderStatus status, String direction, double distance) {
        super();
        this.bicycleId = bicycleId;
        this.renterId = renterId;
        this.bookedStartTime = bookedStartTime;
        this.bookedEndTime = bookedEndTime;
        this.actualStartTime = actualStartTime;
        this.actualEndTime = actualEndTime;
        this.status = status;
        this.direction = direction;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = OrderStatus.valueOf(status);
    }

    public int getBicycleId() {
        return bicycleId;
    }

    public void setBicycleId(int bicycleId) {
        this.bicycleId = bicycleId;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public String getBookedStartTime() {
        return bookedStartTime;
    }

    public void setBookedStartTime(String bookedStartTime) {
        this.bookedStartTime = bookedStartTime;
    }

    public String getBookedEndTime() {
        return bookedEndTime;
    }

    public void setBookedEndTime(String bookedEndTime) {
        this.bookedEndTime = bookedEndTime;
    }

    public String getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(String actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public String getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(String actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Bicycle getBicycle() {
        return bicycle;
    }

    public void setBicycle(Bicycle bicycle) {
        this.bicycle = bicycle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
