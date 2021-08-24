package com.gerard.site.service.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Class represents single record from table <i>gerard.health_test</i> ,
 * note that primary key of record is represented
 * by is superclass AbstractEntity {@link AbstractEntity}
 * by it's instance filed 'id' {@link AbstractEntity#id} .
 *
 * <b>Primary key column is named 'health_test_id' </b> .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class HealthTestEntity extends AbstractEntity<Integer> implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * Represents column 'health_test_type' in the table <i>gerard.health_test</i> .
     */
    private String healthTestType;

    /**
     * Represents column 'dog_id' in the table <i>gerard.health_test</i> .
     */
    private int dogId;

    /**
     * Represents column 'date_fact' in the table <i>gerard.health_test</i> .
     */
    private Date dateFact;

    /**
     * Represents column 'result' in the table <i>gerard.health_test</i> .
     */
    private String result;

    public HealthTestEntity() {
        throw new UnsupportedOperationException();
    }

    public String getHealthTestType() {
        return healthTestType;
    }

    public void setHealthTestType(String healthTestType) {
        this.healthTestType = healthTestType;
    }

    public int getDogId() {
        return dogId;
    }

    public void setDogId(int dogId) {
        this.dogId = dogId;
    }

    public Date getDateFact() {
        return dateFact;
    }

    public void setDateFact(Date dateFact) {
        this.dateFact = dateFact;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof HealthTestEntity healthTestEntity) {
            return id == null
                    ? healthTestEntity.id == null
                    : id.equals(healthTestEntity.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        int hashcode = super.hashCode();
        hashcode = hash * hashcode + (healthTestType == null ? 0 : healthTestType.hashCode());
        hashcode = hash * hashcode + dogId;
        hashcode = hash * hashcode + (dateFact == null ? 0 : dateFact.hashCode());
        hashcode = hash * hashcode + (result == null ? 0 : result.hashCode());
        return hashcode;
    }

    @Override
    public String toString() {
        return "HealthTest{"
                + "id=" + id
                + ", healthTestType='"
                + healthTestType + '\''
                + ", dogId=" + dogId
                + ", dateFact=" + dateFact
                + ", result='" + result + '\''
                + '}';
    }
}
