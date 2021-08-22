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
 * Has nested class Builder {@link AppUserEntity.Builder}
 * that provides and simplifies creating class instance .
 * </p>
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class AppUserEntity extends AbstractEntity<Integer> {

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
     * Can be null value in the table
     */
    private String surname;

    /**
     * Represents column 'patronymic' in the table <i>gerard.app_user</i> .
     * Can be null value in the table
     */
    private String patronymic;

    /**
     * Represents column 'phone' in the table <i>gerard.app_user</i> .
     */
    private int phone;

    /**
     * Represents column 'is_admin' in the table <i>gerard.app_user</i> .
     */
    private boolean isAdmin;

    public AppUserEntity() {
        super();
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof AppUserEntity appUserEntity) {
            return id == null
                    ? appUserEntity.id == null
                    : id.equals(appUserEntity.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        int hashcode = super.hashCode();
        hashcode = hash * hashcode + (email == null ? 0 : email.hashCode());
        hashcode = hash * hashcode + (name == null ? 0 : name.hashCode());
        hashcode = hash * hashcode + (surname == null ? 0 : surname.hashCode());
        hashcode = hash * hashcode + (patronymic == null ? 0 : patronymic.hashCode());
        hashcode = hash * hashcode + (isAdmin ? 1 : 0);
        hashcode = hash * hashcode + phone;
        return hashcode;
    }

    @Override
    public String toString() {
        return "AppUserEntity{"
                + "id=" + id
                + ", email='" + email + '\''
                + ", name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", patronymic='" + patronymic + '\''
                + ", phone=" + phone
                + ", isAdmin=" + isAdmin
                + '}';
    }

    /**
     * Nested service class that provides
     * creating object of class UserEntity {@link AppUserEntity}
     * and realizes creational design pattern 'Builder' .
     */
    public static class Builder {

        private AppUserEntity appUserEntity;

        public Builder() {
            appUserEntity = new AppUserEntity();
        }

        public Builder id(Integer id) {
            appUserEntity.id = id;
            return this;
        }

        public Builder email(String email) {
            appUserEntity.email = email;
            return this;
        }

        public Builder name(String name) {
            appUserEntity.name = name;
            return this;
        }

        public Builder surname(String surname) {
            appUserEntity.surname = surname;
            return this;
        }

        public Builder patronymic(String patronymic) {
            appUserEntity.patronymic = patronymic;
            return this;
        }

        public Builder phone(int phone) {
            appUserEntity.phone = phone;
            return this;
        }


        public Builder isAdmin(boolean isAdmin) {
            appUserEntity.isAdmin = isAdmin;
            return this;
        }

        public AppUserEntity build() {
            return appUserEntity;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object instanceof AppUserEntity.Builder userEntityBuilder) {
                return (appUserEntity == null)
                        ? userEntityBuilder.appUserEntity == null
                        : appUserEntity.equals(userEntityBuilder.appUserEntity);
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            int hashcode = hash + 31 * (appUserEntity == null ? 0 : appUserEntity.hashCode());
            return hashcode;
        }

        @Override
        public String toString() {
            return "AppUserEntity.Builder{"
                    + "userEntity=" + appUserEntity
                    + '}';
        }
    }
}
