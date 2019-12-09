package by.epam.project.util;

public class Constants {

    // session constants
    public static final String SESSION_USER = "user";
    public static final String SESSION_ROLE = "role";
    public static final String COMMAND = "command";
    public static final String SESSION_IS_LOGIN = "is_login";

    // encoding
    public static final String ENCODING_TYPE = "MD5";

    // errors
    public static final String LOCKED_ERROR = "You are locked in sharing system besause of not paying debt. Please pay debt!";
    public static final String CONFIRMED_ERROR = "You are locked in sharing system besause of yoa are not confirmed your email. Please confirm!";
    public static final String ERROR_CAUSE = "cause";

    // bicycle colors
    public static final String RED = "RED";
    public static final String PURPLE = "PURPLE";
    public static final String BLACK = "BLACK";
    public static final String WHITE = "WHITE";
    public static final String ORANGE = "ORANGE";
    public static final String YELLOW = "YELLOW";
    public static final String BLUE = "BLUE";
    public static final String GREEN = "GREEN";
    public static final String GRAY = "GRAY";

    // bicycle states
    public static final String GOOD = "GOOD";
    public static final String WORN = "WORN";
    public static final String BAD = "BAD";

    // bank masters
    public static final String BELARUSBANK = "BELARUSBANK";
    public static final String BELINVESTBANK = "BELINVESTBANK";
    public static final String ALFABANK = "ALFABANK";

    // logic parameters amount values
    public static final int BICYCLES_PARAMETERS_AMOUNT = 0;
    public static final int ILLEGAL_DELETE_PARAMETERS_AMOUNT = 0;
    public static final int ACCOUNT_PARAMETERS_AMOUNT = 1;
    public static final int DISABLE_CARD_PARAMETERS_AMOUNT = 1;
    public static final int ENABLE_BICYCLE_PARAMETERS_AMOUNT = 1;
    public static final int DISABLE_BICYCLE_PARAMETERS_AMOUNT = 1;
    public static final int LOCK_USER_PARAMETERS_AMOUNT = 1;
    public static final int UNLOCK_USER_PARAMETERS_AMOUNT = 1;
    public static final int ENABLE_CARD_PARAMETERS_AMOUNT = 1;
    public static final int HOME_PARAMETERS_AMOUNT = 1;
    public static final int FAVORITES_PARAMETERS_AMOUNT = 1;
    public static final int START_MOVE_PARAMETERS_AMOUNT = 1;
    public static final int POINTS_PARAMETERS_AMOUNT = 0;
    public static final int BILLING_ADMIN_PARAMETERS_AMOUNT = 0;
    public static final int BILLING_USER_PARAMETERS_AMOUNT = 1;
    public static final int RENTAL_POINTS_PARAMETERS_AMOUNT = 1;
    public static final int USERS_PARAMETERS_AMOUNT = 0;
    public static final int SEND_MAIL_PARAMETERS_AMOUNT = 1;
    public static final int PAY_DEBT_PARAMETERS_AMOUNT = 2;
    public static final int CONFIRM_PARAMETERS_AMOUNT = 3;
    public static final int LOGIN_PARAMETERS_AMOUNT = 2;
    public static final int LOGOUT_PARAMETERS_AMOUNT = 1;
    public static final int CHANGE_AVATAR_PARAMETERS_AMOUNT = 2;
    public static final int ORDER_PARAMETERS_AMOUNT = 2;
    public static final int ADD_BICYCLE_LOCATION_PARAMETERS_AMOUNT = 3;
    public static final int FORGOT_PASSWORD_PARAMETERS_AMOUNT = 3;
    public static final int CHANGE_PASSWORD_PARAMETERS_AMOUNT = 3;
    public static final int CHANGE_PROFILE_PARAMETERS_AMOUNT = 4;
    public static final int BALANCE_REPLENICH_PARAMETERS_AMOUNT = 5;
    public static final int CREATE_CARD_PARAMETERS_AMOUNT = 5;
    public static final int REGISTRATION_PARAMETERS_AMOUNT = 6;
    public static final int CHANGE_BICYCLE_PARAMETERS_AMOUNT = 12;
    public static final int ADD_BICYCLE_PARAMETERS_AMOUNT = 6;
    public static final int ADD_BILLING_PARAMETERS_AMOUNT = 12;
    public static final int CHANGE_BILLING_PARAMETERS_AMOUNT = 12;

