package com.rustdv.marketplace.unit.entity.embeddable;

import com.rustdv.marketplace.entity.embeddable.OwnershipForm;
import com.rustdv.marketplace.exception.NoSuchElementInEnumException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class OwnershipFormTest {

    @Test
    void getOrganizationTypeShouldPass() {
        var IPOrganizationType = "ИП";
        var OOOOrganizationType = "ООО";
        var OAOOrganizationType = "ОАО";
        var IP = OwnershipForm.getOwnershipForm(IPOrganizationType);
        var OOO = OwnershipForm.getOwnershipForm(OOOOrganizationType);
        var OAO = OwnershipForm.getOwnershipForm(OAOOrganizationType);

        assertThat(IP).isEqualTo(OwnershipForm.IP);
        assertThat(OOO).isEqualTo(OwnershipForm.OOO);
        assertThat(OAO).isEqualTo(OwnershipForm.OAO);

    }

    @ParameterizedTest
    @MethodSource("com.rustdv.marketplace.unit.entity.embeddable.OrganizationTypeTest#organizationsArguments")
    void getOrganizationTypeShouldThrowException(String organizationType) {

        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementInEnumException.class, () -> OwnershipForm.getOwnershipForm(organizationType));



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