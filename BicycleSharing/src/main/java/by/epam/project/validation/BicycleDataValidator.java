package by.epam.project.validation;

import java.util.regex.Pattern;

import by.epam.project.util.Constants;

public class BicycleDataValidator {

    private static final String IMAGE_PATH_PATTERN = "^.\\/image\\/[a-zA-z0-9]{1,100}.(png|jpg)$";
    private static final String BRAND_PATTERN = "[A-Z]{2,20}$";

    public static boolean isBrandTypeValid(String brand) {
        return brand != null && !brand.isEmpty() && Pattern.matches(BRAND_PATTERN, brand);
    }

    public static boolean isImagePathValid(String path) {
        return path != null && !path.isEmpty() && Pattern.matches(IMAGE_PATH_PATTERN, path);
    }

    public static boolean isColorTypeValid(String color) {
        return color != null && !color.isEmpty()
                && (color.equals(Constants.YELLOW) || color.equals(Constants.BLACK) || color.equals(Constants.WHITE)
                        || color.equals(Constants.BLUE) || color.equals(Constants.GREEN)
                        || color.equals(Constants.ORANGE) || color.equals(Constants.PURPLE)
                        || color.equals(Constants.GRAY) || color.equals(Constants.RED));
    }

    public static boolean isSpeedValueValid(int speed) {
        return speed > 0 && speed < 100;
    }

    public static boolean isCoordinateValid(int coordinate) {
        return coordinate > 0 && coordinate < 550;
    }

    public static boolean isStateTypeValid(String state) {
        return state != null && !state.isEmpty()
                && (state.equals(Constants.BAD) || state.equals(Constants.GOOD) || state.equals(Constants.WORN));
    }

    public static boolean isStatusTypeValid(String status) {
        return status.equals(Constants.ENABLE) || status.equals(Constants.DISABLE);
    }
}
