package com.parkjinhun.kmong.kmong_assignment_project.domain.order;

public interface OrderService {
    String registerOrder(OrderCommand.RegisterOrder registerOrder, String accessToken);

    OrderInfo.Main retrieveOrder(String accessToken);

}