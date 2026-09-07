package com.user.mgmt.repository;

import com.user.mgmt.dto.MemberDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository{
    private List<MemberDto> members = new ArrayList<MemberDto>();
    public void save(MemberDto member) {members.add(member);}
    public List<MemberDto> findAll() {
        return new ArrayList<>(members); // 캡슐화를 위해서 새 ArrayList를 선언(=복사)하여 반환
    }
    public Optional<MemberDto> findById(String id) {
        for (MemberDto member : members) {
            if (member.getId().equals(id)) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }
    public List<MemberDto> findByName(String keyword) {
        return this.members.stream().filter(member -> member.getName().contains(keyword)).toList();
    }
    public boolean delete(String id) {
        return members.removeIf(member -> member.getId().equals(id));
    }
}
