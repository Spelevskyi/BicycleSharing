package by.epam.project.dao;

import java.util.Optional;

import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.card.Card;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;

public abstract class UserDao extends AbstractDao<User> {

    public abstract boolean matchEmailPassword(String email, String password) throws DaoException;
    public abstract Optional<User> findUserByEmail(String email) throws DaoException;
    public abstract void replenishCash(User user, Card card) throws DaoException;
    public abstract void updateUserAfterOrder(User user, Bicycle bicycle) throws DaoException;
}
