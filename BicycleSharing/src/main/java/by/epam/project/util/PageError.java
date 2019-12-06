package by.epam.project.util;

public class PageError {

    private static String[] error = new String[2];

    public static final String[] getError(String isError, String value) {
        error[0] = isError;
        error[1] = value;
        return error;
    }

}
