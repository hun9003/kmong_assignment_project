package com.parkjinhun.kmong.kmong_assignment_project.domain.order;


import com.parkjinhun.kmong.kmong_assignment_project.domain.order.item.OrderItem;
import com.parkjinhun.kmong.kmong_assignment_project.domain.order.item.OrderItemOption;
import com.parkjinhun.kmong.kmong_assignment_project.domain.order.item.OrderItemOptionGroup;

public interface OrderStore {
    Order store(Order order);
    OrderItem store(OrderItem orderItem);
    OrderItemOptionGroup store(OrderItemOptionGroup orderItemOptionGroup);
    OrderItemOption store(OrderItemOption orderItemOption);
}
