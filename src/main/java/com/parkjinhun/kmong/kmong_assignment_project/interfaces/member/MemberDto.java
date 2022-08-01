package com.parkjinhun.kmong.kmong_assignment_project.interfaces.member;

import com.parkjinhun.kmong.kmong_assignment_project.domain.member.MemberCommand;
import com.parkjinhun.kmong.kmong_assignment_project.domain.member.MemberInfo;
import com.parkjinhun.kmong.kmong_assignment_project.domain.member.token.TokenInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class MemberDto {

    @Getter
    @Builder
    @ToString
    public static class RegisterMemberRequest {
        @NotEmpty(message = "아이디는 필수 값 입니다.")
        @Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9_]{5,11}$", message = "아이디는 6~12자 영문, 숫자를 사용 하세요.")
        private final String memberId;

        @NotEmpty(message = "이메일은 필수 값 입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
        private final String memberEmail;

        @NotEmpty(message = "비밀번호는 필수 값 입니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private final String memberPassword;

        public MemberCommand.RegisterMember toCommand() {
            return MemberCommand.RegisterMember.builder()
                    .memberId(memberId)
                    .memberEmail(memberEmail)
                    .memberPassword(memberPassword)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class RegisterMemberResponse {
        private final String memberId;
        private final String memberEmail;

        public RegisterMemberResponse(MemberInfo memberInfo) {
            this.memberId = memberInfo.getMemberId();
            this.memberEmail = memberInfo.getMemberEmail();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class LoginMemberRequest {
        @NotEmpty(message = "아이디는 필수 값 입니다.")
        @Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9_]{5,11}$", message = "아이디는 6~12자 영문, 숫자를 사용 하세요.")
        private final String memberId;

        @NotEmpty(message = "비밀번호는 필수 값 입니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private final String memberPassword;

        public MemberCommand.LoginMember toCommand() {
            return MemberCommand.LoginMember.builder()
                    .memberId(memberId)
                    .memberPassword(memberPassword)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class ReissueTokenRequest {
        @NotEmpty(message = "accessToken 은 필수 값 입니다.")
        private String accessToken;

        @NotEmpty(message = "refreshToken 은 필수 값 입니다.")
        private String refreshToken;
    }

    @Getter
    @Builder
    @ToString
    public static class LogoutRequest {
        @NotEmpty(message = "잘못된 요청 입니다.")
        private String accessToken;

        @NotEmpty(message = "잘못된 요청 입니다.")
        private String refreshToken;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class TokenInfoRequest {
        private String grantType;
        private String accessToken;
        private String refreshToken;
        private Long refreshTokenExpirationTime;
    }

    @Getter
    @ToString
    public static class TokenInfoResponse {
        private final String grantType;
        private final String accessToken;
        private final String refreshToken;
        private final Long refreshTokenExpirationTime;

        public TokenInfoResponse(TokenInfo tokenInfo) {
            this.grantType = tokenInfo.getGrantType();
            this.accessToken = tokenInfo.getAccessToken();
            this.refreshToken = tokenInfo.getRefreshToken();
            this.refreshTokenExpirationTime = tokenInfo.getRefreshTokenExpirationTime();
        }
    }

}
