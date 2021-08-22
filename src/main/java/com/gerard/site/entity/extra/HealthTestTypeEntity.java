package com.gerard.site.entity.extra;

import com.gerard.site.entity.AbstractEntity;

/**
 * Class represents single record from table <i>gerard.health_test_type</i> ,
 * note that primary key of record is represented
 * by is superclass AbstractEntity {@link AbstractEntity}
 * by it's instance filed 'id' {@link AbstractEntity#id} .
 *
 * <b>Primary key column is named 'health_test_type' </b> .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class HealthTestTypeEntity extends AbstractEntity<String> {
    /**
     * Represents column 'health_test_type_descr' in the table <i>gerard.health_test_type</i> .
     */
    private String healthTestTypeDescr;

    /**
     * Represents column 'is_active' in the table <i>gerard.health_test_type</i> .
     */
    private boolean isActive;

    public HealthTestTypeEntity() {
        throw new UnsupportedOperationException();
    }

    public String getHealthTestTypeDescr() {
        return healthTestTypeDescr;
    }

    public void setHealthTestTypeDescr(String healthTestTypeDescr) {
        this.healthTestTypeDescr = healthTestTypeDescr;
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
        if (object instanceof HealthTestTypeEntity healthTestTypeEntity) {
            return id == null
                    ? healthTestTypeEntity.id == null
                    : id.equals(healthTestTypeEntity.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        int hashcode = super.hashCode();
        hashcode = hash * hashcode + (healthTestTypeDescr == null ? 0 : healthTestTypeDescr.hashCode());
        hashcode = hash * hashcode + (isActive ? 1 : 0);
        return hashcode;
    }

    @Override
    public String toString() {
        return "HealthTestType{"
                + "id=" + id
                + ", healthTestTypeDescr='" + healthTestTypeDescr + '\''
                + ", isActive=" + isActive
                + '}';
    }
}
