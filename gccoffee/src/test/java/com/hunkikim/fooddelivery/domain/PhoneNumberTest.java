package com.hunkikim.fooddelivery.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class PhoneNumberTest {

    @Test
    void phoneNumber_정상_입력_테스트() {
        //given
        String testPhoneNumber = "01038392750";
        //when
        var phoneNumber = new PhoneNumber(testPhoneNumber);
        //then
        assertThat(phoneNumber.getPhoneNumber()).isEqualTo(testPhoneNumber);
    }

    @Test
    void phoneNumber_11글자아닐때_테스트() {
        //given
        String testPhoneNumber = "0102345765";
        //then
        assertThatCode(() -> {
            new PhoneNumber(testPhoneNumber);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void phoneNumber_공백_방지_테스트() {
        assertThatCode(() -> {
            new PhoneNumber("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void phoneNumber_정규표현식_테스트() {
        assertThatCode(() -> {
            new PhoneNumber("0102345a113");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}