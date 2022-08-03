package com.parkjinhun.kmong.kmong_assignment_project.infrastructure.order;


import com.parkjinhun.kmong.kmong_assignment_project.domain.order.item.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
