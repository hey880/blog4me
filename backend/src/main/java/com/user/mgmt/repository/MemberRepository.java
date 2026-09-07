package com.user.mgmt.repository;

import com.user.mgmt.dto.MemberDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(MemberDto member);
    List<MemberDto> findAll();
    Optional<MemberDto> findById(String id);
    List<MemberDto> findByName(String keyword);
    boolean delete(String id);
}
