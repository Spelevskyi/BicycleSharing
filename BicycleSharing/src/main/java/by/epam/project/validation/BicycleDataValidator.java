package by.epam.project.validation;

import java.util.regex.Pattern;

import by.epam.project.entity.bicycle.StateType;

public class BicycleDataValidator {

    private static final String IMAGE_PATH_PATTERN = "^.\\/image\\/[a-zA-z0-9]{1,100}.(png|jpg)$";
    private static final String BRAND_PATTERN = "[A-Z]{2,20}$";
    private static final String COLOR_PATTERN = "[A-Z]{3,20}$";

    public static boolean isBrandTypeValid(String brand) {
        return brand != null && !brand.isEmpty() && Pattern.matches(BRAND_PATTERN, brand);
    }

    public static boolean isImagePathValid(String path) {
        return path != null && !path.isEmpty();
    }

    public static boolean isColorTypeValid(String color) {
        return color != null && !color.isEmpty() && Pattern.matches(COLOR_PATTERN, color);
    }

    public static boolean isSpeedValueValid(int speed) {
        return speed > 0 && speed < 100;
    }

    public static boolean isCoordinateValid(int coordinate) {
        return coordinate > 0 && coordinate < 550;
    }

    public static boolean isStateTypeValid(String state) {
        return state.equals(StateType.BAD.toString()) || state.equals(StateType.GOOD.toString())
                || state.equals(StateType.WORN.toString());
    }

    public static boolean isStatusTypeValid(String status) {
        return status.equals("ENABLE") || status.equals("DISABLE");
    }
}
