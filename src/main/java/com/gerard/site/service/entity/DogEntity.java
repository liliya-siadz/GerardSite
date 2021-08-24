package com.gerard.site.service.entity;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

/**
 * Class represents single record from table <i>gerard.dog</i> ,
 * note that primary key of record is represented
 * by is superclass AbstractEntity {@link AbstractEntity}
 * by it's instance filed 'id' {@link AbstractEntity#id} .
 *
 * <b>Primary key column is named 'dog_id' </b> .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class DogEntity extends AbstractEntity<Integer> implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;

    /**
     * Possible values for instance field {@code dogSex}
     * {@link DogEntity#dogSex} .
     */
    public enum DogSex {
        MALE,
        FEMALE
    }

    /**
     * Represents column 'dog_sex'
     * in the table <i>gerard.dog</i> .
     */
    private DogSex dogSex;

    /**
     * Represents column 'nickname'
     * in the table <i>gerard.dog</i> .
     */
    private String nickname;

    /**
     * Represents column 'fullname'
     * in the table <i>gerard.dog</i> .
     */
    private String fullname;

    /**
     * Represents column 'birthday'
     * in the table <i>gerard.dog</i> .
     */
    private Date birthday;

    /**
     * Represents column 'avatar_photo_path'
     * in the table <i>gerard.dog</i> .
     */
    private String avatarPhotoPath;

    /**
     * Represents column 'pedigree_photo_path'
     * in the table <i>gerard.dog</i> .
     */
    private String pedigreePhotoPath;

    /**
     * Represents column 'description'
     * in the table <i>gerard.dog</i> .
     */
    private String description;

    /**
     * Represents column 'active'
     * in the table <i>gerard.dog</i> .
     */
    private boolean active;

    public DogEntity() {
    }

    public DogSex getDogSex() {
        return dogSex;
    }

    public void setDogSex(DogSex dogSex) {
        this.dogSex = dogSex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAvatarPhotoPath() {
        return avatarPhotoPath;
    }

    public void setAvatarPhotoPath(String avatarPhotoPath) {
        this.avatarPhotoPath = avatarPhotoPath;
    }

    public String getPedigreePhotoPath() {
        return pedigreePhotoPath;
    }

    public void setPedigreePhotoPath(String pedigreePhotoPath) {
        this.pedigreePhotoPath = pedigreePhotoPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof DogEntity dogEntity) {
            return id == null
                    ? dogEntity.id == null
                    : id.equals(dogEntity.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        int hashcode = super.hashCode();
        hashcode = hash * hashcode + (dogSex == null ? 0 : dogSex.hashCode());
        hashcode = hash * hashcode + (nickname == null ? 0 : nickname.hashCode());
        hashcode = hash * hashcode + (fullname == null ? 0 : fullname.hashCode());
        hashcode = hash * hashcode + (birthday == null ? 0 : birthday.hashCode());
        hashcode = hash * hashcode + (avatarPhotoPath == null ? 0 : avatarPhotoPath.hashCode());
        hashcode = hash * hashcode + (pedigreePhotoPath == null ? 0 : pedigreePhotoPath.hashCode());
        hashcode = hash * hashcode + (description == null ? 0 : description.hashCode());
        hashcode = hash * hashcode + (active ? 1 : 0);
        return hashcode;
    }

    @Override
    public String toString() {
        return "DogEntity{"
                + "id=" + id
                + ", dogSex=" + dogSex
                + ", nickname='" + nickname + '\''
                + ", fullname='" + fullname + '\''
                + ", birthday=" + birthday
                + ", avatarMediaId=" + avatarPhotoPath
                + ", pedigreeMediaId=" + pedigreePhotoPath
                + ", description='" + description + '\''
                + ", isActive='" + active + '\''
                + '}';
    }

    /**
     * Nested service class that provides
     * creating object of class DogEntity {@link DogEntity}
     * and realizes creational design pattern 'Builder' .
     */
    public static class Builder {
        private DogEntity dogEntity;

        public Builder() {
            dogEntity = new DogEntity();
        }

        public Builder id(Integer id) {
            dogEntity.id = id;
            return this;
        }

        public Builder dogSex(DogSex dogSex) {
            dogEntity.dogSex = dogSex;
            return this;
        }

        public Builder nickname(String nickname) {
            dogEntity.nickname = nickname;
            return this;
        }

        public Builder fullname(String fullname) {
            dogEntity.fullname = fullname;
            return this;
        }

        public Builder birthday(Date birthday) {
            dogEntity.birthday = birthday;
            return this;
        }

        public Builder avatarPhotoPath(String avatarPhotoPath) {
            dogEntity.avatarPhotoPath = avatarPhotoPath;
            return this;
        }

        public Builder pedigreePhotoPath(String pedigreePhotoPath) {
            dogEntity.pedigreePhotoPath = pedigreePhotoPath;
            return this;
        }

        public Builder description(String description) {
            dogEntity.description = description;
            return this;
        }


        public Builder active(boolean isActive) {
            dogEntity.active = isActive;
            return this;
        }

        public DogEntity build() {
            return dogEntity;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object instanceof DogEntity.Builder dogEntityBuilder) {
                return (dogEntity == null)
                        ? dogEntityBuilder.dogEntity == null
                        : dogEntity.equals(dogEntityBuilder.dogEntity);
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            int hashcode = hash + 31 * (dogEntity == null ? 0 : dogEntity.hashCode());
            return hashcode;
        }

        @Override
        public String toString() {
            return "DogEntity.Builder{"
                    + "dogEntity=" + dogEntity
                    + '}';
        }
    }
}
