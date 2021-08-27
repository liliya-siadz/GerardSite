package com.gerard.site.service.entity;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

/**
 * Class represents single record from table <i>photo</i> ,
 * note that primary key of record is represented
 * by is superclass AbstractEntity {@link AbstractEntity}
 * by it's instance filed 'id' {@link AbstractEntity#id} .
 *
 * <b>Primary key column is named 'photo_id' </b> .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class PhotoEntity extends AbstractEntity<Integer> implements Serializable {

    /**
     * Possible values for column 'photo_type'
     * in table <i>photo</i>
     */
    public enum PhotoType {
        PHOTO, AVATAR, PEDIGREE
    }

    @Serial
    private static final long serialVersionUID=1L;

    /**
     * Represents column 'path'
     * in table <i>photo</i> .
     */
    private String photoPath;

    /**
     * Represents column 'photo_type'
     * in table <i>photo</i>
     */
   private PhotoType photoType;

    /**
     * Represents column 'dog_id'
     * in table <i>photo</i> .
     */
    private int dogId;


    /**
     * Represents column 'photo_date'
     * in  table <i>photo</i> .
     */
    private Date photoDate;

    public PhotoEntity() {
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public PhotoType getPhotoType() {
        return photoType;
    }

    public void setPhotoType(PhotoType photoType) {
        this.photoType = photoType;
    }

    public int getDogId() {
        return dogId;
    }

    public void setDogId(int dogId) {
        this.dogId = dogId;
    }

    public Date getPhotoDate() {
        return photoDate;
    }

    public void setPhotoDate(Date photoDate) {
        this.photoDate = photoDate;
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
        hashcode = hash * hashcode + (photoPath == null ? 0 : photoPath.hashCode());
        hashcode = hash * hashcode + (photoType == null ? 0 : photoType.hashCode());
        hashcode = hash * hashcode + (photoDate == null ? 0 : photoDate.hashCode());
        hashcode = hash * hashcode + dogId;
        return hashcode;
    }

    @Override
    public String toString() {
        return "PhotoEntity{"
                + "id=" + id
                + ", path='" + photoPath + '\''
                + ", photoType='" + photoType + '\''
                + ", dogId=" + dogId
                + '}';
    }
}
