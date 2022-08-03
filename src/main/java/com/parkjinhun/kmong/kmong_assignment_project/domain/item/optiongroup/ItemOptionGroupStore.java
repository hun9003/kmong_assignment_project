package com.parkjinhun.kmong.kmong_assignment_project.domain.item.optiongroup;

public interface ItemOptionGroupStore {
    ItemOptionGroup store(ItemOptionGroup itemOptionGroup);

    void deleteAll();
}
