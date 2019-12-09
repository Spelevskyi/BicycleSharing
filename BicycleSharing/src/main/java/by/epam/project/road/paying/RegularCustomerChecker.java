package by.epam.project.road.paying;

import java.sql.Date;

public class RegularCustomerChecker {

    /**
     * Static method for finding regular customer by date value
     * 
     * @param lastOrderDate
     * @return boolean value
     */
    public static boolean isRegularCustomer(Date lastOrderDate) {
        Date currentDate = new Date(System.currentTimeMillis());
        String currentDateArray[] = currentDate.toString().split("-");
        String lastOrderDateArray[] = lastOrderDate.toString().split("-");
        if (currentDateArray[0].equals(lastOrderDateArray[0]) && currentDateArray[1].equals(lastOrderDateArray[1])
                && ((Integer.valueOf(currentDateArray[2]) - Integer.valueOf(lastOrderDateArray[2])) <= 2)) {
            return true;
        }
        return false;
    }

}
