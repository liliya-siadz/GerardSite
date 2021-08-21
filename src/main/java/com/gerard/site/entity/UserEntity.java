package com.gerard.site.entity;

/**
 * Class represents single record from table <i>gerard.app_user</i> ,
 * note that primary key of record is represented
 * by is superclass AbstractEntity {@link AbstractEntity}
 * by it's instance filed 'id' {@link AbstractEntity#id} .
 *
 * <b>Primary key column is named 'app_user_id"</b>
 * <p>
 * Doesn't store value from column 'password'
 * for security reasons.
 * <p>
 * Has nested class Builder {@link UserEntity.Builder}
 * that provides and simplifies creating class instance .
 * </p>
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class UserEntity extends AbstractEntity<Integer> {
    /**
     * Represents column 'app_user_status' in the table <i>gerard.app_user</i> .
     * Stores enum value in table .
     */
    private AppUserStatus appUserStatus;

    /**
     * Represents column 'app_user_role' in the table <i>gerard.app_user</i> .
     * Stores enum value in table .
     */
    private AppUserRole appUserRole;

    /**
     * Represents column 'app_user_sex' in the table <i>gerard.app_user</i> .
     * Stores enum value in table, may store null value in database .
     */
    private AppUserSex appUserSex;

    /**
     * Represents column 'email' in the table <i>gerard.app_user</i> .
     */
    private String email;

    /**
     * Represents column 'name' in the table <i>gerard.app_user</i> .
     */
    private String name;

    /**
     * Represents column 'surname' in the table <i>gerard.app_user</i> .
     * May store null value in database .
     */
    private String surname;

    /**
     * Represents column 'patronymic' in the table <i>gerard.app_user</i> .
     * May store null value in database .
     */
    private String patronymic;

    /**
     * Represents column 'phone' in the table <i>gerard.app_user</i> .
     */
    private int phone;

    public UserEntity() {
        super();
    }

    public AppUserStatus getAppUserStatus() {
        return appUserStatus;
    }

    public void setAppUserStatus(AppUserStatus appUserStatus) {
        this.appUserStatus = appUserStatus;
    }

    public AppUserRole getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(AppUserRole appUserRole) {
        this.appUserRole = appUserRole;
    }

    public AppUserSex getAppUserSex() {
        return appUserSex;
    }

    public void setAppUserSex(AppUserSex appUserSex) {
        this.appUserSex = appUserSex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof UserEntity userEntity) {
            return id == null
                    ? userEntity.id == null
                    : id.equals(userEntity.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        int hashcode = super.hashCode();
        hashcode = hash * hashcode + (appUserStatus == null ? 0 : appUserStatus.hashCode());
        hashcode = hash * hashcode + (appUserRole == null ? 0 : appUserRole.hashCode());
        hashcode = hash * hashcode + (appUserSex == null ? 0 : appUserSex.hashCode());
        hashcode = hash * hashcode + (email == null ? 0 : email.hashCode());
        hashcode = hash * hashcode + (name == null ? 0 : name.hashCode());
        hashcode = hash * hashcode + (surname == null ? 0 : surname.hashCode());
        hashcode = hash * hashcode + (patronymic == null ? 0 : patronymic.hashCode());
        hashcode = hash * hashcode + phone;
        return hashcode;
    }

    @Override
    public String toString() {
        return "UserEntity{"
                + "id=" + id
                + ", appUserStatus=" + appUserStatus
                + ", appUserRole=" + appUserRole
                + ", appUserSex=" + appUserSex
                + ", email='" + email + '\''
                + ", name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", patronymic='" + patronymic + '\''
                + ", phone=" + phone
                + '}';
    }

    /**
     * Possible values for instance field {@code appUserStatus}
     * {@link UserEntity#appUserStatus} .
     */
    public enum AppUserStatus {
        /**
         * Represents next user's status:
         * user was successfully registered .
         */
        ACTIVE,

        /**
         * Represents next user's status:
         * user had does not confirmed his credentials
         */
        NON_CONFIRMED,

        /**
         * Represents next user's status:
         * user was did some harmfully actions
         * and couldn't be authorized to system .
         */
        BLOCKED,

        /**
         * Represents next user's status:
         * user deleted had deleted his account .
         */
        ARCHIVED
    }

    /**
     * Possible values for instance field {@code appUserRole}
     * {@link UserEntity#appUserRole} .
     */
    public enum AppUserRole {
        ADMIN, CLIENT, GUEST
    }

    /**
     * Possible values for instance field {@code appUserSex}
     * {@link UserEntity#appUserSex} .
     */
    public enum AppUserSex {
        MALE, FEMALE, BINARY
    }

    /**
     * Nested service class that provides
     * creating object of class UserEntity {@link UserEntity}
     * and realizes creational design pattern 'Builder' .
     */
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

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object instanceof UserEntity.Builder userEntityBuilder) {
                return (userEntity == null)
                        ? userEntityBuilder.userEntity == null
                        : userEntity.equals(userEntityBuilder.userEntity);
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            int hashcode = hash + 31 * (userEntity == null ? 0 : userEntity.hashCode());
            return hashcode;
        }

        @Override
        public String toString() {
            return "UserEntity.Builder{"
                    + "userEntity=" + userEntity
                    + '}';
        }
    }
}
