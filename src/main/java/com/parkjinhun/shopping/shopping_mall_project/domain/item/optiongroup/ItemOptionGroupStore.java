package com.parkjinhun.shopping.shopping_mall_project.domain.item.optiongroup;

public interface ItemOptionGroupStore {
    ItemOptionGroup store(ItemOptionGroup itemOptionGroup);

    void deleteAll();
}
