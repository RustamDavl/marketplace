package com.rustdv.marketplace.dto.read;

import lombok.Builder;
import lombok.Value;


import java.time.LocalDate;

@Value
@Builder
public class ReadBuyerDto {

    Long id;
    String email;
    String phoneNumber;
    String registerAt;
    String city;
    String street;
    String houseNumber;
    String gender;
    LocalDate birthDate;
}
