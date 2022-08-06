package com.parkjinhun.kmong.kmong_assignment_project.domain.order;

import com.parkjinhun.kmong.kmong_assignment_project.interfaces.order.OrderDto;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    String registerOrder(OrderCommand.RegisterOrder registerOrder, String accessToken);

    OrderInfo.Main retrieveOrder(String accessToken, String orderToken);

    OrderInfo.OrderList retrieveAllOrder(String accessToken, OrderDto.SearchOrderRequest searchRequest, Pageable pageable);
}