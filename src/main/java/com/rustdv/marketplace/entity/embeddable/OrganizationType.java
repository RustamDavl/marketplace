package com.rustdv.marketplace.entity.embeddable;

import com.rustdv.marketplace.exception.NoSuchElementInEnumException;

import java.util.Arrays;
import java.util.List;

public enum OrganizationType {
    OOO("ООО"),
    IP("ИП"),
    OAO("ОАО");

    private String organization;

    private OrganizationType(String organization) {
        this.organization = organization;
    }
    public static OrganizationType getOrganizationType(String organization) {
        List<OrganizationType> organizationList = Arrays.stream(OrganizationType.values())
                .filter(organizationType -> organizationType.organization.equals(organization))
                .toList();

        if(organizationList.isEmpty())
            throw new NoSuchElementInEnumException("there is no such organization type");

        return organizationList.get(0);

    }
}
