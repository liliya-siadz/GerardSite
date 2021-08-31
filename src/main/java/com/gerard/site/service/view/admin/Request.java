package com.gerard.site.service.view.admin;

import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.service.entity.RequestEntity;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int requestId;
    private RequestEntity.RequestStatus requestStatus;
    private RequestEntity.RequestType requestType;
    private String content;
    private Date dateFact;
    private String reply;
    private String email;
    private String name;
    private String surname;
    private String patronymic;
    private String phone;
    private String nickname;
    private String fullname;
    private DogEntity.DogSex dogSex;
    private Date birthday;
    private String avatarPhotoPath;

    public Request() {
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public RequestEntity.RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestEntity.RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public RequestEntity.RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestEntity.RequestType requestType) {
        this.requestType = requestType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateFact() {
        return dateFact;
    }

    public void setDateFact(Date dateFact) {
        this.dateFact = dateFact;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public DogEntity.DogSex getDogSex() {
        return dogSex;
    }

    public void setDogSex(DogEntity.DogSex dogSex) {
        this.dogSex = dogSex;
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


    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof Request request) {
            return requestId == request.requestId;
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RequestAndAppUserAndDog{");
        sb.append("requestId=").append(requestId);
        sb.append(", requestStatus=").append(requestStatus);
        sb.append(", requestType=").append(requestType);
        sb.append(", content='").append(content).append('\'');
        sb.append(", dateFact=").append(dateFact);
        sb.append(", reply='").append(reply).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", patronymic='").append(patronymic).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", nickname='").append(nickname).append('\'');
        sb.append(", fullname='").append(fullname).append('\'');
        sb.append(", dogSex=").append(dogSex);
        sb.append(", birthday=").append(birthday);
        sb.append(", avatarPhotoPath='").append(avatarPhotoPath).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * Nested service class that provides
     * creating object of class DogEntity {@link DogEntity}
     * and realizes creational design pattern 'Builder' .
     */
    public static class Builder {
        private Request request;

        public Builder() {
            request = new Request();
        }

        public Request.Builder requestId(int requestId) {
            request.requestId = requestId;
            return this;
        }

        public Request.Builder requestStatus(
                RequestEntity.RequestStatus requestStatus) {
            request.requestStatus = requestStatus;
            return this;
        }

        public Request.Builder requestType(
                RequestEntity.RequestType requestType) {
            request.requestType = requestType;
            return this;
        }


        public Request.Builder content(String content) {
            request.content = content;
            return this;
        }

        public Request.Builder dateFact(Date dateFact) {
            request.dateFact = dateFact;
            return this;
        }

        public Request.Builder reply(String reply) {
            request.reply = reply;
            return this;
        }

        public Request.Builder email(String email) {
            request.email = email;
            return this;
        }


        public Request.Builder name(String name) {
            request.name = name;
            return this;
        }

        public Request.Builder surname(String surname) {
            request.surname = surname;
            return this;
        }

        public Request.Builder patronymic(String patronymic) {
            request.patronymic = patronymic;
            return this;
        }

        public Request.Builder phone(String phone) {
            request.phone = phone;
            return this;
        }

        public Request.Builder nickname(String nickname) {
            request.nickname = nickname;
            return this;
        }

        public Request.Builder fullname(String fullname) {
            request.fullname = fullname;
            return this;
        }

        public Request.Builder dogSex(DogEntity.DogSex dogSex) {
            request.dogSex = dogSex;
            return this;
        }

        public Request.Builder birthday(Date birthday) {
            request.birthday = birthday;
            return this;
        }

        public Request.Builder avatarPhotoPath(
                String avatarPhotoPath) {
            request.avatarPhotoPath = avatarPhotoPath;
            return this;
        }

        public Request build() {
            return request;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object instanceof Request.Builder
                    requestAndAppUserAndDogBuilder) {
                return (request == null)
                        ? requestAndAppUserAndDogBuilder.request == null
                        : request.equals(requestAndAppUserAndDogBuilder.request);
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            int hashcode = hash + 31 * (request == null
                    ? 0
                    : request.hashCode());
            return hashcode;
        }

        @Override
        public String toString() {
            return "DogEntity.Builder{"
                    + "dogEntity=" + request
                    + '}';
        }
    }
}
