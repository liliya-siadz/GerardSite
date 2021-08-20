package com.gerard.site.entity;

/**
 * Class represents single record from table <i>gerard.photo</i> ,
 * note that primary key of record is represented
 * by is superclass AbstractEntity {@link AbstractEntity}
 * by it's instance filed 'id' {@link AbstractEntity#id} .
 *
 * <b>Primary key column is named 'photo_id' </b> .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class PhotoEntity extends AbstractEntity<Integer> {
    /**
     * Possible values for instance field {@code photoType}
     * {@link PhotoEntity#photoType} .
     */
    public enum PhotoType {
        PHOTO, AVATAR, PEDIGREE
    }

    /**
     * Represents column 'path' in the table <i>gerard.photo</i> .
     */
    private String path;

    /**
     * Represents column 'photo_type' in the table <i>gerard.photo</i> .
     */
    private PhotoType photoType;

    /**
     * Represents column 'name' in the table <i>gerard.photo</i> .
     * May store null value in database .
     */
    private String name;

    /**
     * Represents column 'dog_id' in the table <i>gerard.photo</i> .
     * May store null value in database .
     */
    private int dogId;

    /**
     * Represents column 'request_id' in the table <i>gerard.photo</i> .
     * May store null value in database .
     */
    private int requestId;

    public PhotoEntity() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public PhotoType getPhotoType() {
        return photoType;
    }

    public void setPhotoType(PhotoType photoType) {
        this.photoType = photoType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDogId() {
        return dogId;
    }

    public void setDogId(int dogId) {
        this.dogId = dogId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof PhotoEntity photoEntity) {
            return id == null
                    ? photoEntity.id == null
                    : id.equals(photoEntity.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        int hashcode = super.hashCode();
        hashcode = hash * hashcode + (path == null ? 0 : path.hashCode());
        hashcode = hash * hashcode + (photoType == null ? 0 : photoType.hashCode());
        hashcode = hash * hashcode + (name == null ? 0 : name.hashCode());
        hashcode = hash * hashcode + dogId;
        hashcode = hash * hashcode + requestId;
        return hashcode;
    }

    @Override
    public String toString() {
        return "PhotoEntity{"
                + "id=" + id
                + ", path='" + path + '\''
                + ", photoType=" + photoType
                + ", name='" + name + '\''
                + ", dogId=" + dogId
                + ", requestId=" + requestId
                + '}';
    }
}
