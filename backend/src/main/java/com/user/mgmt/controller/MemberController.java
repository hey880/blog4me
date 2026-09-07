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
    public String getMemeberById(@PathVariable String id) {
        return "요청받은 id: " + id;
    }

    @GetMapping("/members/search")
    public String searchByName(@RequestParam(required=false) String name) {
        if (name == null) {
            return "검색어가 없습니다.";
        }
        return "검색어: " + name;
    }
}
