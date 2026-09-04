package org.member;

import javax.swing.text.html.Option;
import java.util.*;

public class MemberService {
    List<MemberDto> members = new ArrayList<MemberDto>();
    public void addMember(MemberDto member) {
        members.add(member);
    }
    public List<MemberDto> getAllMember () {
        return members;
    }
    public Optional<MemberDto> getMemberById (String id) {
        for (MemberDto member : members) {
            if (member.getId().equals(id)) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
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
}
