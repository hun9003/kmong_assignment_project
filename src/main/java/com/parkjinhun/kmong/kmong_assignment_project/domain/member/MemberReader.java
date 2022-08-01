package com.parkjinhun.kmong.kmong_assignment_project.domain.member;

public interface MemberReader {
    Member getMemberById(String memberId);
    boolean checkMemberById(String memberId);

    Member getMemberByEmail(String memberEmail);
    boolean checkMemberByEmail(String memberEmail);

    boolean loginMember(String memberId, String memberPassword);
}
