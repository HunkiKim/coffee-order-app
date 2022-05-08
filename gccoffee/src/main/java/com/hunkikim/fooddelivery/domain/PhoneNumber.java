package com.hunkikim.fooddelivery.domain;

import java.util.regex.Pattern;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class PhoneNumber {
    private final String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        Assert.notNull(phoneNumber, "phone number should not be null");
        Assert.isTrue(phoneNumber.length() == 11 , "phone number length must be 13 characters");
        Assert.isTrue(checkPhoneNumber(phoneNumber), "Invalid phone Number");
        this.phoneNumber = phoneNumber;
    }

    private static boolean checkPhoneNumber(String phoneNumber) {
        return Pattern.matches("^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$",phoneNumber);
    }
}
