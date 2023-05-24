package com.rustdv.marketplace.entity;

import com.rustdv.marketplace.entity.embeddable.PurchaseStatus;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cart",
        uniqueConstraints = @UniqueConstraint(columnNames = {
                "buyer_id", "goods_id"
        }))
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    @ToString.Exclude
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    @ToString.Exclude
    private Goods goods;

    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "purchaseStatus")
    private PurchaseStatus purchaseStatus;

    public void addBuyer(Buyer buyer) {
        this.buyer = buyer;
        buyer.getCart().add(this);
    }

    public void addGoods(Goods goods) {
        this.goods = goods;
        goods.getCart().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cart cart = (Cart) o;
        return id != null && Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyer.hashCode(), goods.hashCode(), purchaseStatus, amount);
    }
}
