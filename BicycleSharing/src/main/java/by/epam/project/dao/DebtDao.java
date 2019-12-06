package by.epam.project.dao;

import java.util.List;
import java.util.Map;

import by.epam.project.entity.debt.Debt;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;

public abstract class DebtDao extends AbstractDao<Debt> {

    public abstract Map<Debt, User> findUsersDebts() throws DaoException;
    public abstract Map<Debt, User> findUserDebt(int id) throws DaoException;
    public abstract List<Debt> findAllById(int id) throws DaoException;
    public abstract void updateAfterPayDebt(User user, Debt debt) throws DaoException;
}
