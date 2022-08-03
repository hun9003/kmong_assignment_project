package com.parkjinhun.kmong.kmong_assignment_project.interfaces.order;

import com.parkjinhun.kmong.kmong_assignment_project.application.order.OrderFacade;
import com.parkjinhun.kmong.kmong_assignment_project.common.response.CommonResponse;
import com.parkjinhun.kmong.kmong_assignment_project.common.util.TokenGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = {"주문 API"})
@RequestMapping("/api/v1/orders")
public class OrderApiController {
    private final OrderFacade orderFacade;
    private final OrderDtoMapper orderDtoMapper;

    @PostMapping
    @ApiOperation(value = "상품 주문", notes = "전달 받은 정보로 상품을 주문 합니다 회원 로그인이 필요한 API 입니다.")
    @ApiResponse(code = 200, message = "성공 시 주문 토큰을 반환 합니다.")
    public CommonResponse registerOrder(@RequestBody @Valid OrderDto.RegisterOrderRequest request,
                                        @ApiParam(value = "회원 엑세스 토큰", example = "Bearer {ACCESS_TOKEN}")
                                        @RequestHeader(value="Authorization", defaultValue = "") String authorization) {
        String token = TokenGenerator.getToken(authorization);
        var orderCommand = orderDtoMapper.of(request);
        var orderToken = orderFacade.registerOrder(orderCommand, token);
        var response = orderDtoMapper.of(orderToken);
        return CommonResponse.success(response);
    }

    @GetMapping
    @ApiOperation(value = "회원 주문 내역 조회", notes = "전달 받은 정보로 상품을 주문 합니다 회원 로그인이 필요한 API 입니다.")
    @ApiResponse(code = 200, message = "성공 시 주문 토큰을 반환 합니다.")
    public CommonResponse retrieveOrder(@RequestHeader(value="Authorization", defaultValue = "") String authorization) {
        String token = TokenGenerator.getToken(authorization);
        var orderResult = orderFacade.retrieveOrder(token);
        var response = orderDtoMapper.of(orderResult);
        return CommonResponse.success(response);
    }

}
