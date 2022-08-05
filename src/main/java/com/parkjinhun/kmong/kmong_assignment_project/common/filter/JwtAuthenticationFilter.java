package com.parkjinhun.kmong.kmong_assignment_project.common.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkjinhun.kmong.kmong_assignment_project.common.exception.BaseException;
import com.parkjinhun.kmong.kmong_assignment_project.common.response.CommonResponse;
import com.parkjinhun.kmong.kmong_assignment_project.common.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TYPE = "Bearer";

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate redisTemplate;

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        // 1. Request Header 에서 JWT 토큰 추출
//        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
//        // 2. validateToken 으로 토큰 유효성 검사
//        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
//            Authentication authentication = jwtTokenProvider.getAuthentication(token);
//            System.out.println(authentication);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
////        try {
////
////        } catch (SecurityException | MalformedJwtException e) {
////            request.setAttribute("exception", ErrorCode.MEMBER_FAIL_INVALID_TOKEN);
////        } catch (ExpiredJwtException e) {
////            request.setAttribute("exception", ErrorCode.MEMBER_FAIL_EXPIRED_TOKEN);
////        } catch (UnsupportedJwtException e) {
////            request.setAttribute("exception", ErrorCode.MEMBER_FAIL_UNSUPPORTED_TOKEN);
////        } catch (IllegalArgumentException e) {
////            request.setAttribute("exception", ErrorCode.COMMON_INVALID_PARAMETER);
////        } catch (Exception e) {
////            log.error("================================================");
////            log.error("JwtFilter - doFilterInternal() 오류발생");
////            log.error("token : {}", token);
////            log.error("Exception Message : {}", e.getMessage());
////            log.error("Exception StackTrace : {");
////            e.printStackTrace();
////            log.error("}");
////            log.error("================================================");
////            request.setAttribute("exception", ErrorCode.COMMON_SYSTEM_ERROR);
////        }
//
//        chain.doFilter(request, response);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 헤더에서 JWT 를 받아옵니다.
        String token = resolveToken((HttpServletRequest) request);
        // 유효한 토큰인지 확인합니다.
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                // SecurityContext 에 Authentication 객체를 저장합니다.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (BaseException e) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            var result = CommonResponse.builder()
                    .result(CommonResponse.Result.FAIL)
                    .message(e.getErrorCode().getErrorMsg())
                    .errorCode(e.getErrorCode().name())
                    .build();

            response.getWriter().print(new ObjectMapper().writeValueAsString(result));
        }
    }

    // Request Header 에서 토큰 정보 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TYPE)) {
            return bearerToken.substring(7);
        }
        return null;
    }

}