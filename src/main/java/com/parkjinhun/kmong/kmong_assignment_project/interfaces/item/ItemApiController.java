package com.parkjinhun.kmong.kmong_assignment_project.interfaces.item;

import com.parkjinhun.kmong.kmong_assignment_project.application.item.ItemFacade;
import com.parkjinhun.kmong.kmong_assignment_project.common.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = {"상품 API"})
@RequestMapping("/api/v1/items")
public class ItemApiController {
    private final ItemFacade itemFacade;
    private final ItemDtoMapper itemDtoMapper;

    @PostMapping
    @ApiOperation(value = "상품 등록", notes = "전달 받은 정보로 상품을 등록 합니다 회원 로그인이 필요한 API 입니다.")
    @ApiResponse(code = 200, message = "성공 시 상품 토큰을 반환 합니다.")
    public CommonResponse<?> registerItem(@RequestBody @Valid ItemDto.RegisterItemRequest request) {
        var accessToken = request.getAccessToken();
        var itemCommand = itemDtoMapper.of(request);
        var itemToken = itemFacade.registerItem(itemCommand, accessToken);
        var response = itemDtoMapper.of(itemToken);
        return CommonResponse.success(response);
    }

    @PutMapping("/change-on-sales")
    @ApiOperation(value = "상품 상태 판매중으로 변경", notes = "상품의 상태를 판매중으로 변경 합니다. 상품을 등록한 회원의 로그인이 필요한 API 입니다.")
    @ApiResponse(code = 200, message = "성공 시 완료 메시지를 반환 합니다.")
    public CommonResponse<?> changeOnSaleItem(@RequestBody @Valid ItemDto.ChangeStatusItemRequest request) {
        itemFacade.changeOnSaleItem(request);
        return CommonResponse.success("OK");
    }

    @PutMapping("/change-end-of-sales")
    @ApiOperation(value = "상품 상태 판매중지로 변경", notes = "상품의 상태를 판매중지로 변경 합니다. 상품을 등록한 회원의 로그인이 필요한 API 입니다.")
    @ApiResponse(code = 200, message = "성공 시 완료 메시지를 반환 합니다.")
    public CommonResponse<?> changeEndOfSaleItem(@RequestBody @Valid ItemDto.ChangeStatusItemRequest request) {
        itemFacade.changeEndOfSaleItem(request);
        return CommonResponse.success("OK");
    }

    @GetMapping("/{itemToken}")
    @ApiOperation(value = "상품 조회", notes = "상품의 정보를 조회 합니다.")
    @ApiResponse(code = 200, message = "성공 시 상품 정보를 반환 합니다.")
    public CommonResponse<?> retrieve(@PathVariable("itemToken") String itemToken) {
        var itemInfo = itemFacade.retrieveItemInfo(itemToken);
        var response = itemDtoMapper.of(itemInfo);
        return CommonResponse.success(response);
    }
}
