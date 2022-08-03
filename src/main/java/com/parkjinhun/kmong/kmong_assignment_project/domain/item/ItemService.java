package com.parkjinhun.kmong.kmong_assignment_project.domain.item;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {
    String registerItem(ItemCommand.RegisterItemRequest request, String accessToken);
    ItemInfo.Main retrieveItemInfo(String itemToken);
    void deleteAllItem();

    List<ItemInfo.Main> retrieveAllItemInfo(String keyword, Pageable pageable);
}
