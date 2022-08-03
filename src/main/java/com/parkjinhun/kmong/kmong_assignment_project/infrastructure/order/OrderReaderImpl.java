package com.parkjinhun.kmong.kmong_assignment_project.infrastructure.order;

import com.parkjinhun.kmong.kmong_assignment_project.common.exception.EntityNotFoundException;
import com.parkjinhun.kmong.kmong_assignment_project.domain.order.Order;
import com.parkjinhun.kmong.kmong_assignment_project.domain.order.OrderReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
}
