package com.parkjinhun.kmong.kmong_assignment_project.infrastructure.member;

import com.parkjinhun.kmong.kmong_assignment_project.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    boolean existsByMemberId(String memberId);
    boolean existsByMemberEmail(String memberEmail);
    Optional<Member> findByMemberEmail(String memberEmail);

    boolean existsByMemberIdAndMemberPassword(String memberId, String memberPassword);
}
