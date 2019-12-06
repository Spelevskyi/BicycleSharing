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

public class DeleteCardLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(DeleteCardLogic.class);

    public CardDaoImpl cardDao = new CardDaoImpl();

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of deleting cards performing.");
        if (parameters.size() == 0) {
            logger.error("Invalid parameters amount for deleting cards!");
            throw new LogicException("Invalid parameters amount for deleting cards!");
        }
        try {
            for (String id : parameters) {
                int cardId = Integer.valueOf(id);
                Optional<Card> findedCard = cardDao.findById(cardId);
                if (!findedCard.isPresent()) {
                    logger.error("Card not exists!");
                }
                cardDao.delete(cardId);
            }
            logger.info("Deleting row from card list.");
        } catch (DaoException ex) {
            throw new LogicException("Deleting user cards failed!", ex);
        }
    }
}
