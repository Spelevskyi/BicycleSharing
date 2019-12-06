package by.epam.project.entity.billing;

import java.math.BigDecimal;

public class PriceList {
    
    private int id;
    private String brand;
    private BigDecimal unlockPrice;
    private BigDecimal perMinutePrice;
    private BigDecimal perHourPrice;
    private BigDecimal stayPrice;
    private BigDecimal threeHoursDiscount;
    private BigDecimal sixHoursDiscount;
    private BigDecimal nineHoursDiscount;
    private BigDecimal daySale;
    private BigDecimal regularCustomerDiscount;
    private BigDecimal travelerDiscount;
    private BigDecimal newCustomerDiscount;

    public PriceList(String brand, BigDecimal unlockPrice, BigDecimal perMinutePrice, BigDecimal perHourPrice,
            BigDecimal stayPrice, BigDecimal threeHoursDiscount, BigDecimal sixHoursDiscount,
            BigDecimal nineHoursDiscount, BigDecimal daySale, BigDecimal regularCustomerDiscount,
            BigDecimal travelerDiscount, BigDecimal newCustomerDiscount) {
        super();
        this.brand = brand;
        this.unlockPrice = unlockPrice;
        this.perMinutePrice = perMinutePrice;
        this.perHourPrice = perHourPrice;
        this.stayPrice = stayPrice;
        this.threeHoursDiscount = threeHoursDiscount;
        this.sixHoursDiscount = sixHoursDiscount;
        this.nineHoursDiscount = nineHoursDiscount;
        this.daySale = daySale;
        this.regularCustomerDiscount = regularCustomerDiscount;
        this.travelerDiscount = travelerDiscount;
        this.newCustomerDiscount = newCustomerDiscount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getUnlockPrice() {
        return unlockPrice;
    }

    public void setUnlockPrice(BigDecimal unlockPrice) {
        this.unlockPrice = unlockPrice;
    }

    public BigDecimal getPerMinutePrice() {
        return perMinutePrice;
    }

    public void setPerMinutePrice(BigDecimal perMinutePrice) {
        this.perMinutePrice = perMinutePrice;
    }

    public BigDecimal getPerHourPrice() {
        return perHourPrice;
    }

    public void setPerHourPrice(BigDecimal perHourPrice) {
        this.perHourPrice = perHourPrice;
    }

    public BigDecimal getStayPrice() {
        return stayPrice;
    }

    public void setStayPrice(BigDecimal stayPrice) {
        this.stayPrice = stayPrice;
    }

    public BigDecimal getThreeHoursDiscount() {
        return threeHoursDiscount;
    }

    public void setThreeHoursDiscount(BigDecimal threeHoursDiscount) {
        this.threeHoursDiscount = threeHoursDiscount;
    }

    public BigDecimal getSixHoursDiscount() {
        return sixHoursDiscount;
    }

    public void setSixHoursDiscount(BigDecimal sixHoursDiscount) {
        this.sixHoursDiscount = sixHoursDiscount;
    }

    public BigDecimal getNineHoursDiscount() {
        return nineHoursDiscount;
    }

    public void setNineHoursDiscount(BigDecimal nineHoursDiscount) {
        this.nineHoursDiscount = nineHoursDiscount;
    }

    public BigDecimal getDaySale() {
        return daySale;
    }

    public void setDaySale(BigDecimal daySale) {
        this.daySale = daySale;
    }

    public BigDecimal getRegularCustomerDiscount() {
        return regularCustomerDiscount;
    }

    public void setRegularCustomerDiscount(BigDecimal regularCustomerDiscount) {
        this.regularCustomerDiscount = regularCustomerDiscount;
    }

    public BigDecimal getTravelerDiscount() {
        return travelerDiscount;
    }

    public void setTravelerDiscount(BigDecimal travelerDiscount) {
        this.travelerDiscount = travelerDiscount;
    }

    public BigDecimal getNewCustomerDiscount() {
        return newCustomerDiscount;
    }

    public void setNewCustomerDiscount(BigDecimal newCustomerDiscount) {
        this.newCustomerDiscount = newCustomerDiscount;
    }
}
