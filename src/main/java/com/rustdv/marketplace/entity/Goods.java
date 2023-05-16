package com.rustdv.marketplace.entity;

import com.rustdv.marketplace.entity.embeddable.GoodsCategory;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    @ToString.Exclude
    private Shop shop;

    @Column(nullable = false)
    private Integer amount;

    @Builder.Default
    @OneToMany(mappedBy = "goods", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Cart> cart = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Goods goods = (Goods) o;
        return id != null && Objects.equals(id, goods.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
