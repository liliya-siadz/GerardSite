package com.gerard.site.service.entity;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

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
public class PhotoEntity extends AbstractEntity<Integer> implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;

    /**
     * Represents column 'path'
     * in the table <i>gerard.photo</i> .
     */
    private String photoPath;

    /**
     * Represents column 'name'
     * in the table <i>gerard.photo</i> .
     */
    private String name;

    /**
     * Represents column 'dog_id'
     * in the table <i>gerard.photo</i> .
     * Can be null value in the table
     */
    private int dogId;


    /**
     * Represents column 'photo_date'
     * in the table <i>gerard.photo</i> .
     * Can be null value in the table
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
        hashcode = hash * hashcode + (name == null ? 0 : name.hashCode());
        hashcode = hash * hashcode + (photoDate == null ? 0 : photoDate.hashCode());
        hashcode = hash * hashcode + dogId;
        return hashcode;
    }

    @Override
    public String toString() {
        return "PhotoEntity{"
                + "id=" + id
                + ", path='" + photoPath + '\''
                + ", name='" + name + '\''
                + ", dogId=" + dogId
                + '}';
    }
}
