package com.rustdv.marketplace.entity.embeddable;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    private String city;
    private String street;
    private String houseNumber;


}
