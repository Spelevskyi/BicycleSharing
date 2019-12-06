package by.epam.project.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.card.Card;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;

public abstract class UserDao extends AbstractDao<User> {

    public abstract void addUser(String firstName, String lastName, String email, String password, String role,
            Date registrationDate, int rentalAmount, Date lastRentaDate, String phoneNumber, String status,
            boolean confirmed, String imagePath) throws DaoException;

    public abstract boolean matchEmailPassword(String email, String password) throws DaoException;

    public abstract Optional<User> findUserByEmail(String email) throws DaoException;

    public abstract void updateUser(String firstName, String lastName, String phoneNumber,
            int id) throws DaoException;

    public abstract List<User> findAllUsers() throws DaoException;

    public abstract void lockUser(int id) throws DaoException;

    public abstract void unlockUser(int id) throws DaoException;

    public abstract List<User> findAllUnlocked() throws DaoException;

    public abstract void confirmUser(String email) throws DaoException;

    public abstract void changeImage(String imagePath, int id) throws DaoException;

    public abstract void replenishCash(User user, Card card) throws DaoException;

    public abstract void updateUserAfterOrder(User user, Bicycle bicycle) throws DaoException;

    public abstract Optional<User> findUserWithCards(int id) throws DaoException;

    public abstract Optional<User> findUserWithOrders(int id) throws DaoException;

    public abstract Optional<User> findUserWithActiveOrder(int id) throws DaoException;

    public abstract Optional<User> findUserWithCardsAndOrders(int id) throws DaoException;
}
