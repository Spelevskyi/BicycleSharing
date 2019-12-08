package by.epam.project.builder;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.entity.user.RoleType;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.util.Constants;

public class UserBuilder {

    private static final Logger logger = LogManager.getLogger(UserBuilder.class);

    // UserBuilder method for creating user entity
    public static Optional<User> createUser(ResultSet result) throws DaoException {
        logger.info("Creating user in builder method.");
        try {
            if (!result.next()) {
                logger.info("User was not found in builder method by current result set!");
                return Optional.empty();
            }
            result.beforeFirst();
            result.next();
            String firstName = result.getString(Constants.USER_FIRSTNAME);
            String lastName = result.getString(Constants.USER_LASTNAME);
            String email = result.getString(Constants.USER_EMAIL);
            String password = result.getString(Constants.USER_PASSWORD);
            String status = result.getString(Constants.USER_STATUS);
            RoleType role = RoleType.valueOf(result.getString(Constants.USER_ROLE));
            Date registrationDate = result.getDate(Constants.USER_REGISTRATION_DATE);
            int rentalAmount = result.getInt(Constants.USER_RENTAL_AMOUNT);
            Date lastRentalDate = result.getDate(Constants.USER_LAST_RENTAL_DATE);
            String phoneNumber = result.getString(Constants.USER_PHONE_NUMBER);
            boolean confirmed = result.getBoolean(Constants.CONFIRMED_USER);
            String imagePath = result.getString(Constants.IMAGE_PATH);
            BigDecimal cash = result.getBigDecimal(Constants.USER_CASH);
            boolean onRoad = result.getBoolean(Constants.USER_ONROAD);
            boolean online = result.getBoolean(Constants.USER_ONLINE);
            User user = new User(firstName, lastName, email, password, role, registrationDate, rentalAmount,
                    lastRentalDate, phoneNumber, status, confirmed, imagePath, cash);
            user.setId(result.getInt(Constants.USER_ID));
            user.setOnline(online);
            user.setOnRoad(onRoad);
            logger.info("Succesfully creating user in builder method!");
            return Optional.of(user);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    public static Optional<User> createOrderUser(ResultSet result) throws DaoException {
        logger.info("Creating user in builder method.");
        try {
            String firstName = result.getString(Constants.USER_FIRSTNAME);
            String lastName = result.getString(Constants.USER_LASTNAME);
            String email = result.getString(Constants.USER_EMAIL);
            String password = result.getString(Constants.USER_PASSWORD);
            String status = result.getString(Constants.USER_STATUS);
            RoleType role = RoleType.valueOf(result.getString(Constants.USER_ROLE));
            Date registrationDate = result.getDate(Constants.USER_REGISTRATION_DATE);
            int rentalAmount = result.getInt(Constants.USER_RENTAL_AMOUNT);
            Date lastRentalDate = result.getDate(Constants.USER_LAST_RENTAL_DATE);
            String phoneNumber = result.getString(Constants.USER_PHONE_NUMBER);
            boolean confirmed = result.getBoolean(Constants.CONFIRMED_USER);
            String imagePath = result.getString(Constants.IMAGE_PATH);
            BigDecimal cash = result.getBigDecimal(Constants.USER_CASH);
            boolean onRoad = result.getBoolean(Constants.USER_ONROAD);
            boolean online = result.getBoolean(Constants.USER_ONLINE);
            User user = new User(firstName, lastName, email, password, role, registrationDate, rentalAmount,
                    lastRentalDate, phoneNumber, status, confirmed, imagePath, cash);
            user.setId(result.getInt(Constants.USER_ID));
            user.setOnline(online);
            user.setOnRoad(onRoad);
            logger.info("Succesfully creating user in builder method!");
            return Optional.of(user);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    public static Optional<User> createUserWithCardsAndOrders(ResultSet result) throws DaoException {
        try {
            result.beforeFirst();
            result.next();
            String firstName = result.getString(Constants.USER_FIRSTNAME);
            String lastName = result.getString(Constants.USER_LASTNAME);
            String email = result.getString(Constants.USER_EMAIL);
            String password = result.getString(Constants.USER_PASSWORD);
            String status = result.getString(Constants.USER_STATUS);
            RoleType role = RoleType.valueOf(result.getString(Constants.USER_ROLE));
            Date registrationDate = result.getDate(Constants.USER_REGISTRATION_DATE);
            int rentalAmount = result.getInt(Constants.USER_RENTAL_AMOUNT);
            Date lastRentalDate = result.getDate(Constants.USER_LAST_RENTAL_DATE);
            String phoneNumber = result.getString(Constants.USER_PHONE_NUMBER);
            boolean confirmed = result.getBoolean(Constants.CONFIRMED_USER);
            String imagePath = result.getString(Constants.IMAGE_PATH);
            BigDecimal cash = result.getBigDecimal(Constants.USER_CASH);
            boolean onRoad = result.getBoolean(Constants.USER_ONROAD);
            boolean online = result.getBoolean(Constants.USER_ONLINE);
            User user = new User(firstName, lastName, email, password, role, registrationDate, rentalAmount,
                    lastRentalDate, phoneNumber, status, confirmed, imagePath, cash);
            user.setId(result.getInt(Constants.USER_ID));
            user.setOnline(online);
            user.setOnRoad(onRoad);
            user.setCards(CardBuilder.createUserCards(result));
            user.setOrders(RentalOrderBuilder.createOrders(result));
            return Optional.ofNullable(user);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    public static Optional<User> createUserWithCards(ResultSet result) throws DaoException {
        try {
            logger.info("Creating user entity with card list");
            if (!result.next()) {

                return Optional.empty();
            }
            result.beforeFirst();
            result.next();
            String firstName = result.getString(Constants.USER_FIRSTNAME);
            String lastName = result.getString(Constants.USER_LASTNAME);
            String email = result.getString(Constants.USER_EMAIL);
            String password = result.getString(Constants.USER_PASSWORD);
            String status = result.getString(Constants.USER_STATUS);
            RoleType role = RoleType.valueOf(result.getString(Constants.USER_ROLE));
            Date registrationDate = result.getDate(Constants.USER_REGISTRATION_DATE);
            int rentalAmount = result.getInt(Constants.USER_RENTAL_AMOUNT);
            Date lastRentalDate = result.getDate(Constants.USER_LAST_RENTAL_DATE);
            String phoneNumber = result.getString(Constants.USER_PHONE_NUMBER);
            boolean confirmed = result.getBoolean(Constants.CONFIRMED_USER);
            String imagePath = result.getString(Constants.IMAGE_PATH);
            BigDecimal cash = result.getBigDecimal(Constants.USER_CASH);
            boolean onRoad = result.getBoolean(Constants.USER_ONROAD);
            boolean online = result.getBoolean(Constants.USER_ONLINE);
            User user = new User(firstName, lastName, email, password, role, registrationDate, rentalAmount,
                    lastRentalDate, phoneNumber, status, confirmed, imagePath, cash);
            user.setId(result.getInt(Constants.USER_ID));
            user.setOnline(online);
            user.setOnRoad(onRoad);
            user.setCards(CardBuilder.createUserCards(result));
            logger.info("Succesfully creating user entity with card list.");
            return Optional.ofNullable(user);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    public static Optional<User> createUserWithOrders(ResultSet result) throws DaoException {
        try {
            logger.info("Creating user entity with order list");
            if (!result.next()) {
                return Optional.empty();
            }
            result.beforeFirst();
            result.next();
            String firstName = result.getString(Constants.USER_FIRSTNAME);
            String lastName = result.getString(Constants.USER_LASTNAME);
            String email = result.getString(Constants.USER_EMAIL);
            String password = result.getString(Constants.USER_PASSWORD);
            String status = result.getString(Constants.USER_STATUS);
            RoleType role = RoleType.valueOf(result.getString(Constants.USER_ROLE));
            Date registrationDate = result.getDate(Constants.USER_REGISTRATION_DATE);
            int rentalAmount = result.getInt(Constants.USER_RENTAL_AMOUNT);
            Date lastRentalDate = result.getDate(Constants.USER_LAST_RENTAL_DATE);
            String phoneNumber = result.getString(Constants.USER_PHONE_NUMBER);
            boolean confirmed = result.getBoolean(Constants.CONFIRMED_USER);
            String imagePath = result.getString(Constants.IMAGE_PATH);
            BigDecimal cash = result.getBigDecimal(Constants.USER_CASH);
            boolean onRoad = result.getBoolean(Constants.USER_ONROAD);
            boolean online = result.getBoolean(Constants.USER_ONLINE);
            User user = new User(firstName, lastName, email, password, role, registrationDate, rentalAmount,
                    lastRentalDate, phoneNumber, status, confirmed, imagePath, cash);
            user.setId(result.getInt(Constants.USER_ID));
            user.setOnline(online);
            user.setOnRoad(onRoad);
            user.setOrders(RentalOrderBuilder.createUserOrders(result));
            logger.info("Succesfully creating user entity with order list.");
            return Optional.ofNullable(user);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    public static Optional<User> createUserWithActiveOrder(ResultSet result) throws DaoException {
        try {
            result.beforeFirst();
            result.next();
            String firstName = result.getString(Constants.USER_FIRSTNAME);
            String lastName = result.getString(Constants.USER_LASTNAME);
            String email = result.getString(Constants.USER_EMAIL);
            String password = result.getString(Constants.USER_PASSWORD);
            String status = result.getString(Constants.USER_STATUS);
            RoleType role = RoleType.valueOf(result.getString(Constants.USER_ROLE));
            Date registrationDate = result.getDate(Constants.USER_REGISTRATION_DATE);
            int rentalAmount = result.getInt(Constants.USER_RENTAL_AMOUNT);
            Date lastRentalDate = result.getDate(Constants.USER_LAST_RENTAL_DATE);
            String phoneNumber = result.getString(Constants.USER_PHONE_NUMBER);
            boolean confirmed = result.getBoolean(Constants.CONFIRMED_USER);
            String imagePath = result.getString(Constants.IMAGE_PATH);
            BigDecimal cash = result.getBigDecimal(Constants.USER_CASH);
            boolean onRoad = result.getBoolean(Constants.USER_ONROAD);
            boolean online = result.getBoolean(Constants.USER_ONLINE);
            User user = new User(firstName, lastName, email, password, role, registrationDate, rentalAmount,
                    lastRentalDate, phoneNumber, status, confirmed, imagePath, cash);
            user.setId(result.getInt(Constants.USER_ID));
            user.setOnline(online);
            user.setOnRoad(onRoad);
            user.setActiveOrder(RentalOrderBuilder.createOrder(result).get());
            return Optional.ofNullable(user);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    public static ArrayList<User> createUsers(ResultSet result) throws DaoException {
        logger.info("Creating users list.");
        ArrayList<User> users = new ArrayList<>();
        try {
            if (!result.next()) {
                logger.error("Users were not found!");
            }
            result.beforeFirst();
            while (result.next()) {
                String firstName = result.getString(Constants.USER_FIRSTNAME);
                String lastName = result.getString(Constants.USER_LASTNAME);
                String email = result.getString(Constants.USER_EMAIL);
                String password = result.getString(Constants.USER_PASSWORD);
                String status = result.getString(Constants.USER_STATUS);
                RoleType role = RoleType.valueOf(result.getString(Constants.USER_ROLE));
                Date registrationDate = result.getDate(Constants.USER_REGISTRATION_DATE);
                int rentalAmount = result.getInt(Constants.USER_RENTAL_AMOUNT);
                Date lastRentalDate = result.getDate(Constants.USER_LAST_RENTAL_DATE);
                String phoneNumber = result.getString(Constants.USER_PHONE_NUMBER);
                boolean confirmed = result.getBoolean(Constants.CONFIRMED_USER);
                String imagePath = result.getString(Constants.IMAGE_PATH);
                BigDecimal cash = result.getBigDecimal(Constants.USER_CASH);
                boolean onRoad = result.getBoolean(Constants.USER_ONROAD);
                boolean online = result.getBoolean(Constants.USER_ONLINE);
                User user = new User(firstName, lastName, email, password, role, registrationDate, rentalAmount,
                        lastRentalDate, phoneNumber, status, confirmed, imagePath, cash);
                user.setId(result.getInt(Constants.USER_ID));
                user.setOnline(online);
                user.setOnRoad(onRoad);
                users.add(user);
                logger.info("User was created in builder.");
            }
            logger.info("All user were created!");
        } catch (SQLException ex) {
            throw new DaoException("Users were not found!");
        }
        return users;
    }
}
