package com.parkjinhun.kmong.kmong_assignment_project.application.order;

import com.parkjinhun.kmong.kmong_assignment_project.domain.order.OrderCommand;
import com.parkjinhun.kmong.kmong_assignment_project.domain.order.OrderInfo;
import com.parkjinhun.kmong.kmong_assignment_project.domain.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFacade {
    private final OrderService orderService;

    public String registerOrder(OrderCommand.RegisterOrder registerOrder, String accessToken) {
        return orderService.registerOrder(registerOrder, accessToken);
    }

    public OrderInfo.Main retrieveOrder(String accessToken) {
        return orderService.retrieveOrder(accessToken);
    }

}
