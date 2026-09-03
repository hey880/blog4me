package org.member;

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
}
