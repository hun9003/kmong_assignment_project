package com.parkjinhun.shopping.shopping_mall_project.infrastructure.item.option;


import com.parkjinhun.shopping.shopping_mall_project.domain.item.option.ItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionRepository extends JpaRepository<ItemOption, Long> {
}
