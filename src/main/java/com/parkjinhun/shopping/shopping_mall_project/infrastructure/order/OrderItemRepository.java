package com.parkjinhun.shopping.shopping_mall_project.infrastructure.order;


import com.parkjinhun.shopping.shopping_mall_project.domain.order.item.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
