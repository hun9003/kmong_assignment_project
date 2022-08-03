package com.parkjinhun.kmong.kmong_assignment_project.infrastructure.order;


import com.parkjinhun.kmong.kmong_assignment_project.domain.order.item.OrderItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemOptionRepository extends JpaRepository<OrderItemOption, Long> {
}
