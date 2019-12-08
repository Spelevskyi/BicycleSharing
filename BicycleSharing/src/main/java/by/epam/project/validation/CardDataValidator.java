package by.epam.project.validation;

import java.util.regex.Pattern;

import by.epam.project.util.Constants;

public class CardDataValidator {

    private static final String CARD_CODE_PATTERN = "^[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}$";
    private static final String CARD_DATE_PATTERN = "^[0-9]{4}-[0-9]{2}-[0-9]{2}";
    private static final String CARD_ = "^[0-9]{4}-[0-9]{2}-[0-9]{2}";

    public static boolean isCardCodeValid(String code) {
        return code != null && !code.isEmpty() && Pattern.matches(CARD_CODE_PATTERN, code);
    }

    public static boolean isCardMasterValid(String cardMaster) {
        return cardMaster.equals(Constants.BELARUSBANK) || cardMaster.equals(Constants.BELINVESTBANK)
                || cardMaster.equals(Constants.ALFABANK);
    }

    public static boolean isCardDateValid(String date) {
        return date != null && !date.isEmpty() && Pattern.matches(CARD_DATE_PATTERN, date);
    }

    public static boolean isCardNumberValid(int number) {
        return number > 0 && number < 999;
    }
}
