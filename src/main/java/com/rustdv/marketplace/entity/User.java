package com.rustdv.marketplace.entity;

import com.rustdv.marketplace.entity.embeddable.Address;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
@MappedSuperclass
public abstract class User<ID extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;
    protected String email;
    protected String password;
    protected String phoneNumber;
    @CreationTimestamp
    protected LocalDateTime registerAt;
    @Embedded
    protected Address address;
}
