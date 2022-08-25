package com.parkjinhun.shopping.shopping_mall_project.domain.item;


import com.parkjinhun.shopping.shopping_mall_project.domain.item.optiongroup.ItemOptionGroup;

import java.util.List;

public interface ItemOptionSeriesFactory {
    List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest request, Item item);

    void deleteAll();
}
