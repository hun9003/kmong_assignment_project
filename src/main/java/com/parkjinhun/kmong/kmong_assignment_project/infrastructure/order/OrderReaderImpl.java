package com.parkjinhun.kmong.kmong_assignment_project.infrastructure.order;

import com.parkjinhun.kmong.kmong_assignment_project.common.exception.EntityNotFoundException;
import com.parkjinhun.kmong.kmong_assignment_project.domain.order.Order;
import com.parkjinhun.kmong.kmong_assignment_project.domain.order.OrderReader;
import com.parkjinhun.kmong.kmong_assignment_project.interfaces.order.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderReaderImpl implements OrderReader {

    private final OrderRepository orderRepository;

    @Override
    public Order getOrder(String orderToken) {
        return orderRepository.findByOrderToken(orderToken)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Order> getOrderList(String memberId, OrderDto.SearchOrderRequest searchRequest, Pageable pageable) {
        LocalDate startDate = LocalDate.parse(searchRequest.getStartDate());
        LocalDate endDate = LocalDate.parse(searchRequest.getEndDate());
        ZonedDateTime start = startDate.atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime end = endDate.atStartOfDay(ZoneId.systemDefault());
        return orderRepository.findByMemberIdAndStatusAndCreatedAtBetween(memberId, searchRequest.getStatus(), start, end, pageable);
    }
}
