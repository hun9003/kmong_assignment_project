package com.parkjinhun.kmong.kmong_assignment_project.interfaces.item;

import com.parkjinhun.kmong.kmong_assignment_project.domain.item.Item;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ItemDto {

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "상품 등록 요청 데이터", description = "상품 등록에 필요한 정보 입니다.")
    public static class RegisterItemRequest {
        @NotEmpty(message = "accessToken 은 필수 값 입니다.")
        @ApiModelProperty(name = "accessToken",
                example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTIzNDUiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNjU5NDQ2NjU0fQ.WLmpRAaJ60LtLOrBmKkWNYOy1BOZw-hG8CuZEVlMKXw",
                notes = "회원의 엑세스 토큰 입니다.", required = true)
        private String accessToken;

        @NotEmpty(message = "itemName 은 필수 값 입니다.")
        @ApiModelProperty(name = "itemName", example = "티셔츠", notes = "상품의 이름 입니다.", required = true)
        private String itemName;

        @NotNull(message = "itemPrice 은 필수 값 입니다.")
        @ApiModelProperty(name = "itemPrice", example = "10000", notes = "상품의 가격 입니다.", required = true)
        private Long itemPrice;

        @ApiModelProperty(name = "itemOptionGroupList", notes = "상품의 옵션 그룹 리스트 입니다.", required = true)
        private List<RegisterItemOptionGroupRequest> itemOptionGroupList;
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "상품 옵션 그룹 등록 요청 데이터", description = "상품 옵션 그룹 등록에 필요한 정보 입니다.")
    public static class RegisterItemOptionGroupRequest {
        @ApiModelProperty(name = "ordering", notes = "상품의 옵션 그룹의 정렬 순서 입니다.", required = true)
        private Integer ordering;

        @ApiModelProperty(name = "itemOptionGroupName", notes = "상품의 옵션 그룹 이름 입니다.", required = true)
        private String itemOptionGroupName;

        @ApiModelProperty(name = "itemOptionList", notes = "상품의 옵션 리스트 입니다.", required = true)
        private List<RegisterItemOptionRequest> itemOptionList;
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "상품 옵션 등록 요청 데이터", description = "상품 옵션 등록에 필요한 정보 입니다.")
    public static class RegisterItemOptionRequest {
        @ApiModelProperty(name = "ordering", notes = "상품의 옵션의 정렬 순서 입니다.", required = true)
        private Integer ordering;

        @ApiModelProperty(name = "itemOptionName", notes = "상품의 옵션 이름 입니다.", required = true)
        private String itemOptionName;

        @ApiModelProperty(name = "itemOptionPrice", notes = "상품의 옵션 가격 입니다.", required = true)
        private Long itemOptionPrice;
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "상품 등록 응답", description = "등록한 상품의 토큰 입니다.")
    public static class RegisterItemResponse {
        @ApiModelProperty(name = "itemToken", example = "itm_631eCfNg6g79g40V", notes = "상품의 토큰 입니다.", required = true)
        private final String itemToken;
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "상품 상태 변경 요청", description = "상품 상태 변경에 필요한 데이터 입니다.")
    public static class ChangeStatusItemRequest {
        @NotEmpty(message = "accessToken 은 필수 값 입니다.")
        @ApiModelProperty(name = "accessToken",
                example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTIzNDUiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNjU5NDQ2NjU0fQ.WLmpRAaJ60LtLOrBmKkWNYOy1BOZw-hG8CuZEVlMKXw",
                notes = "회원의 엑세스 토큰 입니다.", required = true)
        private String accessToken;

        @NotEmpty(message = "itemToken 은 필수 값 입니다.")
        @ApiModelProperty(name = "itemToken", example = "itm_631eCfNg6g79g40V", notes = "상품의 토큰 입니다.", required = true)
        private String itemToken;
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "상품 조회 데이터", description = "조회하는 상품의 데이터 입니다.")
    public static class Main {
        @ApiModelProperty(name = "itemToken", example = "itm_631eCfNg6g79g40V", notes = "상품의 토큰 입니다.", required = true)
        private final String itemToken;

        @ApiModelProperty(name = "memberId", example = "test1234", notes = "가입 처리된 아이디.")
        private final String memberId;

        @ApiModelProperty(name = "itemName", example = "티셔츠", notes = "상품의 이름 입니다.", required = true)
        private final String itemName;

        @ApiModelProperty(name = "itemPrice", example = "10000", notes = "상품의 가격 입니다.", required = true)
        private final Long itemPrice;

        @ApiModelProperty(name = "status", example = "PREPARE", notes = "상품의 상태 입니다. (PREPARE : 상품준비중, ON_SALE: 판매중, END_OF_SALE: 판매중지)", required = true)
        private final Item.Status status;

        @ApiModelProperty(name = "itemOptionGroupList", notes = "상품의 옵션 그룹 리스트 입니다.", required = true)
        private final List<ItemOptionGroupInfo> itemOptionGroupList;
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "상품 옵션 그룹 조회 데이터", description = "조회하는 상품의 옵션 그룹 데이터 입니다.")
    public static class ItemOptionGroupInfo {
        @ApiModelProperty(name = "ordering", notes = "상품의 옵션 그룹의 정렬 순서 입니다.", required = true)
        private final Integer ordering;

        @ApiModelProperty(name = "itemOptionGroupName", notes = "상품의 옵션 그룹 이름 입니다.", required = true)
        private final String itemOptionGroupName;

        @ApiModelProperty(name = "itemOptionList", notes = "상품의 옵션 리스트 입니다.", required = true)
        private final List<ItemOptionInfo> itemOptionList;
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "상품 옵션 조회 데이터", description = "조회하는 상품의 옵션 데이터 입니다.")
    public static class ItemOptionInfo {
        @ApiModelProperty(name = "ordering", notes = "상품의 옵션의 정렬 순서 입니다.", required = true)
        private final Integer ordering;

        @ApiModelProperty(name = "itemOptionName", notes = "상품의 옵션 이름 입니다.", required = true)
        private final String itemOptionName;

        @ApiModelProperty(name = "itemOptionPrice", notes = "상품의 옵션 가격 입니다.", required = true)
        private final Long itemOptionPrice;
    }
}
