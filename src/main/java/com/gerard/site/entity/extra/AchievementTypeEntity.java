package com.gerard.site.entity.extra;

import com.gerard.site.entity.AbstractEntity;

/**
 * Class represents single record from table <i>gerard.achievement_type</i> ,
 * note that primary key of record is represented
 * by is superclass AbstractEntity {@link AbstractEntity}
 * by it's instance filed 'id' {@link AbstractEntity#id} .
 *
 * <b>Primary key column is named 'achievement_type' </b>
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class AchievementTypeEntity extends AbstractEntity<String> {
    /**
     * Represents column 'achievement_type_descr' in the table <i>gerard.app_user</i> .
     */
    private String achievementTypeDescr;

    /**
     * Represents column 'is_active' in the table <i>gerard.app_user</i> .
     */
    private boolean isActive;

    public AchievementTypeEntity() {
        throw new UnsupportedOperationException();
    }

    public String getAchievementTypeDescr() {
        return achievementTypeDescr;
    }

    public void setAchievementTypeDescr(String achievementTypeDescr) {
        this.achievementTypeDescr = achievementTypeDescr;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof AchievementTypeEntity achievementTypeEntity) {
            return id == null
                    ? achievementTypeEntity.id == null
                    : id.equals(achievementTypeEntity.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        int hashcode = super.hashCode();
        hashcode = hash * hashcode + (achievementTypeDescr == null
                                      ? 0
                                      : achievementTypeDescr.hashCode());
        hashcode = hash * hashcode + (isActive ? 1 : 0);
        return hashcode;
    }

    @Override
    public String toString() {
        return "AchievementType{"
                + "id=" + id
                + ", achievementTypeDescr='" + achievementTypeDescr + '\''
                + ", isActive=" + isActive
                + '}';
    }
}
