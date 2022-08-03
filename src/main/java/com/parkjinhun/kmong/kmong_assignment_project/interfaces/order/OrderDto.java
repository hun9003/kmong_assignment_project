package com.parkjinhun.kmong.kmong_assignment_project.interfaces.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class OrderDto {

    @Getter
    @Setter
    @ToString
    @ApiModel(value = "상품 주문 요청 데이터", description = "상품 주문에 필요한 정보 입니다.")
    public static class RegisterOrderRequest {
        @NotBlank(message = "receiverName 는 필수값입니다")
        @ApiModelProperty(name = "receiverName", example = "홍길동", notes = "주문자 이름 입니다.", required = true)
        private String receiverName;

        @NotBlank(message = "receiverPhone 는 필수값입니다")
        @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식에 맞지 않습니다.")
        @ApiModelProperty(name = "receiverPhone", example = "010-0000-0000", notes = "주문자 전화번호 입니다.", required = true)
        private String receiverPhone;

        @NotBlank(message = "receiverZipcode 는 필수값입니다")
        @ApiModelProperty(name = "receiverZipcode", example = "01234", notes = "배송지 우편번호 입니다.", required = true)
        private String receiverZipcode;

        @NotBlank(message = "receiverAddress1 는 필수값입니다")
        @ApiModelProperty(name = "receiverAddress1", example = "서울시 강남구", notes = "배송지 주소 입니다.", required = true)
        private String receiverAddress1;

        @NotBlank(message = "receiverAddress2 는 필수값입니다")
        @ApiModelProperty(name = "receiverAddress2", example = "강남 아파트 101호", notes = "배송지 상세 주소 입니다.", required = true)
        private String receiverAddress2;

        @NotBlank(message = "etcMessage 는 필수값입니다")
        @ApiModelProperty(name = "etcMessage", example = "문 앞에 놓아주세요.", notes = "배송 메시지 입니다.", required = true)
        private String etcMessage;

        @ApiModelProperty(name = "orderItemList", notes = "주문할 상품의 리스트 입니다.", required = true)
        private List<RegisterOrderItem> orderItemList;

    }

    @Getter
    @Setter
    @ToString
    @ApiModel(value = "주문 상품 요청 데이터", description = "주문한 상품에 필요한 정보 입니다.")
    public static class RegisterOrderItem {
        @NotNull(message = "orderCount 는 필수값입니다")
        @ApiModelProperty(name = "orderCount", example = "1", notes = "주문할 상품의 개수 입니다.", required = true)
        private Integer orderCount;

        @NotBlank(message = "itemToken 는 필수값입니다")
        @ApiModelProperty(name = "itemToken", example = "itm_631eCfNg6g79g40V", notes = "상품의 토큰 입니다.", required = true)
        private String itemToken;

        @NotBlank(message = "itemName 는 필수값입니다")
        @ApiModelProperty(name = "itemToken", example = "티셔츠", notes = "상품의 이름 입니다.", required = true)
        private String itemName;

        @NotNull(message = "itemPrice 는 필수값입니다")
        @ApiModelProperty(name = "itemPrice", example = "10000", notes = "상품의 가격 입니다.", required = true)
        private Long itemPrice;

        @ApiModelProperty(name = "orderItemOptionGroupList", notes = "주문 상품의 옵션 그룹 리스트 입니다.", required = true)
        private List<RegisterOrderItemOptionGroupRequest> orderItemOptionGroupList;
    }

    @Getter
    @Setter
    @ToString
    @ApiModel(value = "주문 상품 옵션 그룹 등록 요청 데이터", description = "주문 상품 옵션 그룹 등록에 필요한 정보 입니다.")
    public static class RegisterOrderItemOptionGroupRequest {
        @NotNull(message = "ordering 는 필수값입니다")
        @ApiModelProperty(name = "ordering", example = "1", notes = "주문 상품의 옵션 그룹의 정렬 순서 입니다.", required = true)
        private Integer ordering;

        @NotBlank(message = "itemOptionGroupName 는 필수값입니다")
        @ApiModelProperty(name = "itemOptionGroupName", example = "색상", notes = "주문 상품의 옵션 그룹 이름 입니다.", required = true)
        private String itemOptionGroupName;

        @ApiModelProperty(name = "orderItemOptionList", notes = "주문 상품의 옵션 리스트 입니다.", required = true)
        private List<RegisterOrderItemOptionRequest> orderItemOptionList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderItemOptionRequest {
        @NotNull(message = "ordering 는 필수값입니다")
        @ApiModelProperty(name = "ordering", example = "1", notes = "주문 상품의 옵션의 정렬 순서 입니다.", required = true)
        private Integer ordering;

        @NotBlank(message = "itemOptionName 는 필수값입니다")
        @ApiModelProperty(name = "itemOptionName", example = "RED", notes = "주문 상품의 옵션 이름 입니다.", required = true)
        private String itemOptionName;

        @NotNull(message = "itemOptionPrice 는 필수값입니다")
        @ApiModelProperty(name = "itemOptionPrice", example = "0", notes = "주문 상품의 옵션 가격 입니다.", required = true)
        private Long itemOptionPrice;
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterResponse {
        @ApiModelProperty(name = "orderToken", example = "ord_631eCfNg6g79g40V", notes = "주문 상품의 토큰 입니다.")
        private final String orderToken;
    }

    // 조회
    @Getter
    @Builder
    @ToString
    public static class Main {
        @ApiModelProperty(name = "orderToken", example = "ord_631eCfNg6g79g40V", notes = "주문 상품의 토큰 입니다.")
        private final String orderToken;
        @ApiModelProperty(name = "memberId", example = "test1324", notes = "주문자 아이디 입니다.")
        private final String memberId;
        @ApiModelProperty(name = "totalAmount", example = "120000", notes = "주문한 상품들의 총 가격 입니다.")
        private final Long totalAmount;
        @ApiModelProperty(name = "deliveryInfo", notes = "배송지 정보 입니다.")
        private final DeliveryInfo deliveryInfo;
        @ApiModelProperty(name = "orderedAt", notes = "주문 일시 입니다.")
        private final String orderedAt;
        @ApiModelProperty(name = "status", example = "INIT", notes = "주문 상태 입니다.")
        private final String status;
        @ApiModelProperty(name = "statusDescription", example = "주문시작", notes = "주문 상태 설명 입니다.")
        private final String statusDescription;
        @ApiModelProperty(name = "orderItemList", notes = "주문 상품 리스트 입니다.")
        private final List<OrderItem> orderItemList;
    }

    @Getter
    @Builder
    @ToString
    public static class DeliveryInfo {
        @ApiModelProperty(name = "receiverName", example = "홍길동", notes = "주문자 이름 입니다.")
        private final String receiverName;
        @ApiModelProperty(name = "receiverPhone", example = "010-0000-0000", notes = "주문자 전화번호 입니다.")
        private final String receiverPhone;
        @ApiModelProperty(name = "receiverZipcode", example = "01234", notes = "배송지 우편번호 입니다.")
        private final String receiverZipcode;
        @ApiModelProperty(name = "receiverAddress1", example = "서울시 강남구", notes = "배송지 주소 입니다.")
        private final String receiverAddress1;
        @ApiModelProperty(name = "receiverAddress2", example = "강남 아파트 101호", notes = "배송지 상세 주소 입니다.")
        private final String receiverAddress2;
        @ApiModelProperty(name = "etcMessage", example = "문 앞에 놓아주세요.", notes = "배송 메시지 입니다.")
        private final String etcMessage;
    }

    @Getter
    @Builder
    @ToString
    public static class OrderItem {

        @ApiModelProperty(name = "orderCount", example = "1", notes = "주문할 상품의 개수 입니다.")
        private final Integer orderCount;
        @ApiModelProperty(name = "memberId", example = "test1324", notes = "주문자 아이디 입니다.")
        private final String memberId;
        @ApiModelProperty(name = "itemId", example = "1", notes = "상품의 IDX 입니다.")
        private final Long itemId;
        @ApiModelProperty(name = "itemName", example = "티셔츠", notes = "상품의 이름 입니다.")
        private final String itemName;
        @ApiModelProperty(name = "totalAmount", example = "120000", notes = "주문한 상품들의 총 가격 입니다.")
        private final Long totalAmount;
        @ApiModelProperty(name = "itemPrice", example = "10000", notes = "상품의 가격 입니다.")
        private final Long itemPrice;
        @ApiModelProperty(name = "deliveryStatus", example = "BEFORE_DELIVERY", notes = "배송 상태 입니다.")
        private final String deliveryStatus;
        @ApiModelProperty(name = "deliveryStatusDescription", example = "배송전", notes = "배송 상태 설명 입니다.")
        private final String deliveryStatusDescription;
        @ApiModelProperty(name = "orderItemOptionGroupList", notes = "주문 상품의 옵션 그룹 리스트 입니다.")
        private final List<OrderItemOptionGroup> orderItemOptionGroupList;
    }

    @Getter
    @Builder
    @ToString
    public static class OrderItemOptionGroup {
        @ApiModelProperty(name = "ordering", example = "1", notes = "주문 상품의 옵션 그룹의 정렬 순서 입니다.")
        private final Integer ordering;

        @ApiModelProperty(name = "itemOptionGroupName", example = "색상", notes = "주문 상품의 옵션 그룹 이름 입니다.")
        private final String itemOptionGroupName;

        @ApiModelProperty(name = "orderItemOptionList", notes = "주문 상품의 옵션 리스트 입니다.")
        private final List<OrderItemOption> orderItemOptionList;
    }

    @Getter
    @Builder
    @ToString
    public static class OrderItemOption {
        @ApiModelProperty(name = "ordering", example = "1", notes = "주문 상품의 옵션의 정렬 순서 입니다.", required = true)
        private final Integer ordering;

        @ApiModelProperty(name = "itemOptionName", example = "RED", notes = "주문 상품의 옵션 이름 입니다.", required = true)
        private final String itemOptionName;

        @ApiModelProperty(name = "itemOptionPrice", example = "0", notes = "주문 상품의 옵션 가격 입니다.", required = true)
        private final Long itemOptionPrice;

    }
}
