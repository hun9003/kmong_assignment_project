package com.parkjinhun.kmong.kmong_assignment_project.infrastructure.item;

import com.parkjinhun.kmong.kmong_assignment_project.domain.item.Item;
import com.parkjinhun.kmong.kmong_assignment_project.domain.item.ItemStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemStoreImpl implements ItemStore {
    private final ItemRepository itemRepository;

    @Override
    public Item store(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void deleteAll() {
        itemRepository.deleteAll();
    }
}
