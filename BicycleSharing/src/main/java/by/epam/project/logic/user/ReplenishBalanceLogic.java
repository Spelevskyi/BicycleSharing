package by.epam.project.logic.user;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.CardDaoImpl;
import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.entity.card.Card;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;
import by.epam.project.validation.CardDataValidator;

public class ReplenishBalanceLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(ReplenishBalanceLogic.class);

    private UserDaoImpl userDao = new UserDaoImpl();
    private CardDaoImpl cardDao = new CardDaoImpl();

    private User user;

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("User balance replenishing executing.");
        if (parameters.size() != Constants.BALANCE_REPLENICH_PARAMETERS_AMOUNT) {
            logger.error("Invalid blance replenish parameters amount!");
            throw new LogicException("Invalid blance replenish parameters amount!");
        }
        int userId = Integer.valueOf(parameters.get(0));
        String type = parameters.get(1);
        double amount = Double.valueOf(parameters.get(2));
        String code = parameters.get(3);
        int number = Integer.valueOf(parameters.get(4));
        if ((amount < 0)) {
            logger.error("Cash amount is less than 0!");
            throw new LogicException("Cash amount is less than 0!");
        }
        if ((amount > Constants.INITIAL_RENTAL_AMOUNT)) {
            logger.error("Cash amount is more than 1000!");
            throw new LogicException("Cash amount is more than 1000!");
        }
        if (!CardDataValidator.isCardCodeValid(code)) {
            logger.error("Invalid card code value!");
            throw new LogicException("Invalid card code value!");
        }
        if (!CardDataValidator.isCardMasterValid(type)) {
            logger.error("Invalid card master value!");
            throw new LogicException("Invalid card master value!");
        }
        if (!CardDataValidator.isCardNumberValid(number)) {
            logger.error("Invalid card identification number value!");
            throw new LogicException("Invalid card identification number value!");
        }
        try {
            Optional<User> findedUser = userDao.findById(userId);
            if (!findedUser.isPresent()) {
                logger.error("User not exists!");
            }
            Optional<Card> findedCard = cardDao.findUserCard(userId, code, number, type);
            if (!findedCard.isPresent()) {
                logger.error("Card not exists!");
                throw new LogicException("Card no exists!");
            } else {
                User changedUser = findedUser.get();
                Card changedCard = findedCard.get();
                if (changedCard.getBalance().doubleValue() < amount) {
                    logger.error("Card balance is limited!");
                    throw new LogicException("Card balance is linited!");
                }
                if (changedCard.getStatus().equals(Constants.DISABLE)) {
                    logger.error("Card is blocked!");
                    throw new LogicException("Card is blocked!");
                }
                BigDecimal cash = new BigDecimal(amount);
                changedUser.setCash(changedUser.getCash().add(cash));
                changedCard.setBalance(changedCard.getBalance().subtract(cash));
                userDao.replenishCash(changedUser, changedCard);
                user = userDao.findById(userId).get();
                logger.info("Succesfully replenish user balance!");
            }
        } catch (DaoException ex) {
            logger.error(ex);
            throw new LogicException("Replenishing user balance failed!", ex);
        }
    }

    public User getUser() {
        return user;
    }
}
