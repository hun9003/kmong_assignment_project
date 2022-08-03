package com.parkjinhun.kmong.kmong_assignment_project.application.item;

import com.parkjinhun.kmong.kmong_assignment_project.domain.item.ItemCommand;
import com.parkjinhun.kmong.kmong_assignment_project.domain.item.ItemInfo;
import com.parkjinhun.kmong.kmong_assignment_project.domain.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemFacade {
    private final ItemService itemService;

    public String registerItem(ItemCommand.RegisterItemRequest request, String accessToken) {
        return itemService.registerItem(request, accessToken);
    }

    public ItemInfo.Main retrieveItemInfo(String itemToken) {
        return itemService.retrieveItemInfo(itemToken);
    }

    public void deleteAllItem() {
        itemService.deleteAllItem();
    }

    public List<ItemInfo.Main> retrieveAllItemInfo(String keyword, Pageable pageable) {
        return itemService.retrieveAllItemInfo(keyword, pageable);
    }
}