    // user constants
    public static final String USER_ID = "user.Id";
    public static final String USER_FIRSTNAME = "user.FirstName";
    public static final String USER_LASTNAME = "user.LastName";
    public static final String USER_EMAIL = "user.Email";
    public static final String USER_PASSWORD = "user.Password";
    public static final String USER_ROLE = "user.Role";
    public static final String USER_STATUS = "user.Status";
    public static final String USER_REGISTRATION_DATE = "user.RegistrationDate";
    public static final String USER_RENTAL_AMOUNT = "user.RentalAmount";
    public static final String USER_LAST_RENTAL_DATE = "user.LastRentalDate";
    public static final String USER_PHONE_NUMBER = "user.PhoneNumber";
    public static final String CONFIRMED_USER = "user.Confirmed";
    public static final String IMAGE_PATH = "user.ImagePath";
    public static final String USER_CASH = "user.Cash";
    public static final String USER_ONLINE = "user.InSystem";
    public static final String USER_ONROAD = "user.OnRoad";

    // Rental point constants
    public static final String POINT_BICYCLE_ID = "rental_point.BicycleId";
    public static final String POINT_ID = "rental_point.Id";
    public static final String POINT_X_COORDINATE = "rental_point.x_coordinate";
    public static final String POINT_Y_COORDINATE = "rental_point.y_coordinate";
    public static final String X_COORDINATE = "x_coordinate";
    public static final String Y_COORDINATE = "y_coordinate";
    // Connection pool constants

    public static final String DB_URL = "url";
    public static final String DB_USER = "user";
    public static final String DB_PASSWORD = "password";
    public static final String DB_ENCODING = "characterEncoding";
    public static final String DB_USE_UNICODE = "useUnicode";
    public static final String DB_POOL_SIZE = "poolSize";

    // RegistrationCommand constants
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "email";
    public static final String FIRST_PASSWORD = "first_password";
    public static final String SECOND_PASSWORD = "second_password";
    public static final String PASSWORD = "password";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String CONFIRMATION_CODE = "code";
    public static final String UNLOCKED = "UNLOCKED";
    public static final String LOCKED = "LOCKED";

    // add bicycle commands
    public static final String DISABLE_VALUE = "DISABLE";

    // user home page constants
    public static final String CARD_LIST = "cardList";
    public static final String ACTIVE_ORDER = "order";
    public static final String ORDER_LIST = "orderList";

    // user balance replenish constants
    public static final String REPLENISH_BALANCE_CODE = "code";
    public static final String REPLENISH_BALANCE_AMOUNT = "amount";
    public static final String REPLENISH_BALANCE_ID = "cardId";
    public static final String REPLENISH_BALANCE_NUMBER = "number";

    // Bicycle constants
    public static final String BICYCLE_ID = "bicycle.Id";
    public static final String BICYCLE_POINT_ID = "bicycle.PointId";
    public static final String BICYCLE_BRAND = "bicycle.Brand";
    public static final String BICYCLE_COLOR = "bicycle.Color";
    public static final String BICYCLE_MAX_SPEED = "bicycle.MaxSpeed";
    public static final String BICYCLE_REGISTRATION_DATE = "bicycle.CreationDate";
    public static final String BICYCLE_STATE = "bicycle.State";
    public static final String BICYCLE_IMAGE_PATH = "bicycle.ImagePath";
    public static final String BICYCLE_BILLING_ID = "bicycle.BillingId";
    public static final String BICYCLE_STATUS = "bicycle.Status";
    public static final String BICYCLE = "bicycle";
    public static final String BICYCLE_COUNT = "BicycleCount";
    public static final String BICYCLE_SELECT_ID = "bicycleId";
    
    // RentalOrder constants
    public static final String ORDER_ID = "Id";
    public static final String ORDER_RENTER_ID = "RenterId";
    public static final String ORDER_BICYCLE_ID = "BicycleId";
    public static final String ORDER_BOOKED_START_TIME = "BookedStartTime";
    public static final String ORDER_BOOKED_END_TIME = "BookedStartTime";
    public static final String ORDER_ACTUAL_START_TIME = "ActualStartTime";
    public static final String ORDER_ACTUAL_END_TIME = "ActualEndTime";
    public static final String ORDER_STATUS = "Status";
    public static final String ORDER_DIRECTION = "Direction";
    public static final String ORDER_DISTANCE = "Distance";

