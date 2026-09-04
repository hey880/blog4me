package org.member;

import java.util.*;

public class MemberService {
    List<MemberDto> members = new ArrayList<MemberDto>();
    public void addMember(MemberDto member) {
        members.add(member);
    }
    public List<MemberDto> getAllMember () {
        // members arraylist를 그대로 Main으로 넘겨주면 Main에서 이 값을 마음대로 수정/삭제할 수 있음
        // 따라서 복사된 값을 넘겨주기 위해 ArrayList를 새로 선언하여 반환한다. (캡슐화)
        return new ArrayList<>(members);
    }
    public Optional<MemberDto> getMemberById (String id) {
        for (MemberDto member : members) {
            if (member.getId().equals(id)) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }
    public MemberDto getMemberByIdOrThrow(String id) {
        Optional<MemberDto> member = getMemberById(id);
        return member.orElseThrow(() -> new RuntimeException("Not exist id: " + id));
        // 관례상 unchecked exception은 메서드 뒤에 throws~ 안 붙임
        // RuntimeException도 unchecked exception에 해당하기 때문에 이렇게 선언만 하고
        // 메서드 뒤에 throws를 붙이지는 않는다.
    }
    public boolean updateMember(String id, String name, String email) {
        Optional<MemberDto> member = getMemberById(id);
        if (member.isPresent()) {
            member.get().setName(name);
            member.get().setEmail(email);
            return true;
        } else {
            return false;
        }
    }
    public boolean deleteMember(String id) {
        // removeIf는 boolean 값을 반환한다.
        return members.removeIf(member -> member.getId().equals(id));
    }
    // Stream을 이용한 구현
    // List는 빈 List로 두지 Optional 타입으로 처리하지 않는다.
    public List<MemberDto> findByName(String keyword) {
        return members.stream().filter(member -> member.getName().contains(keyword)).toList();
    }
}
