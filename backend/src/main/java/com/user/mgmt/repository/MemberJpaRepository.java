package com.user.mgmt.repository;

import com.user.mgmt.dto.MemberDto;
import com.user.mgmt.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberJpaRepository extends JpaRepository<Member, String> {
    // 이 Repository 하나만 만들어도 save(), findById(), findAll(), deleteByID() 등이 전부 자동생성된다.
    List<Member> findByNameContaining(String keyword);
}