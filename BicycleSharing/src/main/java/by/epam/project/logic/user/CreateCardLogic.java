package by.epam.project.logic.user;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.CardDaoImpl;
import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.entity.card.Card;
import by.epam.project.entity.card.CardType;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;
import by.epam.project.validation.CardDataValidator;

public class CreateCardLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(CreateCardLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();
    public CardDaoImpl cardDao = new CardDaoImpl();

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of creating user credit card performing.");
        if (parameters.size() != Constants.CREATE_CARD_PARAMETERS_AMOUNT) {
            logger.error("Invalid card creation parameters amount!");
            throw new LogicException("Invalid card creation parameters amount!");
        }
        String cardMaster = parameters.get(0);
        String cardCode = parameters.get(1);
        int number = Integer.valueOf(parameters.get(2));
        String date = parameters.get(3);
        int userId = Integer.valueOf(parameters.get(4));
        if (!(CardDataValidator.isCardCodeValid(cardCode))) {
            logger.error("Invalid card code value!");
            throw new LogicException("Invalid card code value!");
        }
        if (!(CardDataValidator.isCardMasterValid(cardMaster))) {
            logger.error("Invalid card master value!");
            throw new LogicException("Invalid card master value!");
        }
        if (!(CardDataValidator.isCardNumberValid(number))) {
            logger.error("Invalid card identification number value!");
            throw new LogicException("Invalid card identification number value!");
        }
        if (!(CardDataValidator.isCardDateValid(date))) {
            logger.error("Invalid card validation date value!");
            throw new LogicException("Invalid card validation date value!");
        }
        try {
            if (cardDao.matchCodeNumberMaster(cardCode, number, cardMaster)) {
                logger.error("Card already exists!");
                throw new LogicException("Card already exists!");
            } else {
                Card card = new Card(userId, CardType.valueOf(cardMaster),
                        new BigDecimal(Constants.INITIAL_CARD_BALANCE), cardCode,
                        number, Date.valueOf(date), Constants.ENABLE);
                cardDao.create(card);
                logger.info("Succesfully created card!");
            }
        } catch (DaoException ex) {
            throw new LogicException("Credit card creation failed!", ex);
        }
    }

}
