package com.nd_monitoring.service;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"), //사이트 관리자 권한
    USER("ROLE_USER"); //유저 권한

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}
