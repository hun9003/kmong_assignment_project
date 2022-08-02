package com.parkjinhun.kmong.kmong_assignment_project.domain.item;

import com.parkjinhun.kmong.kmong_assignment_project.interfaces.item.ItemDto;

public interface ItemService {
    String registerItem(ItemCommand.RegisterItemRequest request, String accessToken);
    void changeOnSale(ItemDto.ChangeStatusItemRequest request);
    void changeEndOfSale(ItemDto.ChangeStatusItemRequest request);
    ItemInfo.Main retrieveItemInfo(String itemToken);
}
