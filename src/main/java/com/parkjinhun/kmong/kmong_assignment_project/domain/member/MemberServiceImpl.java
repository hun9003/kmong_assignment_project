package com.parkjinhun.kmong.kmong_assignment_project.domain.member;

import com.parkjinhun.kmong.kmong_assignment_project.common.exception.InvalidParamException;
import com.parkjinhun.kmong.kmong_assignment_project.common.response.ErrorCode;
import com.parkjinhun.kmong.kmong_assignment_project.common.util.jwt.JwtTokenProvider;
import com.parkjinhun.kmong.kmong_assignment_project.config.security.SecurityUtil;
import com.parkjinhun.kmong.kmong_assignment_project.domain.member.role.Authority;
import com.parkjinhun.kmong.kmong_assignment_project.domain.member.token.TokenInfo;
import com.parkjinhun.kmong.kmong_assignment_project.interfaces.member.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberReader memberReader;
    private final MemberStore memberStore;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisTemplate redisTemplate;

    @Transactional
    @Override
    public MemberInfo registerMember(MemberCommand.RegisterMember command) {
        if (memberReader.checkMemberById(command.getMemberId())) throw new InvalidParamException(ErrorCode.MEMBER_REDUPLICATION_ID);
        if (memberReader.checkMemberByEmail(command.getMemberEmail())) throw new InvalidParamException(ErrorCode.MEMBER_REDUPLICATION_ID);

        var initMember = command.toEntity();
        Member member = memberStore.saveMember(initMember);
        return new MemberInfo(member);
    }

    @Transactional(readOnly = true)
    @Override
    public TokenInfo loginMember(MemberCommand.LoginMember command) {

        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = command.toAuthentication();

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        // 4. RefreshToken Redis 저장 (expirationTime 설정을 통해 자동 삭제 처리)
        redisTemplate.opsForValue().set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return tokenInfo;
    }

    @Override
    public TokenInfo reissueToken(MemberDto.ReissueTokenRequest reissue) {
        // 1. Refresh Token 검증
        if (!jwtTokenProvider.validateToken(reissue.getRefreshToken())) throw new InvalidParamException(ErrorCode.MEMBER_BAD_REFRESH_TOKEN);

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(reissue.getAccessToken());

        // 3. Redis 에서 User email 을 기반으로 저장된 Refresh Token 값을 가져옵니다.
        String refreshToken = (String)redisTemplate.opsForValue().get("RT:" + authentication.getName());
        // (추가) 로그아웃되어 Redis 에 RefreshToken 이 존재하지 않는 경우 처리
        if(ObjectUtils.isEmpty(refreshToken)) throw new InvalidParamException(ErrorCode.COMMON_BAD_REQUEST);

        if(!refreshToken.equals(reissue.getRefreshToken())) throw new InvalidParamException(ErrorCode.MEMBER_FAIL_REFRESH_TOKEN);

        // 4. 새로운 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        // 5. RefreshToken Redis 업데이트
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return tokenInfo;
    }

    @Override
    public void logoutMember(MemberDto.LogoutRequest logout) {
        // 1. Access Token 검증
        if (!jwtTokenProvider.validateToken(logout.getAccessToken())) throw new InvalidParamException(ErrorCode.COMMON_BAD_REQUEST);

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(logout.getAccessToken());

        // 3. Redis 에서 해당 User email 로 저장된 Refresh Token 이 있는지 여부를 확인 후 있을 경우 삭제 합니다.
        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            // Refresh Token 삭제
            redisTemplate.delete("RT:" + authentication.getName());
        }

        // 4. 해당 Access Token 유효시간 가지고 와서 BlackList 로 저장하기
        Long expiration = jwtTokenProvider.getExpiration(logout.getAccessToken());
        redisTemplate.opsForValue()
                .set(logout.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);
    }

    @Override
    public void authority() {
        String userId = SecurityUtil.getCurrentUserId();

        var member = memberReader.getMemberById(userId);

        // add ROLE_ADMIN
        member.getRoles().add(Authority.ROLE_ADMIN.name());
        memberStore.saveMember(member);
    }
}
