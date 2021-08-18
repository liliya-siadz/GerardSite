package com.gerard.site.entity;

import java.util.Objects;

public class UserEntity extends AbstractEntity<Integer> {
    private AppUserStatus appUserStatus;
    private AppUserRole appUserRole;
    private AppUserSex appUserSex;
    private String email;
    private String name;
    private String surname;
    private String patronymic;
    private int phone;

    private UserEntity() {
        super();
    }

    public AppUserStatus getAppUserStatus() {
        return appUserStatus;
    }

    public AppUserRole getAppUserRole() {
        return appUserRole;
    }

    public AppUserSex getAppUserSex() {
        return appUserSex;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getPhone() {
        return phone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(appUserStatus, appUserRole,
                appUserSex, email, name, surname, patronymic);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "appUserStatus=" + appUserStatus +
                ", appUserRole=" + appUserRole +
                ", appUserSex=" + appUserSex +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phone='" + phone + '\n' +
                '}';
    }

    public enum AppUserStatus {
        ACTIVE, NON_CONFIRMED, BLOCKED, ARCHIVED
    }

    public enum AppUserRole {
        ADMIN, CLIENT
    }

    public enum AppUserSex {
        MALE, FEMALE, BINARY
    }

    public static class Builder {

        private UserEntity userEntity;

        public Builder() {
            userEntity = new UserEntity();
        }

        public Builder id(Integer id) {
            userEntity.id = id;
            return this;
        }

        public Builder appUserStatus(AppUserStatus appUserStatus) {
            userEntity.appUserStatus = appUserStatus;
            return this;
        }

        public Builder appUserRole(AppUserRole appUserRole) {
            userEntity.appUserRole = appUserRole;
            return this;
        }

        public Builder appUserSex(AppUserSex appUserSex) {
            userEntity.appUserSex = appUserSex;
            return this;
        }

        public Builder email(String email) {
            userEntity.email = email;
            return this;
        }

        public Builder name(String name) {
            userEntity.name = name;
            return this;
        }

        public Builder surname(String surname) {
            userEntity.surname = surname;
            return this;
        }

        public Builder patronymic(String patronymic) {
            userEntity.patronymic = patronymic;
            return this;
        }

        public Builder phone(int phone) {
            userEntity.phone = phone;
            return this;
        }

        public UserEntity build() {
            return userEntity;
        }
    }
}
