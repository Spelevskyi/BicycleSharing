package by.epam.project.logic.user;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.CardDaoImpl;
import by.epam.project.entity.card.Card;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;

public class DisableCardLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(DisableCardLogic.class);

    public CardDaoImpl cardDao = new CardDaoImpl();

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of disabling user card performing.");
        if (parameters.size() != Constants.DISABLE_CARD_PARAMETERS_AMOUNT) {
            logger.error("Invalid parameters amount for disabling cards!");
            throw new LogicException("Invalid parameters amount for disabling cards!");
        }
        int cardId = Integer.valueOf(parameters.get(0));
        try {
            Optional<Card> findedCard = cardDao.findById(cardId);
            if (!findedCard.isPresent()) {
                logger.error("Card not exists! Try again. Choose existing credit card!");
            } else {
                Card card = findedCard.get();
                card.setStatus(Constants.DISABLE);
                cardDao.update(card);
                logger.info("Disabling credit card performing!");
            }
        } catch (DaoException ex) {
            throw new LogicException("Disabling current card failed!", ex);
        }
    }
}
