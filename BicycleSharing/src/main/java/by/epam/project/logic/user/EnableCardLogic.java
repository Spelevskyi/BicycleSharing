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

public class EnableCardLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(EnableCardLogic.class);

    public CardDaoImpl cardDao = new CardDaoImpl();

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of enabling current card performing.");
        if (parameters.size() == 0) {
            logger.error("Invalid parameters amount for enabling card!");
            throw new LogicException("Invalid parameters amount for enabling card!");
        }
        int cardId = Integer.valueOf(parameters.get(0));
        try {
            Optional<Card> findedCard = cardDao.findById(cardId);
            if (!findedCard.isPresent()) {
                logger.error("Card not exists!");
            }
            Card card = findedCard.get();
            card.setStatus(Constants.ENABLE);
            cardDao.update(card);
            logger.info("Enable card!");
        } catch (DaoException ex) {
            throw new LogicException("Enabling current card failed!", ex);
        }
    }
}
