package com.rustdv.marketplace.unit.entity.embeddable;

import com.rustdv.marketplace.entity.embeddable.Gender;
import com.rustdv.marketplace.exception.NoSuchElementInEnumException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GenderTest {


    @Test
    void getGenderShouldPass() {
        var maleGenderFromRequest = "мужчина";
        var femaleGenderFromRequest = "женщина";
        var male = Gender.getGender(maleGenderFromRequest);
        var female = Gender.getGender(femaleGenderFromRequest);

        assertThat(male).isEqualTo(Gender.MALE);
        assertThat(female).isEqualTo(Gender.FEMALE);
    }

    @ParameterizedTest
    @MethodSource("com.rustdv.marketplace.unit.entity.embeddable.GenderTest#genderArguments")
    void getGenderShouldThrowException(String gender) {

        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementInEnumException.class, () -> Gender.getGender(gender));



    }

    static Stream<Arguments> genderArguments() {

        return Stream.of(
                Arguments.of("м"),
                Arguments.of("ж")
        );
    }



}