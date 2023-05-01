package com.rustdv.marketplace.entity;

import com.rustdv.marketplace.entity.embeddable.OrganizationType;
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
public class Seller extends User<Long> {

    @Enumerated(EnumType.STRING)
    @Column(name = "organization_type", nullable = false)
    private OrganizationType organizationType;

    @Column(unique = true, length = 12, nullable = false)
    private String inn;

    @Column(name = "magazine_name", unique = true, nullable = false)
    private String magazineName;

    @Builder.Default
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Goods> goods = new ArrayList<>();

    public void addGoods(Goods goods) {
        this.goods.add(goods);
        goods.setSeller(this);
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
        return getClass().hashCode();
    }
}
