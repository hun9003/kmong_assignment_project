package com.parkjinhun.kmong.kmong_assignment_project.domain.member.token;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenInfo {
    private final String grantType;
    private final String accessToken;
    private final String refreshToken;
    private final Long refreshTokenExpirationTime;

    public TokenInfo(String grantType, String accessToken, String refreshToken, Long refreshTokenExpirationTime) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }
}
