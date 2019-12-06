package by.epam.project.logic.user;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.CardDaoImpl;
import by.epam.project.dao.impl.DebtDaoImpl;
import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.entity.card.Card;
import by.epam.project.entity.debt.Debt;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;

public class BillingUserLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(BillingUserLogic.class);

    public CardDaoImpl cardDao = new CardDaoImpl();
    public DebtDaoImpl debtDao = new DebtDaoImpl();
    public UserDaoImpl userDao = new UserDaoImpl();

    private List<Card> cards;
    private List<Debt> debets;

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of forwarding to user billing page performing.");
        if (parameters.size() != Constants.BILLING_USER_PARAMETERS_AMOUNT) {
            logger.error("Invalid user billing page parameters amount!");
            throw new LogicException("Invalid user billing page parameters amount!");
        }
        int userId = Integer.valueOf(parameters.get(0));
        try {
            Optional<User> findedUser = userDao.findById(userId);
            if (!findedUser.isPresent()) {
                logger.error("User not exists!");
            } else {
                cards = cardDao.findUserCards(userId);
                debets = debtDao.findAllById(userId);
                logger.info("Succesfully forwarding to user billing page!");
            }
        } catch (DaoException ex) {
            throw new LogicException(ex);
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Debt> getDebets() {
        return debets;
    }

}
