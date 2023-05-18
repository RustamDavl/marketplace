package com.rustdv.marketplace.service;

public interface UserService<T, ID> {

    T findByEmailAndPassword(String email, String password);

    T register(T user);
}
