package com.gerard.site.entity;

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
public class DogEntity extends AbstractEntity<Integer> {
    /**
     * Possible values for instance field {@code dogStatus}
     * {@link DogEntity#dogStatus} .
     */
    public enum DogStatus {
        /**
         * Represents next dog's status:
         * dog is ready for exhibition
         * and can be displayed on view .
         */
        EXHIBITION,

        /**
         * Represents next dog's status:
         * dog is ready for exhibition or marriage
         * and can be displayed on view .
         */
        EXHIBITION_FERTILE,

        /**
         * Represents next dog's status:
         * dog is retired on on vacation
         * and can not be displayed on view .
         */
        NON_ACTIVE
    }

    /**
     * Possible values for instance field {@code dogSex}
     * {@link DogEntity#dogSex} .
     */
    public enum DogSex {
        MALE,
        FEMALE
    }

    /**
     * Represents column 'dog_status' in the table <i>gerard.dog</i> .
     */
    private DogStatus dogStatus;

    /**
     * Represents column 'dog_sex' in the table <i>gerard.dog</i> .
     */
    private DogSex dogSex;

    /**
     * Represents column 'nickname' in the table <i>gerard.dog</i> .
     */
    private String nickname;

    /**
     * Represents column 'fullname' in the table <i>gerard.dog</i> .
     */
    private String fullName;

    /**
     * Represents column 'birthday' in the table <i>gerard.dog</i> .
     */
    private Date birthday;

    /**
     * Represents column 'mother_dog_id' in the table <i>gerard.dog</i> .
     */
    private int motherId;

    /**
     * Represents column 'father_dog_id' in the table <i>gerard.dog</i> .
     */
    private int fatherId;

    /**
     * Represents column 'avatar_media_id' in the table <i>gerard.dog</i> .
     */
    private int avatarMediaId;

    /**
     * Represents column 'pedigree_media_id' in the table <i>gerard.dog</i> .
     */
    private int pedigreeMediaId;

    /**
     * Represents column 'home_kennel' in the table <i>gerard.dog</i> .
     */
    private boolean homeKennel;

    /**
     * Represents column 'description' in the table <i>gerard.dog</i> .
     */
    private String description;

    public DogEntity() {
        super();
    }

    public DogStatus getDogStatus() {
        return dogStatus;
    }

    public void setDogStatus(DogStatus dogStatus) {
        this.dogStatus = dogStatus;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getMotherId() {
        return motherId;
    }

    public void setMotherId(int motherId) {
        this.motherId = motherId;
    }

    public int getFatherId() {
        return fatherId;
    }

    public void setFatherId(int fatherId) {
        this.fatherId = fatherId;
    }

    public int getAvatarMediaId() {
        return avatarMediaId;
    }

    public void setAvatarMediaId(int avatarMediaId) {
        this.avatarMediaId = avatarMediaId;
    }

    public int getPedigreeMediaId() {
        return pedigreeMediaId;
    }

    public void setPedigreeMediaId(int pedigreeMediaId) {
        this.pedigreeMediaId = pedigreeMediaId;
    }

    public boolean isHomeKennel() {
        return homeKennel;
    }

    public void setHomeKennel(boolean homeKennel) {
        this.homeKennel = homeKennel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hashcode = hash * hashcode + (dogStatus == null ? 0 : dogStatus.hashCode());
        hashcode = hash * hashcode + (dogSex == null ? 0 : dogSex.hashCode());
        hashcode = hash * hashcode + (nickname == null ? 0 : nickname.hashCode());
        hashcode = hash * hashcode + (fullName == null ? 0 : fullName.hashCode());
        hashcode = hash * hashcode + (birthday == null ? 0 : birthday.hashCode());
        hashcode = hash * hashcode + motherId;
        hashcode = hash * hashcode + fatherId;
        hashcode = hash * hashcode + avatarMediaId;
        hashcode = hash * hashcode + pedigreeMediaId;
        hashcode = hash * hashcode + (homeKennel ? 1 : 0);
        hashcode = hash * hashcode + (description == null ? 0 : description.hashCode());
        return hashcode;
    }

    @Override
    public String toString() {
        return "DogEntity{"
                + "id=" + id
                + ", dogStatus=" + dogStatus
                + ", dogSex=" + dogSex
                + ", nickname='" + nickname + '\''
                + ", fullName='" + fullName + '\''
                + ", birthday=" + birthday
                + ", motherId=" + motherId
                + ", fatherId=" + fatherId
                + ", avatarMediaId=" + avatarMediaId
                + ", pedigreeMediaId=" + pedigreeMediaId
                + ", homeKennel=" + homeKennel
                + ", description='" + description + '\''
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

        public Builder dogStatus(DogStatus dogStatus) {
            dogEntity.dogStatus = dogStatus;
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

        public Builder fullName(String fullName) {
            dogEntity.fullName = fullName;
            return this;
        }

        public Builder birthday(Date birthday) {
            dogEntity.birthday = birthday;
            return this;
        }

        public Builder motherId(int motherId) {
            dogEntity.motherId = motherId;
            return this;
        }

        public Builder fatherId(int fatherId) {
            dogEntity.fatherId = fatherId;
            return this;
        }

        public Builder avatarMediaId(int avatarMediaId) {
            dogEntity.avatarMediaId = avatarMediaId;
            return this;
        }

        public Builder pedigreeMediaId(int pedigreeMediaId) {
            dogEntity.pedigreeMediaId = pedigreeMediaId;
            return this;
        }

        public Builder homeKennel(boolean homeKennel) {
            dogEntity.homeKennel = homeKennel;
            return this;
        }

        public Builder description(String description) {
            dogEntity.description = description;
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
