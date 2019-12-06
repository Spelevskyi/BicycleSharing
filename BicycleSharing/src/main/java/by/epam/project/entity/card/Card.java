package by.epam.project.entity.card;

import java.math.BigDecimal;
import java.sql.Date;

public class Card {

    private int id;
    private int ownerId;
    private CardType type;
    private BigDecimal balance;
    private String code;
    private int number;
    private Date date;
    private String status;

    public Card(int ownerId, CardType type, BigDecimal balance, String code, int number, Date date, String status) {
        super();
        this.ownerId = ownerId;
        this.type = type;
        this.balance = balance;
        this.code = code;
        this.status = status;
        this.number = number;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
