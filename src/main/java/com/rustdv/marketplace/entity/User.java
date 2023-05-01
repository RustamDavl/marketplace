package com.rustdv.marketplace.entity;

import com.rustdv.marketplace.entity.embeddable.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class User<ID extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDateTime registerAt;
    @Embedded
    private Address address;
}
