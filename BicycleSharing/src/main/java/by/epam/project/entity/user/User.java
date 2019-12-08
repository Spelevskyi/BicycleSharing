package by.epam.project.entity.user;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import by.epam.project.entity.card.Card;
import by.epam.project.entity.order.RentalOrder;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private RoleType role;
    private String status;
    private Date registrationDate;
    private int rentalAmount;
    private String phoneNumber;
    private Date lastRentalDate;
    private boolean confirmed;
    private String imagePath;
    private BigDecimal cash;
    private boolean onRoad;
    private boolean online;
    private List<Card> cards;
    private List<RentalOrder> orders;
    private RentalOrder activeOrder;

    public User(String firstName, String lastName, String email, String password, RoleType role,
            Date registrationDate, int rentalAmount, Date lastRentalDate, String phoneNumber, String status,
            boolean confirmed, String imagePath, BigDecimal cash) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.registrationDate = registrationDate;
        this.rentalAmount = rentalAmount;
        this.lastRentalDate = lastRentalDate;
        this.phoneNumber = phoneNumber;
        this.confirmed = confirmed;
        this.imagePath = imagePath;
        this.cash = cash;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = RoleType.valueOf(role);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getRentalAmount() {
        return rentalAmount;
    }

    public void setRentalAmount(int rentalAmount) {
        this.rentalAmount = rentalAmount;
    }

    public Date getLastRentalDate() {
        return lastRentalDate;
    }

    public void setLastRentalDate(Date lastRentalDate) {
        this.lastRentalDate = lastRentalDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal i) {
        this.cash = i;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<RentalOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<RentalOrder> orders) {
        this.orders = orders;
    }

    public RentalOrder getActiveOrder() {
        return activeOrder;
    }

    public void setActiveOrder(RentalOrder activeOrder) {
        this.activeOrder = activeOrder;
    }

    public boolean isOnRoad() {
        return onRoad;
    }

    public void setOnRoad(boolean onRoad) {
        this.onRoad = onRoad;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
