package com.rustdv.marketplace.dto.read;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
@Builder
public class ReadSellerDto {

    Long id;
    String email;
    String magazineName;
    LocalDateTime registerAt;
    String ownershipForm;
}
