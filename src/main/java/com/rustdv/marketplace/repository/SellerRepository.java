package com.rustdv.marketplace.repository;

import com.rustdv.marketplace.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {


    Optional<Seller> findByEmail(String email);
}
