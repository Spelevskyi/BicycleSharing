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

    /**
     * DebtBuilder static method for creation debt from ResultSet
     * @param result - ResultSet
     * @return Optional value of debt
     * @throws DaoException
     */
    public static Optional<Debt> createDebt(ResultSet result) throws DaoException {
        try {
            logger.info("Creating new debt entity.");
            if (!result.next()) {
                logger.error("Debt not exists!");
                return Optional.empty();
            }
            result.beforeFirst();
            result.next();
            int debtorId = result.getInt(Constants.DEBTOR_ID);
            BigDecimal amount = result.getBigDecimal(Constants.DEBT_AMOUNT);
            Date creationDate = result.getDate(Constants.DEBT_DATE);
            Debt debt = new Debt(debtorId, amount, creationDate);
            debt.setId(result.getInt(Constants.DEBT_ID));
            logger.info("Succesfully creating debt.");
            return Optional.of(debt);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    /**
     * DebtBuilder static method for creating Map with key - Debt and value - User
     * @param result - resultSet
     * @return Map with key - Debt, value - User
     * @throws DaoException
     */
    public static Map<Debt, User> createUsersDebts(ResultSet result) throws DaoException {
        logger.info("Creating debts with users.");
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

    /**
     * DebtBuilder static method for creating Map with one single element with key -
     * Debt and value - User
     * @param result - ResultSet
     * @return Map with key - Debt, value - User
     * @throws DaoException
     */
    public static Map<Debt, User> createUserDebt(ResultSet result) throws DaoException {
        logger.info("Creating debt of current user.");
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

    /**
     * DebtBuilder static method for creation list of debts of users
     * @param result - ResultSet
     * @return list of debts
     * @throws DaoException
     */
    public static ArrayList<Debt> createDebets(ResultSet result) throws DaoException {
        logger.info("Creating debts list.");
        ArrayList<Debt> debets = new ArrayList<>();
        try {
            while (result.next()) {
                int debtorId = result.getInt(Constants.DEBTOR_ID);
                BigDecimal amount = result.getBigDecimal(Constants.DEBT_AMOUNT);
                Date creationDate = result.getDate(Constants.DEBT_DATE);
                Debt debt = new Debt(debtorId, amount, creationDate);
                debt.setId(result.getInt(Constants.DEBT_ID));
                debets.add(debt);
                logger.info("New debt entity was added.");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        logger.info("Succesfully creating all debts of existing users.");
        return debets;
    }
}
