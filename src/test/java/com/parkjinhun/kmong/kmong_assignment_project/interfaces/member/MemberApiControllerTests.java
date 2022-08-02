package com.parkjinhun.kmong.kmong_assignment_project.interfaces.member;

import com.parkjinhun.kmong.kmong_assignment_project.KmongAssignmentProjectApplication;
import com.parkjinhun.kmong.kmong_assignment_project.domain.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = KmongAssignmentProjectApplication.class)
@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@AutoConfigureRestDocs
public class MemberApiControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private MemberService memberService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("member/{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
    }

    private MemberDto.RegisterMemberRequest newMemberDto() {
        return MemberDto.RegisterMemberRequest.builder()
                .memberId("test12345")
                .memberEmail("test12345@testEmail.com")
                .memberPassword("abcdf1234!@#$")
                .build();
    }

    private MemberDto.LoginMemberRequest loginMemberDto() {
        var memberDto = newMemberDto();
        return MemberDto.LoginMemberRequest.builder()
                .memberId(memberDto.getMemberId())
                .memberPassword(memberDto.getMemberPassword())
                .build();
    }

    @Test
    @DisplayName("회원가입 - 정상적으로 데이터를 입력")
//    @Transactional
    public void registerMember() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        var memberDto = newMemberDto();

        this.mockMvc.perform(post("/api/v1/members/sign-up")
                        .header("content-type", "application/json")
                        .content(mapper.writeValueAsString(memberDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("admin/{method-name}",
                        requestFields(
                                fieldWithPath("memberId").type(JsonFieldType.STRING).description("회원 아이디"),
                                fieldWithPath("memberEmail").type(JsonFieldType.STRING).description("회원 이메일"),
                                fieldWithPath("memberPassword").type(JsonFieldType.STRING).description("회원 비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메세지").optional(),
                                fieldWithPath("errorCode").type(JsonFieldType.STRING).description("결과 코드").optional(),
                                fieldWithPath("data.memberId").type(JsonFieldType.STRING).description("회원 아이디"),
                                fieldWithPath("data.memberEmail").type(JsonFieldType.STRING).description("회원 이메일")
                        )
                ))
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입 - 잘못된 데이터를 입력")
//    @Transactional
    public void registerMemberInvalid() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        var memberDto = MemberDto.RegisterMemberRequest.builder()
                .memberId("")
                .memberEmail("")
                .memberPassword("")
                .build();

        this.mockMvc.perform(post("/api/v1/members/sign-up")
                        .header("content-type", "application/json")
                        .content(mapper.writeValueAsString(memberDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("admin/{method-name}",
                        requestFields(
                                fieldWithPath("memberId").type(JsonFieldType.STRING).description("회원 아이디"),
                                fieldWithPath("memberEmail").type(JsonFieldType.STRING).description("회원 이메일"),
                                fieldWithPath("memberPassword").type(JsonFieldType.STRING).description("회원 비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메세지").optional(),
                                fieldWithPath("errorCode").type(JsonFieldType.STRING).description("결과 코드").optional(),
                                fieldWithPath("data").type(JsonFieldType.STRING).description("데이터").optional()
                        )
                ))
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 - 정상적으로 데이터를 입력")
    public void loginMember() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        var request = loginMemberDto();

        this.mockMvc.perform(post("/api/v1/members/login")
                        .header("content-type", "application/json")
                        .content(mapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("admin/{method-name}",
                        requestFields(
                                fieldWithPath("memberId").type(JsonFieldType.STRING).description("회원 아이디"),
                                fieldWithPath("memberPassword").type(JsonFieldType.STRING).description("회원 비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메세지").optional(),
                                fieldWithPath("errorCode").type(JsonFieldType.STRING).description("결과 코드").optional(),
                                fieldWithPath("data.accessToken").type(JsonFieldType.STRING).description("엑세스 토큰"),
                                fieldWithPath("data.refreshToken").type(JsonFieldType.STRING).description("리프레쉬 토큰"),
                                fieldWithPath("data.grantType").type(JsonFieldType.STRING).description("유형"),
                                fieldWithPath("data.refreshTokenExpirationTime").type(JsonFieldType.NUMBER).description("유지시간")
                        )
                ))
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 - 잘못된 데이터를 입력")
    public void loginMemberInvalid() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        var request = MemberDto.LoginMemberRequest.builder()
                .memberId("")
                .memberPassword("")
                .build();

        this.mockMvc.perform(post("/api/v1/members/login")
                        .header("content-type", "application/json")
                        .content(mapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("admin/{method-name}",
                        requestFields(
                                fieldWithPath("memberId").type(JsonFieldType.STRING).description("회원 아이디"),
                                fieldWithPath("memberPassword").type(JsonFieldType.STRING).description("회원 비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메세지").optional(),
                                fieldWithPath("errorCode").type(JsonFieldType.STRING).description("결과 코드").optional(),
                                fieldWithPath("data").type(JsonFieldType.STRING).description("데이터").optional()
                        )
                ))
                .andDo(print());
    }

    @Test
    @DisplayName("로그아웃 - 정상적으로 데이터를 입력")
    public void logoutMember() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        var loginDto = loginMemberDto();
        var tokenInfo = memberService.loginMember(loginDto.toCommand());

        var request = MemberDto.LogoutRequest.builder()
                .accessToken(tokenInfo.getAccessToken())
                .refreshToken(tokenInfo.getRefreshToken())
                .build();

        this.mockMvc.perform(post("/api/v1/members/logout")
                        .header("content-type", "application/json")
                        .content(mapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("admin/{method-name}",
                        requestFields(
                                fieldWithPath("accessToken").type(JsonFieldType.STRING).description("엑세스 토큰"),
                                fieldWithPath("refreshToken").type(JsonFieldType.STRING).description("리프레쉬 토큰")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메세지").optional(),
                                fieldWithPath("errorCode").type(JsonFieldType.STRING).description("결과 코드").optional(),
                                fieldWithPath("data").type(JsonFieldType.STRING).description("데이터").optional()
                        )
                ))
                .andDo(print());
    }

    @Test
    @DisplayName("토큰 정보 갱신 - 정상적으로 데이터를 입력")
    public void reissue() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        var loginDto = loginMemberDto();
        var tokenInfo = memberService.loginMember(loginDto.toCommand());

        var request = MemberDto.ReissueTokenRequest.builder()
                .accessToken(tokenInfo.getAccessToken())
                .refreshToken(tokenInfo.getRefreshToken())
                .build();


        this.mockMvc.perform(post("/api/v1/members/reissue")
                        .header("content-type", "application/json")
                        .content(mapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("admin/{method-name}",
                        requestFields(
                                fieldWithPath("accessToken").type(JsonFieldType.STRING).description("엑세스 토큰"),
                                fieldWithPath("refreshToken").type(JsonFieldType.STRING).description("리프레쉬 토큰")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메세지").optional(),
                                fieldWithPath("errorCode").type(JsonFieldType.STRING).description("결과 코드").optional(),
                                fieldWithPath("data.accessToken").type(JsonFieldType.STRING).description("엑세스 토큰"),
                                fieldWithPath("data.refreshToken").type(JsonFieldType.STRING).description("리프레쉬 토큰"),
                                fieldWithPath("data.grantType").type(JsonFieldType.STRING).description("유형"),
                                fieldWithPath("data.refreshTokenExpirationTime").type(JsonFieldType.NUMBER).description("유지시간")
                        )
                ))
                .andDo(print());
    }

}
