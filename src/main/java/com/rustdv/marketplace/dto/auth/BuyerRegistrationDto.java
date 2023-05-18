package com.rustdv.marketplace.dto.auth;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;



@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BuyerRegistrationDto  {

    @Email
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    @Size(min = 11, max = 11)
    private String phoneNumber;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotBlank
    private String houseNumber;

    @NotBlank
    private String gender;

    @DateTimeFormat
    private LocalDate birthDate;


}

