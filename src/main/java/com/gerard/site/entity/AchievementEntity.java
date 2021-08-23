package com.gerard.site.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Class represents single record from table <i>gerard.achievement</i> ,
 * note that primary key of record is represented
 * by is superclass AbstractEntity {@link AbstractEntity}
 * by it's instance filed 'id' {@link AbstractEntity#id} .
 *
 * <b>Primary key column is named 'achievement_id' </b> .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class AchievementEntity extends AbstractEntity<Integer> implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * Represents column 'achievement_type' in the table <i>gerard.achievement</i>.
     */
    private String achievementType;

    /**
     * Represents column 'dog_id' in the table <i>gerard.achievement</i>.
     */
    private int dogId;

    /**
     * Represents column 'achievement_date' in the table <i>gerard.achievement</i>.
     */
    private Date achievementDate;

    public AchievementEntity() {
        throw new UnsupportedOperationException();
    }

    public String getAchievementType() {
        return achievementType;
    }

    public void setAchievementType(String achievementType) {
        this.achievementType = achievementType;
    }

    public int getDogId() {
        return dogId;
    }

    public void setDogId(int dogId) {
        this.dogId = dogId;
    }

    public Date getAchievementDate() {
        return achievementDate;
    }

    public void setAchievementDate(Date achievementDate) {
        this.achievementDate = achievementDate;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof AchievementEntity achievementEntity) {
            return id == null
                    ? achievementEntity.id == null
                    : id.equals(achievementEntity.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        int hashcode = super.hashCode();
        hashcode = hash * hashcode + (achievementType == null ? 0 : achievementType.hashCode());
        hashcode = hash * hashcode + dogId;
        hashcode = hash * hashcode + (achievementDate == null ? 0 : achievementDate.hashCode());
        return hashcode;
    }

    @Override
    public String toString() {
        return "AchievementEntity{"
                + "id=" + id
                + ", achievementType='" + achievementType + '\''
                + ", dogId=" + dogId
                + ", achievementDate=" + achievementDate
                + '}';
    }
}
