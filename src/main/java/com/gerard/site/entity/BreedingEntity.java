package com.gerard.site.entity;

import java.sql.Date;

/**
 * Class represents single record from table <i>gerard.breeding</i> ,
 * note that primary key of record is represented
 * by is superclass AbstractEntity {@link AbstractEntity}
 * by it's instance filed 'id' {@link AbstractEntity#id} .
 *
 * <b>Primary key column is named 'breeding_id' </b> .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class BreedingEntity extends AbstractEntity<Integer> {
    /**
     * Represents column 'date_planned' in the table <i>gerard.breeding</i> .
     */
    private Date datePlanned;

    /**
     * Represents column 'male_dog_id' in the table <i>gerard.breeding</i> .
     */
    private int maleDogId;

    /**
     * Represents column 'female_dog_id' in the table <i>gerard.breeding</i> .
     */
    private int femaleDogId;

    /**
     * Represents column 'date_fact' in the table <i>gerard.breeding</i> .
     */
    private Date dateFact;

    private BreedingEntity() {
        super();
    }

    public Date getDatePlanned() {
        return datePlanned;
    }

    public void setDatePlanned(Date datePlanned) {
        this.datePlanned = datePlanned;
    }

    public int getMaleDogId() {
        return maleDogId;
    }

    public void setMaleDogId(int maleDogId) {
        this.maleDogId = maleDogId;
    }

    public int getFemaleDogId() {
        return femaleDogId;
    }

    public void setFemaleDogId(int femaleDogId) {
        this.femaleDogId = femaleDogId;
    }

    public Date getDateFact() {
        return dateFact;
    }

    public void setDateFact(Date dateFact) {
        this.dateFact = dateFact;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof BreedingEntity breedingEntity) {
            return id == null
                    ? breedingEntity.id == null
                    : id.equals(breedingEntity.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        int hashcode = super.hashCode();
        hashcode = hash * hashcode + (datePlanned == null ? 0 : datePlanned.hashCode());
        hashcode = hash * hashcode + maleDogId;
        hashcode = hash * hashcode + femaleDogId;
        hashcode = hash * hashcode + (dateFact == null ? 0 : dateFact.hashCode());
        return hashcode;
    }

    @Override
    public String toString() {
        return "BreedingEntity{"
                + "id=" + id
                + ", datePlanned=" + datePlanned
                + ", maleDogId=" + maleDogId
                + ", femaleDogId=" + femaleDogId
                + ", dateFact=" + dateFact
                + '}';
    }
}
