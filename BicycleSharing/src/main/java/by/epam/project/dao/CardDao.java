package by.epam.project.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import by.epam.project.entity.card.Card;
import by.epam.project.exception.DaoException;

public abstract class CardDao extends AbstractDao<Card> {

    public abstract void addCard(int ownerId, String master, int balance, String code, int number, Date date,
            String status)
            throws DaoException;

    public abstract List<Card> findUserCards(int id) throws DaoException;

    public abstract void withdrawBalance(int id, int amount) throws DaoException;

    public abstract void deleteUserCards(int id) throws DaoException;

    public abstract boolean matchCodeNumberMaster(String code, int number, String master) throws DaoException;

    public abstract Optional<Card> findUserCard(int id, String code, int number, String master) throws DaoException;
}
