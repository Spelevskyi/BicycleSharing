package by.epam.project.road;

import java.util.Random;

public class DirectionIndex {

    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 8;

    public static int generateDirectionIndex() {
        Random rand = new Random();
        return rand.nextInt((MAX_VALUE - MIN_VALUE) + 1) + MIN_VALUE;
    }
}
