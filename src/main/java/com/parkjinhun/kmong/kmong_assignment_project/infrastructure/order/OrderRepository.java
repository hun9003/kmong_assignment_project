package com.parkjinhun.kmong.kmong_assignment_project.infrastructure.order;


import com.parkjinhun.kmong.kmong_assignment_project.domain.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderToken(String orderToken);
    Page<Order> findByMemberIdAndStatusAndCreatedAtBetween(String memberId, Order.Status status, ZonedDateTime start, ZonedDateTime end, Pageable pageable);
}
