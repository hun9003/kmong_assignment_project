package com.parkjinhun.shopping.shopping_mall_project.domain.order;

import com.parkjinhun.shopping.shopping_mall_project.interfaces.order.OrderDto;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    String registerOrder(OrderCommand.RegisterOrder registerOrder, String accessToken);

    OrderInfo.Main retrieveOrder(String accessToken, String orderToken);

    OrderInfo.OrderList retrieveAllOrder(String accessToken, OrderDto.SearchOrderRequest searchRequest, Pageable pageable);
}