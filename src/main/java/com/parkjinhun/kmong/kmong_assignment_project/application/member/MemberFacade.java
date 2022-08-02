package com.parkjinhun.kmong.kmong_assignment_project.application.member;

import com.parkjinhun.kmong.kmong_assignment_project.domain.member.MemberCommand;
import com.parkjinhun.kmong.kmong_assignment_project.domain.member.MemberInfo;
import com.parkjinhun.kmong.kmong_assignment_project.domain.member.MemberService;
import com.parkjinhun.kmong.kmong_assignment_project.domain.member.token.TokenInfo;
import com.parkjinhun.kmong.kmong_assignment_project.interfaces.member.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberService memberService;

    public MemberInfo registerMember(MemberCommand.RegisterMember command) {
        return memberService.registerMember(command);
    }

    public TokenInfo loginMember(MemberCommand.LoginMember command) {
        return memberService.loginMember(command);
    }

    public TokenInfo reissueToken(MemberDto.ReissueTokenRequest reissueToken) {
        return memberService.reissueToken(reissueToken);
    }

    public void logoutMember(MemberDto.LogoutRequest logout) {
        memberService.logoutMember(logout);
    }
}
