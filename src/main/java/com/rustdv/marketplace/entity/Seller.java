package com.rustdv.marketplace.entity;

import com.rustdv.marketplace.entity.embeddable.OwnershipForm;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "seller")
public class Seller extends User {

    @Enumerated(EnumType.STRING)
    @Column(name = "ownership_form", nullable = false)
    private OwnershipForm ownershipForm;

    @ToString.Exclude
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Shop> shops = new ArrayList<>();


    public void addShop(Shop shop) {
        this.shops.add(shop);
        shop.setSeller(this);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Seller seller = (Seller) o;
        return getId() != null && Objects.equals(getId(), seller.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.email);
    }
}
