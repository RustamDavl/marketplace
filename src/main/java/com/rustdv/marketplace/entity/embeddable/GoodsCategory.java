package com.rustdv.marketplace.entity.embeddable;

import com.rustdv.marketplace.exception.NoSuchElementInEnumException;

import java.util.Arrays;
import java.util.List;

public enum GoodsCategory {

    SPORT("спорт"),
    CLOTHES("одежда"),
    MUSIC("музыка"),
    ADULT("взрослые");

    private String category;

    private GoodsCategory(String category) {
        this.category = category;
    }


    public static GoodsCategory goodsCategory(String category) {

        List<GoodsCategory> goodsCategories = Arrays.stream(GoodsCategory.values())
                .filter(goodsCategory -> goodsCategory.category.equals(category))
                .toList();

        if (goodsCategories.isEmpty())
            throw new NoSuchElementInEnumException("there is no such category");
        return goodsCategories.get(0);
    }

    @Override
    public String toString() {
        return this.category;
    }
}
