package org.member;

import java.util.*;

import org.member.MemberService;

import javax.swing.text.html.Option;

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
        System.out.println("===all member list===");
        for (MemberDto member : allMember) {
            member.printMember();
        }
        // id 검색 조회
        System.out.println("Please input id what you want to search.");
        String searchId = scanner.next();
        search(memberService, searchId);

        // throw try-catch
        try {
            MemberDto member = memberService.getMemberByIdOrThrow(searchId);
            System.out.println("===try catch value result===");
            member.printMember();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        // 수정
        System.out.println("Please input id what you want to edit.");
        String memberEditId = scanner.next();
        Optional<MemberDto> getMember = search(memberService, memberEditId);
        Optional<MemberDto> prevMemberInfo = getMember.map(MemberDto::new);
        if (getMember.isPresent()) {
            System.out.println("Please input 'name' what you want to edit.");
            String name = scanner.next();
            System.out.println("Please input 'email' what you want to edit.");
            String email = scanner.next();
            boolean editSuccess = memberService.updateMember(memberEditId, name, email);
            if (editSuccess) {
                System.out.println("===Previous Member Info===");
                prevMemberInfo.get().printMember();
                System.out.println("===Edit result===");
                getMember.get().printMember();
            }
        }
        // 삭제
        System.out.println("Please input id what you want to delete.");
        String deleteId = scanner.next();
        boolean deleteSuccess = memberService.deleteMember(deleteId);
        if (deleteSuccess) {
            System.out.println("ID: "+"'"+deleteId+"'"+" Delete success");
            System.out.println("===All member list===");
            for (MemberDto member : allMember) {
                member.printMember();
            }
        } else {
            System.out.println("ID: "+"'"+deleteId+"'"+" Delete failed");
        }
        // 이름을 통한 조회
        System.out.println("Please input name what you want to find.");
        String name = scanner.next();
        List<MemberDto> memberList = memberService.findByName(name);
        if (memberList.isEmpty()) {
            System.out.println("name: "+name+" member is not exist.");
        } else {
            for (MemberDto member : memberList) {
                member.printMember();
            }
        }
    }
    public static Optional<MemberDto> search(MemberService memberService, String id) {
        Optional<MemberDto> searchResult = memberService.getMemberById(id);
        if (searchResult.isPresent()) {
            searchResult.get().printMember();
            return searchResult;
        } else {
            System.out.println("'"+id+"'"+" is not exist.");
            return Optional.empty();
        }
    }
}
