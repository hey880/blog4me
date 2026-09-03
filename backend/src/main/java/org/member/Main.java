package org.member;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Map<String, String>> members = addMember();
        for(int i=0; i < members.size(); i++) {
            System.out.println(members.get(i));
        }
    }

    public static List<Map<String, String>> addMember() {
        String id;
        String name;
        String email;
        Integer memberCount;

        List<Map<String, String>> members = new ArrayList<Map<String, String>>();

        Scanner scanner = new Scanner(System.in);

        System.out.print("등록하실 인원을 입력하세요.");
        memberCount = scanner.nextInt();

        for (int i=1; i <= memberCount; i++) {
            Map member = new HashMap();
            System.out.print("id를 입력하세요.");
            id = scanner.next();
            member.put("id", id);
            System.out.print("name을 입력하세요.");
            name = scanner.next();
            member.put("name", name);
            System.out.print("email을 입력하세요.");
            email = scanner.next();
            member.put("email", email);
            members.add(member);
        }

        return members;
    }
}
