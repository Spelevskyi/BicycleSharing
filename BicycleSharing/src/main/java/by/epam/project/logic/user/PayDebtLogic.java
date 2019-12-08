package by.epam.project.logic.user;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.DebtDaoImpl;
import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.entity.debt.Debt;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.logic.admin.ChangeBillingLogic;
import by.epam.project.util.Constants;

public class PayDebtLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(ChangeBillingLogic.class);

    public DebtDaoImpl debtDao = new DebtDaoImpl();
    public UserDaoImpl userDao = new UserDaoImpl();

    private User user;

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of paying user debt performing.");
        if (parameters.size() != Constants.PAY_DEBT_PARAMETERS_AMOUNT) {
            logger.error("Invalid parameters amount for billing changing!");
            throw new LogicException("Invalid parameters amount for billing changing!");
        }
        int debtId = Integer.valueOf(parameters.get(0));
        int userId = Integer.valueOf(parameters.get(1));
        try {
            Optional<User> findedUser = userDao.findById(userId);
            if (!findedUser.isPresent()) {
                logger.error("User not exists!");
            }
            Optional<Debt> findedDebt = debtDao.findById(debtId);
            if (!findedDebt.isPresent()) {
                logger.error("Debt not exists!");
            } else {
                Map<Debt, User> debt = debtDao.findUserDebt(userId);
                for (Map.Entry<Debt, User> userDebt : debt.entrySet()) {
                    if (userDebt.getValue().getCash().doubleValue() < userDebt.getKey().getAmount().doubleValue()) {
                        logger.error("Not enough cash on balance!");
                        throw new LogicException("Not enough cash on balance!");
                    } else {
                        User changedUser = userDebt.getValue();
                        Debt changedDebt = userDebt.getKey();
                        changedUser.setCash(changedUser.getCash().subtract(changedDebt.getAmount()));
                        changedUser.setStatus(Constants.UNLOCKED);
                        debtDao.updateAfterPayDebt(changedUser, changedDebt);
                        user = userDao.findById(userId).get();
                        logger.info("Succesfully pay debt.");
                    }
                }
            }
        } catch (DaoException ex) {
            throw new LogicException("Paying user debt failed!", ex);
        }
    }

    public User getUser() {
        return user;
    }

}
