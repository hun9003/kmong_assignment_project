package com.parkjinhun.kmong.kmong_assignment_project.infrastructure.order;

import com.parkjinhun.kmong.kmong_assignment_project.domain.order.item.OrderItemOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemOptionGroupRepository extends JpaRepository<OrderItemOptionGroup, Long> {
}
