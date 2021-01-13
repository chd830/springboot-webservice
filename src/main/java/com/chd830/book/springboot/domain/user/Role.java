package com.chd830.book.springboot.domain.user;

public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "사용자");

    private final String key;
    private final String title;

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    Role(String title, String key) {
        this.title = title;
        this.key = key;
    }
}

