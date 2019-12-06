package by.epam.project.validation;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class PriceListValidator {

    private static final String BRAND_PATTERN = "[A-Z]{2,20}$";

    public static boolean isBrandTypeValid(String brand) {
        return brand != null && !brand.isEmpty() && Pattern.matches(BRAND_PATTERN, brand);
    }

    public static boolean isPriceValueValid(BigDecimal price) {
        return price.doubleValue() > 0 && price.doubleValue() < 10000;
    }
}
