package com.gerard.site.service.view;

import com.gerard.site.service.entity.DogEntity;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Dog implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private DogEntity.DogSex dogSex;
    private String nickname;
    private String fullname;
    private Date birthday;
    private String avatarPhotoPath;
    private String pedigreePhotoPath;
    private String description;
    private boolean active;

    public Dog() {
    }

    public DogEntity.DogSex getDogSex() {
        return dogSex;
    }

    public void setDogSex(DogEntity.DogSex dogSex) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Dog that =
                (Dog) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb =
                new StringBuilder("Dog{");
        sb.append("id=").append(id);
        sb.append("dogSex=").append(dogSex);
        sb.append(", nickname='").append(nickname).append('\'');
        sb.append(", fullname='").append(fullname).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append(", avatarPhotoPath='").append(avatarPhotoPath).append('\'');
        sb.append(", pedigreePhotoPath='").append(pedigreePhotoPath).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }
}
