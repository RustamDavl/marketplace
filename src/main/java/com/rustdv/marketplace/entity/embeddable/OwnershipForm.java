package com.rustdv.marketplace.entity.embeddable;

import com.rustdv.marketplace.exception.NoSuchElementInEnumException;

import java.util.Arrays;
import java.util.List;

public enum OwnershipForm {
    OOO("ООО"),
    IP("ИП"),
    OAO("ОАО");

    private String organization;

    private OwnershipForm(String organization) {
        this.organization = organization;
    }
    public static OwnershipForm getOwnershipForm(String organization) {
        List<OwnershipForm> organizationList = Arrays.stream(OwnershipForm.values())
                .filter(organizationType -> organizationType.organization.equals(organization))
                .toList();

        if(organizationList.isEmpty())
            throw new NoSuchElementInEnumException("there is no such organization type");

        return organizationList.get(0);

    }

    @Override
    public String toString() {
        return this.organization;
    }
}
