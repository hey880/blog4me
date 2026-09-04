package org.member;

import java.util.List;
import java.util.Scanner;

public class MemberDto implements Cloneable{
    private String id;
    private String name;
    private String email;

    public MemberDto(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // 값 복사를 위한 생성자
    public MemberDto(MemberDto other) {
        this.id = other.id;
        this.name = other.name;
        this.email = other.email;
    }

    public String getId() {
        return id;
    };
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void printMember() {
        System.out.println("[Member Info - "+"id: "+id+", name: "+name+", email : "+email+"]");
    }
}
