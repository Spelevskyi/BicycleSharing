package by.epam.project.validation;

import java.util.regex.Pattern;

public class UserDataValidator {
    
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String FIRSTNAME_PATTERN = "^[A-Z][a-zA-Z]{2,20}$";
    private static final String LASTNAME_PATTERN = "^[A-Z][a-zA-Z]{5,20}$";
    private static final String PASSWORD_PATTERN = "^[0-9a-zA-Z]{4,20}";
    private static final String PHONE_NUMBER_PATTERN = "(\\+375|80) (29|25|44|33) (\\d{3})-(\\d{2})-(\\d{2})$";
    private static final String IMAGE_PATH_PATTERN = "^.\\/image\\/[a-zA-z0-9]{1,100}.(png|jpg)$";

    public static boolean isEmailValid(String email) {
        return email != null && !email.isEmpty() && Pattern.matches(EMAIL_PATTERN, email);
    }
    
    public static boolean isFirstNameValid(String firstName) {
        return firstName != null && !firstName.isEmpty() && Pattern.matches(FIRSTNAME_PATTERN, firstName);
    }

    public static boolean isLastNameValid(String lastName) {
        return lastName != null && !lastName.isEmpty() && Pattern.matches(LASTNAME_PATTERN, lastName);
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber != null && !phoneNumber.isEmpty() && Pattern.matches(PHONE_NUMBER_PATTERN, phoneNumber);
    }
    
    public static boolean isPasswordValid(String password) {
        return password != null && !password.isEmpty() && Pattern.matches(PASSWORD_PATTERN, password);
    }
    
    public static boolean isPasswordEqual(String firstPassword, String secondPassword) {
        return isPasswordValid(firstPassword) && isPasswordValid(secondPassword)
                && firstPassword.equals(secondPassword);
    }

    public static boolean isImagePathValid(String imagePath) {
        return imagePath != null && !imagePath.isEmpty() && Pattern.matches(IMAGE_PATH_PATTERN, imagePath);
    }

}
