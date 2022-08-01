package com.parkjinhun.kmong.kmong_assignment_project.interfaces.member;

import com.parkjinhun.kmong.kmong_assignment_project.KmongAssignmentProjectApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("member/{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
    }

    @Test
    @DisplayName("회원가입 - 정상적으로 데이터를 입력")
//    @Transactional
    public void registerMember() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        var request = MemberDto.RegisterMemberRequest.builder()
                .memberId("test1234")
                .memberEmail("test1234@testEmail.com")
                .memberPassword("abcdf1234!@#$")
                .build();

        this.mockMvc.perform(post("/api/v1/members/sign-up")
                        .header("content-type", "application/json")
                        .content(mapper.writeValueAsString(request))
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
    @DisplayName("로그인 - 정상적으로 데이터를 입력")
    public void loginMember() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        var request = MemberDto.LoginMemberRequest.builder()
                .memberId("test1234")
                .memberPassword("abcdf1234!@#$")
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
                                fieldWithPath("data.accessToken").type(JsonFieldType.STRING).description("엑세스 토큰"),
                                fieldWithPath("data.refreshToken").type(JsonFieldType.STRING).description("리프레쉬 토큰")
                        )
                ))
                .andDo(print());
    }

}
