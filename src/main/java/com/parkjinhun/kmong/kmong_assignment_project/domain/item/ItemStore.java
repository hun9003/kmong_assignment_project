package com.parkjinhun.kmong.kmong_assignment_project.domain.item;

public interface ItemStore {
    Item store(Item initItem);

    void deleteAll();
}
