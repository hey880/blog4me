package org.member;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Member> members = addMember(scanner);
        for (Member member : members) {
            member.printMember();
        }
    }
    public static Member createMember(Scanner scanner) {
        System.out.println("Please input id");
        String id = scanner.next();
        System.out.println("Please input name");
        String name = scanner.next();
        System.out.println("Please input email");
        String email = scanner.next();

        Member member = new Member(id, name, email);
        return member;
    }

    public static List<Member> addMember(Scanner scanner) {
        Integer memberCount;

        List<Member> members = new ArrayList<Member>();

        System.out.print("등록하실 인원을 입력하세요.");
        memberCount = scanner.nextInt();

        for (int i=1; i <= memberCount; i++) {
            Member member = createMember(scanner);
            members.add(member);
        }

        return members;
    }
}
