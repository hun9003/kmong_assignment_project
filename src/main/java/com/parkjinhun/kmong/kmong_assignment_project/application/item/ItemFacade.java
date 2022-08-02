package com.parkjinhun.kmong.kmong_assignment_project.application.item;

import com.parkjinhun.kmong.kmong_assignment_project.domain.item.ItemCommand;
import com.parkjinhun.kmong.kmong_assignment_project.domain.item.ItemInfo;
import com.parkjinhun.kmong.kmong_assignment_project.domain.item.ItemService;
import com.parkjinhun.kmong.kmong_assignment_project.interfaces.item.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemFacade {
    private final ItemService itemService;

    public String registerItem(ItemCommand.RegisterItemRequest request, String accessToken) {
        return itemService.registerItem(request, accessToken);
    }

    public void changeOnSaleItem(ItemDto.ChangeStatusItemRequest request) {
        itemService.changeOnSale(request);
    }

    public void changeEndOfSaleItem(ItemDto.ChangeStatusItemRequest request) {
        itemService.changeEndOfSale(request);
    }

    public ItemInfo.Main retrieveItemInfo(String itemToken) {
        return itemService.retrieveItemInfo(itemToken);
    }
}
