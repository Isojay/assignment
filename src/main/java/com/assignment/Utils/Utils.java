package com.assignment.Utils;

import org.springframework.stereotype.Component;

@Component
public class Utils {

    /**
     * This method generates Unique Number based on the params.
     *
     * @param generatingFor It passes short abbv. for the class UUID is generated.
     * @param quantityNumber It is the number of the unit in the Pack .
     */
    public static String uniqueNumberGenerator(String generatingFor, int quantityNumber) {
        return generatingFor + "-" + System.currentTimeMillis() + "-" + quantityNumber;
    }

}
