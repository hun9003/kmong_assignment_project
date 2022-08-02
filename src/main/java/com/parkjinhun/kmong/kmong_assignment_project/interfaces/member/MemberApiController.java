package com.parkjinhun.kmong.kmong_assignment_project.interfaces.member;

import com.parkjinhun.kmong.kmong_assignment_project.application.member.MemberFacade;
import com.parkjinhun.kmong.kmong_assignment_project.common.exception.InvalidParamException;
import com.parkjinhun.kmong.kmong_assignment_project.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberApiController {
    private final MemberFacade memberFacade;

    @PostMapping("/sign-up")
    public CommonResponse<?> registerMember(@RequestBody @Valid MemberDto.RegisterMemberRequest request, Errors errors) {
        if (errors.hasErrors()) throw new InvalidParamException();
        var command = request.toCommand();
        var memberInfo = memberFacade.registerMember(command);
        var response = new MemberDto.RegisterMemberResponse(memberInfo);
        return CommonResponse.success(response, "회원 가입을 완료 했습니다.");
    }

    @PostMapping("/login")
    public CommonResponse<?> loginMember(@RequestBody @Valid MemberDto.LoginMemberRequest request, Errors errors) {
        if (errors.hasErrors()) throw new InvalidParamException();
        var command = request.toCommand();
        var tokenInfo = memberFacade.loginMember(command);
        var response = new MemberDto.TokenInfoResponse(tokenInfo);
        return CommonResponse.success(response, "로그인을 완료 했습니다.");
    }

    @PostMapping("/reissue")
    public CommonResponse<?> reissue(@RequestBody @Valid MemberDto.ReissueTokenRequest reissue, Errors errors) {
        if (errors.hasErrors()) throw new InvalidParamException();
        var tokenInfo = memberFacade.reissueToken(reissue);
        var response = new MemberDto.TokenInfoResponse(tokenInfo);
        return CommonResponse.success(response, "토큰 정보가 갱신되었습니다.");
    }

    @PostMapping("/logout")
    public CommonResponse<?> logoutMember(@RequestBody @Valid MemberDto.LogoutRequest logout, Errors errors) {
        if (errors.hasErrors()) throw new InvalidParamException();
        memberFacade.logoutMember(logout);
        return CommonResponse.success("로그아웃이 완료되었습니다.");
    }

}
