package com.gerard.site.entity;

public class UserEntity extends AbstractEntity<Integer>{


    private AppUserStatus appUserStatus;
    private AppUserRole appUserRole;
    private AppUserSex appUserSex;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String patronymic;


    public enum AppUserStatus {
        ACTIVE, NON_CONFIRMED, BLOCKED, ARCHIVED
    }
    public enum AppUserRole {
        ADMIN, CLIENT
    }
    public enum AppUserSex {
        MALE, FEMALE, BINARY
    }
}
