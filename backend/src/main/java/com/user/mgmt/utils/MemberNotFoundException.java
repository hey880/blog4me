package com.user.mgmt.utils;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String id) {
        super("존재하지 않는 id입니다: " + id);
    }
}