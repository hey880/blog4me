package org.member;

import java.util.*;

import org.member.MemberService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input member count");
        int memberCount = scanner.nextInt();
        MemberService memberService = new MemberService();
        int i = 0;
        while (i < memberCount) {
            System.out.println("Please input id");
            String id = scanner.next();
            System.out.println("Please input name");
            String name = scanner.next();
            System.out.println("Please input email");
            String email = scanner.next();
            MemberDto member = new MemberDto(id, name, email);
            // member 추가
            memberService.addMember(member);
            i++;
        }
        // 전체 조회
        List<MemberDto> allMember = memberService.getAllMember();
        // id 검색 조회
        System.out.println("Please input id what you want to search.");
        String searchId = scanner.next();
        Optional<MemberDto> result = memberService.getMemberById(searchId);
        if (result.isPresent()) {
            result.get().printMember();
        } else {
            System.out.println("'"+searchId+"'"+" is not exist.");
        }
    }
}
