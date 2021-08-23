package com.gerard.site.entity;

import java.io.Serializable;

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
public class HealthTestTypeEntity extends AbstractEntity<String> implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * Represents column 'health_test_type_descr' in the table <i>gerard.health_test_type</i> .
     */
    private String healthTestTypeDescr;

    /**
     * Represents column 'active' in the table <i>gerard.health_test_type</i> .
     */
    private boolean active;

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
        hashcode = hash * hashcode + (active ? 1 : 0);
        return hashcode;
    }

    @Override
    public String toString() {
        return "HealthTestType{"
                + "id=" + id
                + ", healthTestTypeDescr='" + healthTestTypeDescr + '\''
                + ", isActive=" + active
                + '}';
    }
}
