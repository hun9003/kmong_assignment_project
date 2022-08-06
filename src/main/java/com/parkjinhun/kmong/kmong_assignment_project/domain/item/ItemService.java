package com.parkjinhun.kmong.kmong_assignment_project.domain.item;

import org.springframework.data.domain.Pageable;

public interface ItemService {
    String registerItem(ItemCommand.RegisterItemRequest request, String accessToken);
    ItemInfo.Main retrieveItemInfo(String itemToken);
    void deleteAllItem();

    ItemInfo.ItemList retrieveAllItemInfo(String keyword, Pageable pageable);
}
