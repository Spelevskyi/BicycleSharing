package by.epam.project.util;

import java.util.Random;

public class ConfirmationCodeGenerator {

    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 100000;

    public static int generateCode() {
        Random rand = new Random();
        return rand.nextInt((MAX_VALUE - MIN_VALUE) + 1) + MIN_VALUE;
    }
}
