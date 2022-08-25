package com.parkjinhun.shopping.shopping_mall_project.domain.item.option;

public interface ItemOptionStore {
    void store(ItemOption itemOption);

    void deleteAll();
}
