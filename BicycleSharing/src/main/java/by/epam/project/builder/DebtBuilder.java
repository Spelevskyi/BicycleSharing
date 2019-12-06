package by.epam.project.builder;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.entity.debt.Debt;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.util.Constants;

public class DebtBuilder {

    private static final Logger logger = LogManager.getLogger(DebtBuilder.class);

    public static Optional<Debt> createDebt(ResultSet result) throws DaoException {
        try {
            result.beforeFirst();
            result.next();
            int debtorId = result.getInt(Constants.DEBTOR_ID);
            BigDecimal amount = result.getBigDecimal(Constants.DEBT_AMOUNT);
            Date creationDate = result.getDate(Constants.DEBT_DATE);
            Debt debt = new Debt(debtorId, amount, creationDate);
            debt.setId(result.getInt(Constants.DEBT_ID));
            return Optional.of(debt);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    // DebtBuilder static method for creating Map with key - Debt and value - User
    public static Map<Debt, User> createUsersDebts(ResultSet result) throws DaoException {
        logger.info("Creating debts with users in builder.");
        Map<Debt, User> debets = new HashMap<>();
        try {
            while (result.next()) {
                int debtorId = result.getInt(Constants.DEBTOR_ID);
                BigDecimal amount = result.getBigDecimal(Constants.DEBT_AMOUNT);
                Date creationDate = result.getDate(Constants.DEBT_DATE);
                Debt debt = new Debt(debtorId, amount, creationDate);
                debt.setId(result.getInt(Constants.DEBT_ID));
                User user = UserBuilder.createOrderUser(result).get();
                debets.put(debt, user);
                logger.info("New map element was added.");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        logger.info("Succesfully creating debts with users.");
        return debets;
    }

    // DebtBuilder static method for creating Map with key - Debt and value - User
    public static Map<Debt, User> createUserDebt(ResultSet result) throws DaoException {
        logger.info("Creating debt of current user in builder.");
        Map<Debt, User> debets = new HashMap<>();
        try {
            result.beforeFirst();
            result.next();
            int debtorId = result.getInt(Constants.DEBTOR_ID);
            BigDecimal amount = result.getBigDecimal(Constants.DEBT_AMOUNT);
            Date creationDate = result.getDate(Constants.DEBT_DATE);
            Debt debt = new Debt(debtorId, amount, creationDate);
            debt.setId(result.getInt(Constants.DEBT_ID));
            User user = UserBuilder.createOrderUser(result).get();
            debets.put(debt, user);
            logger.info("New map element was added.");
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        logger.info("Succesfully creating debt of user.");
        return debets;
    }

    public static ArrayList<Debt> createDebets(ResultSet result) throws DaoException {
        ArrayList<Debt> debets = new ArrayList<>();
        try {
            while (result.next()) {
                int debtorId = result.getInt(Constants.DEBTOR_ID);
                BigDecimal amount = result.getBigDecimal(Constants.DEBT_AMOUNT);
                Date creationDate = result.getDate(Constants.DEBT_DATE);
                Debt debt = new Debt(debtorId, amount, creationDate);
                debt.setId(result.getInt(Constants.DEBT_ID));
                debets.add(debt);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return debets;
    }
}
