package by.epam.project.logic.admin;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.DebtDaoImpl;
import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.entity.debt.Debt;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;

public class AdminHomeLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(AdminHomeLogic.class);

    public UserDaoImpl userDao = new UserDaoImpl();
    public DebtDaoImpl debtDao = new DebtDaoImpl();

    private Map<Debt, User> debts;

    /**
     * Logic method of forwarding to admin home page
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of performing admin home page forwarding.");
        if (parameters.size() != Constants.HOME_PARAMETERS_AMOUNT) {
            logger.error("Invalid admin home page parameters amount!");
            throw new LogicException("Invalid admin home page parameters amount!");
        }
        try {
            debts = debtDao.findUsersDebts();
            logger.info("Succesfully admin home page logic performing.");
        } catch (DaoException ex) {
            throw new LogicException("Admin home page forwarding failed!", ex);
        }
    }

    public Map<Debt, User> getDebts() {
        return debts;
    }
}
