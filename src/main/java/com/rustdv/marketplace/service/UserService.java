package com.rustdv.marketplace.service;

public interface UserService<T> {

    T findByEmailAndPassword(String email, String password);

    T register(T user);
}
