package org.member;

import java.util.*;

public interface MemberService {
    public void addMember(MemberDto member);
    public List<MemberDto> getAllMember ();
    public Optional<MemberDto> getMemberById (String id);
    public MemberDto getMemberByIdOrThrow(String id);
    public boolean updateMember(String id, String name, String email);
    public boolean deleteMember(String id);
    public List<MemberDto> findByName(String keyword);
}
