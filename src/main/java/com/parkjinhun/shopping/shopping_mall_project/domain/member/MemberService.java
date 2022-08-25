package com.parkjinhun.shopping.shopping_mall_project.domain.member;

import com.parkjinhun.shopping.shopping_mall_project.domain.member.token.TokenInfo;
import com.parkjinhun.shopping.shopping_mall_project.interfaces.member.MemberDto;

public interface MemberService {
    MemberInfo registerMember(MemberCommand.RegisterMember command);
    TokenInfo loginMember(MemberCommand.LoginMember command);
    TokenInfo reissueToken(MemberDto.ReissueTokenRequest reissue);
    void logoutMember(MemberDto.LogoutRequest logout);

    void deleteAllMember();
}
