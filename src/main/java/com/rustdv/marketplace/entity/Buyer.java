package com.rustdv.marketplace.entity;


import com.rustdv.marketplace.entity.embeddable.Gender;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "buyer")
public class Buyer extends User<Long> {



    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Builder.Default
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Cart> cart = new ArrayList<>();



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Buyer buyer = (Buyer) o;
        return getId() != null && Objects.equals(getId(), buyer.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
