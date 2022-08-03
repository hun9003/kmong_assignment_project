package com.parkjinhun.kmong.kmong_assignment_project.infrastructure.item;


import com.parkjinhun.kmong.kmong_assignment_project.domain.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByItemToken(String itemToken);
    Page<Item> findItemByItemNameContaining(String keyword, Pageable pageable);
}
