package com.parkjinhun.shopping.shopping_mall_project.domain.order;


import com.parkjinhun.shopping.shopping_mall_project.domain.order.item.OrderItem;

import java.util.List;

public interface OrderItemSeriesFactory {
    List<OrderItem> store(Order order, OrderCommand.RegisterOrder requestOrder);
}
