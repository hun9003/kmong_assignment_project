package com.parkjinhun.shopping.shopping_mall_project.common.exception;

import com.parkjinhun.shopping.shopping_mall_project.common.response.ErrorCode;

public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException() {
        super(ErrorCode.COMMON_INVALID_PARAMETER);
    }

    public EntityNotFoundException(String message) {
        super(message, ErrorCode.COMMON_INVALID_PARAMETER);
    }
}
