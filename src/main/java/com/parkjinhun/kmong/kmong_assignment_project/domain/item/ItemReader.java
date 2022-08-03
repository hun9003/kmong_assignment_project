package com.parkjinhun.kmong.kmong_assignment_project.domain.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemReader {
    Item getItemBy(String itemToken);
    List<ItemInfo.ItemOptionGroupInfo> getItemOptionSeries(Item item);
    Page<Item> findItemByKeyword(String keyword, Pageable pageable);
}
