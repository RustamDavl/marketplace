package com.rustdv.marketplace.unit.entity.embeddable;

import com.rustdv.marketplace.entity.embeddable.Gender;
import com.rustdv.marketplace.entity.embeddable.OrganizationType;
import com.rustdv.marketplace.exception.NoSuchElementInEnumException;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrganizationTypeTest {

    @Test
    void getOrganizationTypeShouldPass() {
        var IPOrganizationType = "ИП";
        var OOOOrganizationType = "ООО";
        var OAOOrganizationType = "ОАО";
        var IP = OrganizationType.getOrganizationType(IPOrganizationType);
        var OOO = OrganizationType.getOrganizationType(OOOOrganizationType);
        var OAO = OrganizationType.getOrganizationType(OAOOrganizationType);

        assertThat(IP).isEqualTo(OrganizationType.IP);
        assertThat(OOO).isEqualTo(OrganizationType.OOO);
        assertThat(OAO).isEqualTo(OrganizationType.OAO);

    }

    @ParameterizedTest
    @MethodSource("com.rustdv.marketplace.unit.entity.embeddable.OrganizationTypeTest#organizationsArguments")
    void getOrganizationTypeShouldThrowException(String organizationType) {

        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementInEnumException.class, () -> OrganizationType.getOrganizationType(organizationType));



    }

    static Stream<Arguments> organizationsArguments() {

        return Stream.of(
                Arguments.of("ип"),
                Arguments.of("ооа"),
                Arguments.of("фоа"),
                Arguments.of("щзщ")
        );
    }

}