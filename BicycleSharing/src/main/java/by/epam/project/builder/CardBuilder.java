package by.epam.project.builder;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.entity.card.Card;
import by.epam.project.exception.DaoException;
import by.epam.project.util.Constants;

public class CardBuilder {

    private static final Logger logger = LogManager.getLogger(CardBuilder.class);

    /**
     * CardBuilder method for creating single credit card
     * 
     * @param result
     * @return
     * @throws DaoException
     */
    public static Optional<Card> createCard(ResultSet result) throws DaoException {
        try {
            logger.info("Creating new credit card entity.");
            if (!result.next()) {
                logger.error("Credit card was not found!");
                return Optional.empty();
            }
            result.beforeFirst();
            result.next();
            String type = result.getString(Constants.CARD_TYPE);
            int ownerId = result.getInt(Constants.CARD_OWNER_ID);
            BigDecimal balance = result.getBigDecimal(Constants.CARD_BALANCE);
            String code = result.getString(Constants.CARD_CODE);
            int number = result.getInt(Constants.CARD_NUMBER);
            Date date = result.getDate(Constants.CARD_VALIDATION_DATE);
            String status = result.getString(Constants.CARD_STATUS);
            Card card = new Card(ownerId, type, balance, code, number, date, status);
            card.setId(result.getInt(Constants.CARD_ID));
            logger.info("Succesfully creating credit card entity.");
            return Optional.of(card);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    /**
     * CardBuilder method for creating cards of current user
     * 
     * @param result
     * @return
     * @throws DaoException
     */
    public static List<Card> createUserCards(ResultSet result) throws DaoException {
        ArrayList<Card> cards = new ArrayList<>();
        try {
            logger.info("Creating credit cards.");
            result.beforeFirst();
            result.next();
            while (result.next()) {
                logger.info("Creating new credit card entity.");
                String type = result.getString(Constants.CARD_TYPE);
                int ownerId = result.getInt(Constants.CARD_OWNER_ID);
                BigDecimal balance = result.getBigDecimal(Constants.CARD_BALANCE);
                String code = result.getString(Constants.CARD_CODE);
                int number = result.getInt(Constants.CARD_NUMBER);
                Date date = result.getDate(Constants.CARD_VALIDATION_DATE);
                String status = result.getString(Constants.CARD_STATUS);
                Card card = new Card(ownerId, type, balance, code, number, date, status);
                card.setId(result.getInt(Constants.CARD_ID));
                cards.add(card);
                logger.info("Succesfully creating credit card entity.");
            }
            logger.info("Succesfully creating credit cards of current user.");
            return cards;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    /**
     * CardBuilder method for multiple creating cards
     * 
     * @param result
     * @return
     * @throws DaoException
     */
    public static List<Card> createCards(ResultSet result) throws DaoException {
        ArrayList<Card> cards = new ArrayList<>();
        try {
            logger.info("Creating credit cards.");
            while (result.next()) {
                logger.info("Creating new credit card entity.");
                String type = result.getString(Constants.CARD_TYPE);
                int ownerId = result.getInt(Constants.CARD_OWNER_ID);
                BigDecimal balance = result.getBigDecimal(Constants.CARD_BALANCE);
                String code = result.getString(Constants.CARD_CODE);
                int number = result.getInt(Constants.CARD_NUMBER);
                Date date = result.getDate(Constants.CARD_VALIDATION_DATE);
                String status = result.getString(Constants.CARD_STATUS);
                Card card = new Card(ownerId, type, balance, code, number, date, status);
                card.setId(result.getInt(Constants.CARD_ID));
                cards.add(card);
                logger.info("Succesfully creating credit card entity.");
            }
            logger.info("Succesfully creating credit cards.");
            return cards;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }
}
