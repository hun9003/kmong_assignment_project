package com.parkjinhun.shopping.shopping_mall_project.infrastructure.order;

import com.parkjinhun.shopping.shopping_mall_project.domain.order.item.OrderItemOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemOptionGroupRepository extends JpaRepository<OrderItemOptionGroup, Long> {
}
