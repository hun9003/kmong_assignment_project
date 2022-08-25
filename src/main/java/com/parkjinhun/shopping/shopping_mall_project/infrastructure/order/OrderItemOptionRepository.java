package com.parkjinhun.shopping.shopping_mall_project.infrastructure.order;


import com.parkjinhun.shopping.shopping_mall_project.domain.order.item.OrderItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemOptionRepository extends JpaRepository<OrderItemOption, Long> {
}
