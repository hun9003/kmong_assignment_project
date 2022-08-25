package com.parkjinhun.shopping.shopping_mall_project.domain.member;

public interface MemberStore {
    Member saveMember(Member member);

    void deleteAll();
}
