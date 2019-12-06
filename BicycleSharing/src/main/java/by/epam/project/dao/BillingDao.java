package by.epam.project.dao;

import java.util.Optional;

import by.epam.project.entity.billing.PriceList;
import by.epam.project.exception.DaoException;

public abstract class BillingDao extends AbstractDao<PriceList> {

    public abstract Optional<PriceList> findByBrand(String brand) throws DaoException;

    public abstract void updatePriceList(int id, String brand, int unlockPrice, int pricePerMinute, int pricePerHour,
            int pricePerDay, int stayPrice, int threeHoursDiscount, int SixHoursDiscount, int nineHoursDiscount,
            int daySale, int regularCustomerDiscount, int travelerDiscount, int newCustomerDiscount)
            throws DaoException;
}
