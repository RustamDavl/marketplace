package com.rustdv.marketplace.entity;

import com.rustdv.marketplace.entity.embeddable.GoodsCategory;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
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
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private GoodsCategory goodsCategory;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Builder.Default
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Goods> goods = new ArrayList<>();

    public void addGoods(Goods goods) {
        this.goods.add(goods);
        goods.setShop(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Shop shop = (Shop) o;
        return id != null && Objects.equals(id, shop.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
