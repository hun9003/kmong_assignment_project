package com.parkjinhun.kmong.kmong_assignment_project.domain.item;


import com.parkjinhun.kmong.kmong_assignment_project.domain.item.optiongroup.ItemOptionGroup;

import java.util.List;

public interface ItemOptionSeriesFactory {
    List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest request, Item item);
}
