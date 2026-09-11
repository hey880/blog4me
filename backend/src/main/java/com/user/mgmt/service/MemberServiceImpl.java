package com.user.mgmt.service;

import com.user.mgmt.dto.MemberDto;
import com.user.mgmt.entity.Member;
import com.user.mgmt.repository.MemberJpaRepository;
import com.user.mgmt.utils.MemberNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{
//    final private MemberRepository memberRepository;
    final private MemberJpaRepository memberJpaRepository;

//    public MemberServiceImpl (MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
    public MemberServiceImpl (MemberJpaRepository memberJpaRepository) {
        this.memberJpaRepository = memberJpaRepository;
    }
    public void addMember(MemberDto memberDto) {
        // memberRepository.save(member);
        Member member = new Member(memberDto.getId(), memberDto.getName(), memberDto.getEmail());
        memberJpaRepository.save(member);
    }
    public List<MemberDto> getAllMember () {
        // members arraylist를 그대로 Main으로 넘겨주면 Main에서 이 값을 마음대로 수정/삭제할 수 있음
        // 따라서 복사된 값을 넘겨주기 위해 ArrayList를 새로 선언하여 반환한다. (캡슐화)
        List<Member> allmember = memberJpaRepository.findAll();
        List <MemberDto> allMemberDtoList = allmember.stream()
                .map(entity -> new MemberDto(entity.getId(), entity.getName(), entity.getEmail()))
                .toList();
        return allMemberDtoList;
    }
    public Optional<MemberDto> getMemberById (String id) {
        Optional<Member> member = memberJpaRepository.findById(id);
        if (member.isPresent()) {
            MemberDto memberDto = new MemberDto(member.get().getId(), member.get().getName(), member.get().getEmail());
            return Optional.of(memberDto);
        }
        return Optional.empty();
    }
    public MemberDto getMemberByIdOrThrow(String id) {
        Optional<MemberDto> member = getMemberById(id);
        return member.orElseThrow(() -> new MemberNotFoundException(id));
        // 관례상 unchecked exception은 메서드 뒤에 throws~ 안 붙임
        // RuntimeException도 unchecked exception에 해당하기 때문에 이렇게 선언만 하고
        // 메서드 뒤에 throws를 붙이지는 않는다.
    }
    public boolean updateMember(String id, String name, String email) {
        // DB에 저장된 값을 가져와서 변경해야함.
        Optional<Member> memberOpt = memberJpaRepository.findById(id); // Entity로 직접 조회
        if (memberOpt.isPresent()) {
            // 값 업데이트
            Member member = memberOpt.get();
            member.setName(name);
            member.setEmail(email);
            memberJpaRepository.save(member);  // 명시적으로 다시 저장
            return true;
        }
        return false;
    }
    public boolean deleteMember(String id) {
        // removeIf는 boolean 값을 반환한다.
        // return memberRepository.delete(id);
        if (memberJpaRepository.existsById(id)) {
            memberJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
    // Stream을 이용한 구현
    // List는 빈 List로 두지 Optional 타입으로 처리하지 않는다.
//    public List<MemberDto> findByName(String keyword) {
//        return memberRepository.findByName(keyword);
//    }
    public List<MemberDto> findByName(String keyword) {
        List<Member> members = memberJpaRepository.findByNameContaining(keyword);
        return members.stream()
                .map(entity -> new MemberDto(entity.getId(), entity.getName(), entity.getEmail()))
                .toList();
    }

    // 페이징
    // Page에도 .map()이 있어서 List 처럼 Stream 안 거치고 바로 Entity -> DTO로 변환이 가능
    public Page<MemberDto> getMemberPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Member> memberPage = memberJpaRepository.findAll(pageable);
        return memberPage.map(entity -> new MemberDto(entity.getId(), entity.getName(), entity.getEmail()));
    }
}
