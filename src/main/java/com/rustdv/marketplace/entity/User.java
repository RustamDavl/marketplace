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
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(unique = true)
    protected String email;
    protected String password;
    @Column(unique = true)
    protected String phoneNumber;
    @CreationTimestamp
    protected LocalDateTime registerAt;

}
