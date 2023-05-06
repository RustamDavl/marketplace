package com.rustdv.marketplace.entity.embeddable;

import com.rustdv.marketplace.exception.NoSuchElementInEnumException;

import java.util.Arrays;

public enum Gender {
    MALE("мужчина"),
    FEMALE("женщина");

    private String gender;

    private Gender(String gender) {
        this.gender = gender;
    }

    public static Gender getGender(String requestGender) {
        if(requestGender == null)
            throw new RuntimeException("incorrect request gender");

        var genderList = Arrays.stream(Gender.values())
                .filter(s -> s.gender.equals(requestGender))
                .toList();
        if (genderList.isEmpty())
            throw new NoSuchElementInEnumException("there is no such gender");

        return genderList.get(0);

    }
}
