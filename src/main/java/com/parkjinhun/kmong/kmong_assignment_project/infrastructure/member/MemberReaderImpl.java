package com.parkjinhun.kmong.kmong_assignment_project.infrastructure.member;

import com.parkjinhun.kmong.kmong_assignment_project.common.exception.InvalidParamException;
import com.parkjinhun.kmong.kmong_assignment_project.domain.member.Member;
import com.parkjinhun.kmong.kmong_assignment_project.domain.member.MemberReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberReaderImpl implements MemberReader {
    private final MemberRepository memberRepository;

    @Override
    public Member getMemberById(String memberId) {
        return memberRepository.findById(memberId).orElseThrow(InvalidParamException::new);
    }

    @Override
    public boolean checkMemberById(String memberId) {
        return memberRepository.existsByMemberId(memberId);
    }

    @Override
    public Member getMemberByEmail(String memberEmail) {
        return memberRepository.findByMemberEmail(memberEmail).orElseThrow(InvalidParamException::new);
    }

    @Override
    public boolean checkMemberByEmail(String memberEmail) {
        return memberRepository.existsByMemberEmail(memberEmail);
    }

    @Override
    public boolean loginMember(String memberId, String memberPassword) {
        return memberRepository.existsByMemberIdAndMemberPassword(memberId, memberPassword);
    }
}
