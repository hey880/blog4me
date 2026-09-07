package org.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberRepositoryImpl implements MemberRepository{
    private List<MemberDto> members = new ArrayList<MemberDto>();
    public void save(MemberDto member) {members.add(member);};
    public List<MemberDto> findAll() {
        // members arraylist를 그대로 Main으로 넘겨주면 Main에서 이 값을 마음대로 수정/삭제할 수 있음
        // 따라서 복사된 값을 넘겨주기 위해 ArrayList를 새로 선언하여 반환한다. (캡슐화)
        return new ArrayList<>(members);
    };
    public Optional<MemberDto> findById(String id) {
        for (MemberDto member : members) {
            if (member.getId().equals(id)) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    };
    // Stream을 이용한 구현
    // List는 빈 List로 두지 Optional 타입으로 처리하지 않는다.
    public List<MemberDto> findByName(String keyword) {
        return this.members.stream().filter((member) -> member.getName().contains(keyword)).toList();
    };
    public boolean delete(String id) {
        // removeIf는 boolean 값을 반환한다.
        return members.removeIf(member -> member.getId().equals(id));
    };
}
