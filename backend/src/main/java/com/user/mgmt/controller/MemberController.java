package com.user.mgmt.controller;

import com.user.mgmt.dto.MemberDto;
import com.user.mgmt.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {
    private final MemberService memberService;

    MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public List<MemberDto> getAllMember() {
        return memberService.getAllMember();
    }

    @PostMapping("/members")
    public String addMember(@RequestBody MemberDto member) {
        memberService.addMember(member);
        return "등록 완료";
    }

    @GetMapping("/members/{id}")
    public MemberDto getMemeber(@PathVariable String id) {
        MemberDto member = memberService.getMemberByIdOrThrow(id);
        return member;
    }

    @PutMapping("/members/{id}")
    public String updateMember(@PathVariable String id, @RequestBody MemberDto member) {
        boolean result = memberService.updateMember(id, member.getName(), member.getEmail());
        if (result) {
            return id;
        } else {
            return "수정 실패";
        }
    }

    @DeleteMapping("/members/{id}")
    public String deleteMember(@PathVariable String id) {
        boolean result = memberService.deleteMember(id);
        if (result) {
            return id;
        } else {
            return "삭제 실패";
        }
    }

    @GetMapping("/members/search")
    public List<MemberDto> searchMember(@RequestParam String name) {
        List<MemberDto> result = memberService.findByName(name);
        return result;
    }
}
