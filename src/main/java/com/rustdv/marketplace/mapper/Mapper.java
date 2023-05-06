package com.rustdv.marketplace.mapper;

public interface Mapper<F,T> {

    T map(F from);
}
