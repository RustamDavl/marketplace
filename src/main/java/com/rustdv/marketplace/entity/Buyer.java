package com.rustdv.marketplace.entity;


import com.rustdv.marketplace.entity.embeddable.Sex;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "buyer")
public class Buyer extends User<Long> {

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "birth_date")
    private LocalDate birthDate;

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
