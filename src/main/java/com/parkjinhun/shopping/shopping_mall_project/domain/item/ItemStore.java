package com.parkjinhun.shopping.shopping_mall_project.domain.item;

public interface ItemStore {
    Item store(Item initItem);

    void deleteAll();
}