    // Debt constants
    public static final String DEBT_ID = "debt.Id";
    public static final String DEBTOR_ID = "DebtorId";
    public static final String DEBT_AMOUNT = "Amount";
    public static final String DEBT_DATE = "CreationDate";

    // Credit card constants
    public static final String CARD_TYPE = "CardMaster";
    public static final String CARD_OWNER_ID = "OwnerId";
    public static final String CARD_BALANCE = "Balance";
    public static final String CARD_ID = "credit_card.Id";
    public static final String CARD_CODE = "Code";
    public static final String CARD_VALIDATION_DATE = "ValidationDate";
    public static final String CARD_NUMBER = "Number";
    public static final String CARD_STATUS = "credit_card.Status";

    public static final String PRICE_LIST_ID = "billing.Id";
    public static final String PRICE_LIST_BRAND = "billing.Brand";
    public static final String PRICE_LIST_UNLOCK_PRICE = "billing.UnlockPrice";
    public static final String PRICE_LIST_PER_MINUTE = "billing.PerMinutePrice";
    public static final String PRICE_LIST_PER_HOUR = "billing.PerHourPrice";
    public static final String PRICE_LIST_THREE_HOUR_DISCOUNT = "billing.ThreeHourDiscount";
    public static final String PRICE_LIST_SIX_HOUR_DISCOUNT = "billing.SixHourDiscount";
    public static final String PRICE_LIST_NINE_HOUR_DISCOUNT = "billing.NineHourDiscount";
    public static final String PRICE_LIST_ALL_DAY = "billing.DaySale";
    public static final String PRICE_LIST_TRAVELER = "billing.TravelerDiscount";
    public static final String PRICE_LIST_NEW = "billing.NewCustomerDiscount";
    public static final String PRICE_LIST_REGULAR = "billing.RegularCustomerDiscount";
    public static final String PRICE_LIST_STAY = "billing.StayPrice";

    public static final String LIST_ID = "Id";
    public static final String LIST_BRAND = "Brand";
    public static final String LIST_UNLOCK_PRICE = "UnlockPrice";
    public static final String LIST_PER_MINUTE = "PerMinutePrice";
    public static final String LIST_PER_HOUR = "PerHourPrice";
    public static final String LIST_THREE_HOUR_DISCOUNT = "ThreeHourDiscount";
    public static final String LIST_SIX_HOUR_DISCOUNT = "SixHourDiscount";
    public static final String LIST_NINE_HOUR_DISCOUNT = "NineHourDiscount";
    public static final String LIST_ALL_DAY = "DaySale";
    public static final String LIST_TRAVELER = "TravelerDiscount";
    public static final String LIST_NEW = "NewCustomerDiscount";
    public static final String LIST_REGULAR = "RegularCustomerDiscount";
    public static final String LIST_STAY = "StayPrice";
    public static final String PRICE_LIST_CHANGE_ERROR = "change_error";

    // delete card command constants
    public static final String DELETE_CARD_ID = "id";

    // util package constants
    public static final String DATE_PATTERN = "dd-MM-yyyy HH:mm:ss";

    // Admin home page
    public static final String DEBTS = "debts";

    // Order command constants
    public static final String BICYCLE_ORDER_ID = "id";

    // Add bicycle command constants
    public static final String ADD_BICYCLE_ID = "Id";
    public static final String ADD_BICYCLE_BRAND = "Brand";
    public static final String ADD_BICYCLE_COLOR = "Color";
    public static final String ADD_BICYCLE_MAX_SPEED = "MaxSpeed";
    public static final String ADD_BICYCLE_IMAGE_PATH = "ImagePath";
    public static final String ADD_BICYCLE_STATE = "State";
    public static final String ADD_BICYCLE_STATUS = "Status";

    // Delete bicycles command constants
    public static final String DELETE_BICYCLE_ID = "id";

    // Delete billing command constants
    public static final String DELETE_BILLING_ID = "id";

    // Change billing command constants
    public static final String CHANGE_BILLING_ID = "id";

    // Change bicycle command constants
    public static final String CHANGE_BICYCLE_ID = "id";

