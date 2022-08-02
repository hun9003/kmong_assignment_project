package com.parkjinhun.kmong.kmong_assignment_project.domain.member;

import com.parkjinhun.kmong.kmong_assignment_project.domain.member.token.TokenInfo;
import com.parkjinhun.kmong.kmong_assignment_project.interfaces.member.MemberDto;

public interface MemberService {
    MemberInfo registerMember(MemberCommand.RegisterMember command);
    TokenInfo loginMember(MemberCommand.LoginMember command);
    TokenInfo reissueToken(MemberDto.ReissueTokenRequest reissue);
    void logoutMember(MemberDto.LogoutRequest logout);

}
