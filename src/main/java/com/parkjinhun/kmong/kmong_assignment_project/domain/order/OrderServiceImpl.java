package com.parkjinhun.kmong.kmong_assignment_project.domain.order;

import com.parkjinhun.kmong.kmong_assignment_project.common.exception.BaseException;
import com.parkjinhun.kmong.kmong_assignment_project.common.exception.InvalidParamException;
import com.parkjinhun.kmong.kmong_assignment_project.common.response.ErrorCode;
import com.parkjinhun.kmong.kmong_assignment_project.common.util.jwt.JwtTokenProvider;
import com.parkjinhun.kmong.kmong_assignment_project.interfaces.order.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderStore orderStore;
    private final OrderReader orderReader;
    private final OrderItemSeriesFactory orderItemSeriesFactory;
    private final OrderInfoMapper orderInfoMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public String registerOrder(OrderCommand.RegisterOrder requestOrder, String accessToken) {
        if (!jwtTokenProvider.validateToken(accessToken)) throw new InvalidParamException(ErrorCode.MEMBER_FAIL_INVALID_TOKEN);
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
        String memberId = authentication.getName();
        Order order = orderStore.store(requestOrder.toEntity(memberId));
        orderItemSeriesFactory.store(order, requestOrder);
        return order.getOrderToken();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderInfo.Main retrieveOrder(String accessToken, String orderToken) {
        if (!jwtTokenProvider.validateToken(accessToken)) throw new InvalidParamException(ErrorCode.MEMBER_FAIL_INVALID_TOKEN);
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
        String memberId = authentication.getName();
        var order = orderReader.getOrder(orderToken);
        if(!order.getMemberId().equals(memberId)) throw new BaseException(ErrorCode.MEMBER_BAD_PERMISSION_TOKEN);
        var orderItemList = order.getOrderItemList();
        return orderInfoMapper.of(order, orderItemList);
    }

    @Override
    public List<OrderInfo.Main> retrieveAllOrder(String accessToken, OrderDto.SearchOrderRequest searchRequest, Pageable pageable) {
        if (!jwtTokenProvider.validateToken(accessToken)) throw new InvalidParamException(ErrorCode.MEMBER_FAIL_INVALID_TOKEN);
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
        String memberId = authentication.getName();
        var orderList = orderReader.getOrderList(memberId, searchRequest, pageable);
        return orderList.map(order -> {
            var orderItemList = order.getOrderItemList();
            return orderInfoMapper.of(order, orderItemList);
        }).toList();
    }
}