    // Disable bicycle command constants
    public static final String DISABLE_BICYCLE_ID = "id";

    // Enable bicycle command constants
    public static final String ENABLE_BICYCLE_ID = "id";

    // Delete users command constants
    public static final String DELETE_USER_ID = "id";

    // Lock user command constants
    public static final String LOCK_USER_ID = "id";

    // Unlock user command constants
    public static final String UNLOCK_USER_ID = "id";

    // Disable bicycle command constants
    public static final String DISABLE_CARD_ID = "id";

    // Add bicycle with location command constants
    public static final String X_COORD = "X";
    public static final String Y_COORD = "Y";

    // Enable bicycle command constants
    public static final String ENABLE_CARD_ID = "id";

    // Pay debt command constants
    public static final String PAY_DEBT_ID = "id";

    // Status constants
    public static final String DISABLE = "DISABLE";
    public static final String ENABLE = "ENABLE";
    // road paying constants
    public static final int TRAVELER_RENTAL_AMOUNT = 50;
    public static final int NEW_RENTAL_AMOUNT = 5;
    public static final int THREE_HOUR = 3;
    public static final int SIX_HOUR = 6;
    public static final int NINE_HOUR = 9;
    public static final int DAY_HOURS = 24;
    public static final int PERCENTAGE_DIVISOR = 100;

    // errors constants
    public static final String REGISTER_ERROR = "register_error";
    public static final String BICYCLE_ERROR = "bicycle_error";

    public static final String USER_BILLING_ERROR = "user_billing_error";
    public static final String ADMIN_POINTS_ERROR = "point_error";

    public static final String PRICE_LIST = "priceLists";

    public static final String ACCOUNT_IMAGE_PATH = "ImagePath";

    public static final String USERS_ERROR = "users_error";
    // BicycleCommand constants
    public static final String BICYCLES = "bicycles";

    public static final String LANGUAGE = "lang";
    public static final String PREVIOUS_PATH_PAGE = "previous_path";

    // LoginCommand Constants
    public static final String USER = "user";
    public static final String ERROR = "error";
    public static final String LOGIN_ERROR = "login_error";
    public static final String CREATE_CARD_ERROR = "card_error";
    public static final String IMAGE_ERROR = "image_error";
    public static final String ADMIN_IMAGE_ERROR = "admin_image_error";
    public static final String USER_ERROR = "user_error";
    public static final String PROFILE_ERROR = "profile_error";
    public static final int INITIAL_RENTAL_AMOUNT = 1000;
    public static final String INITIAL_IMAGE_PATH = "../image/default_user.png";
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    public static final String RENTAL_POINTS = "rentalPoints";
    public static final String POINTS_ERROR = "points_error";

    public static final String JSON = "json";
    public static final String JSON_ID = "id";
    public static final String JSON_BRAND = "brand";
    public static final String JSON_COLOR = "color";
    public static final String JSON_IMAGE_PATH = "imagePath";
    public static final String JSON_STATE = "state";
    public static final String JSON_STAY_PRICE = "stay_price";
    public static final String JSON_PER_MINUTE = "per_minute";
    public static final String JSON_PER_HOUR = "per_hour";
    public static final String JSON_X_COORDINATE = "x_coordinate";
    public static final String JSON_Y_COORDINATE = "y_coordinate";

    public static final String BRAND_SORTED = "brandSorted";
    public static final String COLOR_SORTED = "colorSorted";
    
    public static final String USERS = "users";

    public static final String CARDS = "cards";

    public static final String BICYLES = "bicycles";
    public static final String NOT_LOCATED_BICYCLES = "not_located";

    public static final String UNLOCK_PRICE = "UnlockPrice";
    public static final String DIRECTION = "Direction";

    public static final String RUSSIAN_LANGUAGE = "ru";

    public static final String ENGLISH_LANGUAGE = "en";

    public static final int INITIAL_CARD_BALANCE = 10000;

    public static final String CREATE_CARD_CODE = "code";
    public static final String CREATE_CARD_MASTER = "master";
    public static final String CREATE_CARD_NUMBER = "id_number";
    public static final String CREATE_CARD_DATE = "date";

    public static final int SECONDS_CONSTANT = 3600;
    public static final int MINUTE_CONSTANT = 60;



}
