package com.rustdv.marketplace.repository;

import com.rustdv.marketplace.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    public Optional<Cart> findByBuyerIdAndGoodsId(Long buyerId, Long goodsId);

    List<Cart> findAllByBuyerId(Long id);
}
