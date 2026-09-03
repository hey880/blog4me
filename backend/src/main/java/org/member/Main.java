package org.member;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Member> members = addMember();
        for(int i=0; i < members.size(); i++) {
            System.out.println(members.get(i).getId());
            System.out.println(members.get(i).getName());
            System.out.println(members.get(i).getEmail());
        }
    }

    public static List<Member> addMember() {
        Integer memberCount;

        List<Member> members = new ArrayList<Member>();

        Scanner scanner = new Scanner(System.in);

        System.out.print("등록하실 인원을 입력하세요.");
        memberCount = scanner.nextInt();

        for (int i=1; i <= memberCount; i++) {
            Member member = new Member(scanner.next(),scanner.next(),scanner.next());
            members.add(member);
        }

        return members;
    }
}
