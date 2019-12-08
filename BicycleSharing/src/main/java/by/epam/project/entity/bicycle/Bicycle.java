package by.epam.project.entity.bicycle;

import java.sql.Date;

import by.epam.project.entity.billing.PriceList;
import by.epam.project.entity.point.RentalPoint;

public class Bicycle {
    
    private int id;
    private int pointId;
    private int billingId;
    private String brand;
    private String color;
    private int speed;
    private Date date;
    private String state;
    private String imagePath;
    private String status;
    private RentalPoint point;
    private PriceList priceList;

    public Bicycle(String brand, String color, int speed, Date date, String state, String imagePath,
            String status) {
        super();
        this.brand = brand;
        this.color = color;
        this.speed = speed;
        this.date = date;
        this.state = state;
        this.status = status;
        this.imagePath = imagePath;
    }

    public Bicycle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPointId() {
        return pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public RentalPoint getPoint() {
        return point;
    }

    public void setPoint(RentalPoint point) {
        this.point = point;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBillingId() {
        return billingId;
    }

    public void setBillingId(int billingId) {
        this.billingId = billingId;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }
}
